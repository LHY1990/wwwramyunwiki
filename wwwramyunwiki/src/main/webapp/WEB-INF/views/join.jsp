<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/join.css">

    <script type="text/javascript" src="../../resources/javascript/join.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <title>라면위키:회원가입 -라면위키</title>
</head>

<script>
	

</script>
 
<body>
    <div class="layoutOut">
        <div class="layoutInner" id="layoutInnerId">
            <form action="join" method="post" >
                <div id="join_title">라면위키</div>
                

                
                
                <div class="innerBox">

                    아이디<br>
                    <input type="text" minlength="4" maxlength="18"  name="memberId" placeholder="4자이상 15자 이하로 입력" value="${memberId}"><br>
                    <br>
                    비밀번호<br>
                    <input type="password" minlength="8" maxlength="21" id="memberPassword" name="memberPassword" placeholder="8자이상 20자 이하로 입력" value="${memberPassword}"><br>
                    비밀번호 확인<br>
                    <input type="password" minlength="8" maxlength="21" id="passwordCheck" name="passwordCheck" placeholder="8자이상 20자 이하로 입력" value="${memberPassword}"><br>
                    <br>
                    본인 확인 이메일<br>
                    <input id="memberEmail" type="text" name="memberEmail" placeholder="email@gmail.com"  value="${memberEmail}"><br>
                    <button id="send_mail" type="submit" onclick="sendingMailCode()" >인증번호 보내기</button>
                    
                    <br>
                    <c:if test="${memberId!=null}">
	                    <div id="codenumber_check">
	                        인증번호 확인<br>
	                        <input type="text" name="memberEmailCode" style="margin-top: 10px;"><br>
	                        <input id="joinSubmit" type="submit" value="가입하기">
	                    </div>
                    </c:if>
                </div>
                

            </form>
            <c:if test="${memberAlert eq 'error'}">
            		<script type="text/javascript">alert('중복된 아이디입니다.');</script>
            </c:if>
            <c:if test="${memberAlert eq 'codeSended'}">
            		<script type="text/javascript">alert('메일로 인증번호가 전송되었습니다.');</script>
            </c:if>
            <c:if test="${memberAlert eq 'codeIsNotSame'}">
            		<script type="text/javascript">alert('인증번호가 다릅니다.');</script>
            </c:if>
        </div>
    </div>

</body>

</html>