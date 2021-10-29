<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/welcome.css">

    <script type="text/javascript" src="../../resources/javascript/welcome.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <title>라면위키:가입환영! -라면위키</title>
</head>

<script>


</script>
 
<body>
    <div class="layoutOut">
        <div class="layoutInner" id="layoutInnerId">
            <div action="join" method="post" >
                <div id="join_title">라면위키</div>
                <div class="innerBox">

                    
                    <br>i  i  i  i  i <br>
                    회원가입이<br>
                    완료되었습니다.<br>
                    <br>
                    ${newMember} 님, <br>
                    회원가입을 축하합니다.<br>
                    <button id="go_to_home" type="button"><a href="./home" style="text-decoration: none;">홈으로 가기</a></button>
                    
                    <br>
                    
                    <div id="codenumber_check" style="display: none;">
                        인증번호 확인<br>
                        <input type="text" name="memberEmailCode" style="margin-top: 10px;"><br>
                        <input id="joinSubmit" type="submit" value="가입하기">
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

</html>