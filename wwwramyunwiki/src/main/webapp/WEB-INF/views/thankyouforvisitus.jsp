<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/welcome.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/welcome.js"></script>
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

                    
                    회원탈퇴가 완료되었습니다
                    <br>
                    라면위키를 찾아주셔서 
                    <br>
                    감사합니다.
                    <br>
                    <form action="/home" method="get">
                        <button style="width: 300px; color: white;background-color: grey; font: bold;">홈으로 가기</button>
                    </form>
                    <br>
                    
                    
                </div>
            </div>
        </div>
    </div>

</body>

</html>