<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
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
    </style>
</head>
<body>
    
    <!-- 메뉴바 -->
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" required><button id="idcheck">중복확인</button> <br>
					
					<div id="checkResult" style="font-size:0.8em; display:black;"></div>
					
                  
                
							
                    
                    
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="userPwd" required> <br>
				
                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" id="enrollBtn" >회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>
     <script >
                   		$(function(){
                   			$("#idcheck").click(function(){
                   				
                   				$.ajax({
                   					url:"idCheck.me",
                   					data:{idcheck:$("#userId").val()},
                   					success :function(result){
                   						console.log(result);
                   						if(result=="NNNNN"){
		                   					$("#checkResult").css("display","block").css("color","red").html("중복입니다만");
                   						}else{
                   							$("#checkResult").css("display","block").css("color","red").html("사용가능하다 써라");
//                    							${"#enrollBtn"}.attr("disabled",false);
												}
                   						
                   					},
                   					error :function(){
                   						console.log("통신실패");
                   					}
                   				})
                   			});
                   		});
                   			
                   		//아래는 아이디를 입력하는 input 요소 객체르 변수에 담아두기
//                    		const $idInput = $("#userId");
//                    		$idInput.keyup(function(){
//                    			//input 요소 객체에 키가 눌렀다가 떼어질때 발생시키는 function 
                   			
//                    		$idInput.keyup(function(){

//                    			//최소 5글자 이상일 경우에만 중복ㅊㅔ크가 진행되게 작업 
//                    			if($idInput.val().length>=5){
//                    				$.ajax({
//                    					url :"idCheck.me",
//                    					data :{checkId :$idInput.val()},
//                    					success : function(result){
//                    						if(result=="NNNNN"){ //사용불가
// 		                   					$("#checkResult").css("display","block").css("color","red").html("중복입니다만");
//                						}else{
//                							$("#checkResult").css("display","block").css("color","red").html("사용가능하다 써라");
//                							$("#enrollBtn").attr("disabled",false);
//                						}
//                    					},
//                    					error :function(){
//                    						console.log("통신실패");
//                    					}
//                    				})
                   					
//                    			}else{//5글자 미만일 경우  
//                    				$("#checkResult").html("5글자 이상 입력해주세요").show();
//                    			}
//                    		})
                   		
//                    		})
					</script>	
    	
	

    

    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp"/>

</body>
</html>