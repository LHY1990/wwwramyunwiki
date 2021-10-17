<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <form action="join" method="post">
                <div id="join_title">라면위키</div>
                <div class="innerBox">

                    아이디<br>
                    <input type="text" name="memberId"><br>
                    <br>
                    비밀번호<br>
                    <input type="password" name="memberPassword"><br>
                    비밀번호 확인<br>
                    <input type="password" name="passwordCheck"><br>
                    <br>
                    본인 확인 이메일<br>
                    <input type="text" name="memberEmail"><br>
                    <br>
                    <input id="joinSubmit" type="submit" value="가입하기">
                </div>
            </form>
        </div>
    </div>

</body>

</html>