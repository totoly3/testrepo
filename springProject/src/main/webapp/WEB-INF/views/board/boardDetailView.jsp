<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .content {
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
       <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${b.boardTitle }</td>
                </tr>
                
<%--                 <c:forEach var="b" items="${b}"> --%>
                <tr>
                    <th>작성자</th>
                    <td>${b.boardWriter }</td>
                    <th>작성일</th>
                    <td>${b.createDate}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                        <a href="${B.changeName }" download="${b.originName}">${b.originName}</a>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${b.boardContent}</p></td>
                </tr>
<%--                </c:forEach> --%>
            </table>
            <br>
            <!--아래는 로그인 유저의 아이디가 글작성자와 일치한다면 수정하기 삭제하기 버튼이 보이게 끔 !  -->
	<c:if test="${loginUser.userId eq b.boardWriter }">
            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <a class="btn btn-primary" onclick="postFormSubmit(1);">수정하기</a>
                <a class="btn btn-danger" onclick="postFormSubmit(2);">삭제하기</a>
            </div>
            <br><br>
            
            <form id="postForm" method="post">
            	<input type="hidden" name="bno" value="${b.boardNo }">
            	
            	<input type="hidden" name="filePath" value="${b.changeName}">
            </form>
            
           <script >
  				function postFormSubmit(num){
  					console.log(num);
  					//아래는 dom요소 만들어서 이벤트 submit해보기 
  					
  					var form = $("<form>");
  					var cBno = $("<input>").prop("type","hidden").prop("name","bno").prop("value","${b.boardNo}");
  					var cFp = $("<input>").prop("type","hidden").prop("name","filePath").prop("value","${b.changeName}");
  					//form태그에 input요소 두개 넣기 
  					form.append(cBno).append(cFp);
  					if(num==1){  //수정하기 버튼이 눌렸을때 요청주소는 update.bo 요청 타입은 get타입 
  						
  						form.prop("action" , "updateForm.bo");
  					}else{ //삭제하기 버튼이 눌렸을때 요청 주소는  delete.bo  요청타입은 post 
  						form.prop("action" , "delete.bo");
  						
  					}
  					
  					//아래는 만들어 놓은 form 요소를 body 에 추가하기 
					$("body").append(form);
  					
  					form.prop("method","POST").submit();
  					
  					
  					console(form);
					console(cBno);
					console(cFp);
				  					
  					//action에 속성을 넣고 submit을 진행하면 된다 . 
  					//아래는 수정하기 1번 클릭시    1이 넘어온다 
//   					if(num ==1){
//   						$("#postForm").attr("action","update.bo").submit();
//  					}else{ //1이 아니면 삭제하기 2번 ..!
//   						$("#postForm").attr("action","delete.bo").submit();
  						
//   					}
  					
  				}
		   </script>
		   </c:if>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                <c:choose>
                	<c:when test="${not empty loginUser}" >
	                	<tr>
	                        <th colspan="2">
	                            <textarea class="form-control" name="rcontent" id="rcontent" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addReply();">등록하기</button></th>
	                	</tr>
	                	</c:when>
	                  
	                	<c:otherwise>
	                	<tr>
	                	     <th colspan="2">
	                            <textarea class="form-control" name="rcontent" id="rcontent" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addReply();" readonly>등록하기</button></th>
	                	</tr>
                	</c:otherwise>
                </c:choose>
                    <tr>
                        <td colspan="3">댓글(<span id="rcount"></span>)</td>
                    </tr>
                </thead>
                <tbody>
            
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <script >
 
    	$(function(){
    		selectReplyList(); //dom 요소 생성 후 맨처음에(페이지 구성될때 댓글리스트 조회해오기) 
    	})
    	
		//댓글 조회 함수    	
    	function selectReplyList(){
    		$.ajax({
    			url :"rlist.bo",
    			data : {bno : ${b.boardNo}},
    			success : function(result){
//     				console.log(result);
			var resultStr = "";
				
				for(var i=0; i<result.length; i++){
					resultStr += "<tr>"
								+"<th>"+result[i].replyWriter+"</th>"
								+"<td>"+result[i].replyContent+"</td>"
								+"<td>"+result[i].createDate+"</td>"	
								+"</tr>";
				}	
				
				$("#replyArea > tbody").html(resultStr);
				$("#rcount").text(result.length);
    			},
    			error :function(){
    				console.log("통신 실패");
    			}
    		})
    	}
    	
    //아래는 댓글 등록 
    	function addReply(){
    		var $vali = $("#rcontent");
    		
    		//바로아래는 공백제거 후 댓글이 작성되었는지 확인 (공백작성불가하게 막기 )
    		if($vali.val().trim().length != 0){
	    	    	$.ajax({
			    		url : "rinsert.bo",
			    		data : {
			    				refBno : ${b.boardNo},
			    				replyContent :$vali.val(),
			    				replyWriter : "${loginUser.userId}" 
			    					//admin 문자 자체로 나오기때문에 문자열 처리를 해줘야함  하지않으면 변수로인식하기때문임.
			    			},
			    			success : function(result){
			    				console.log(result);
			    				if(result=="yes"){
			    					selectReplyList();
			    					$vali.val("");
			    				}
			    			},
			    			error : function(){
			    				console.log("통신실패");
			    			}
			    		});
   				 }else{ //공백을 넣었거나 댓글을 작성하지 않은경우 
   	    			alert("댓글을작성하세요 . 공백은 작성불가");
   				 	$vali.val("");
   	    		}
    		}
    
    
    </script>
 
    
    
 <%@include file="../common/footer.jsp" %>    
</body>
</html>