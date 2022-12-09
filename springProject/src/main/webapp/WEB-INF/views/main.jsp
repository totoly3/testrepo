<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>




</head>
<body>
	<jsp:include page="common/header.jsp"/>
<%--    <jsp:include page="common/footer.jsp"/> --%>
<div class="content">
<br><br>
	<div class="toplist"></div>
	<h4>게시글 TOP5</h4>
	<br>
	<a href="list.bo">게시글 더보기</a>
	
	<table id="boardList" class="table table-hover" align="center">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>첨부파일</th>
			</tr>
		
		</thead>
		<tbody>
		
		</tbody>
	</table>
</div>




	<script >
		
	$(function (){
		topBoardlist();
// 		setInterval(topBoardlist,1000);  //1초 마다 조회
		
		//동적으로 생성된 이벤트를 걸어주려면 상위요소 선택자 이벤트를 function 을 작성해야한다 . 
// 		$("#boardList tr").click(function(){
// 			console.log("z클릭됨");
// 		})
		$(document).on("click","#boardList>tbody>tr",function(){
			console.log("클릭됨");
			console.log($(this).children().eq(0).text());
			location.href="detail.bo?bno="+$(this).children().eq(0).text();
		})
	})
	
	function topBoardlist(){
		$.ajax({
			url : "topBoard.bo",
// 			data :
// 				{
// 				boardNo : ${b.boardNo},
// 				COUNT : COUNT,
// 				BOARD_WRITER : BOARD_WRITER
// 				},
			success : function(result){
				console.log(result)
				var resultStr = "";
				
				for(var i=0; i<result.length; i++){
					resultStr += "<tr>"
									+"<td>"+result[i].boardNo+"</td>"
									+"<td>"+result[i].boardTitle+"</td>"
									+"<td>"+result[i].boardWriter+"</td>"
									+"<td>"+result[i].count+"</td>"
									+"<td>"+result[i].createDate+"</td>"
									+"<td>";
									if(result[i].originName != null){
											resultStr +="☆";
									}
									resultStr += "</td></tr>";
								}
							$("#boardList> tbody").html(resultStr);				
					},	
			error:function(){
				console.log("통신실패");
			}
		});
	}
</script>


   <%@ include file="common/footer.jsp" %>
   
</body>
</html>