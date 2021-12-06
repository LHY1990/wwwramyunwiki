<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta http-equiv="refresh" content="4; url=http://ramyun.wiki/">
</head>
<style>
    *{
        text-decoration: none;
        text-align: center;
        font-family: "ONE-Mobile-POP";
        font-size: 23px;
    }
    @font-face {
        font-family: 'ONE-Mobile-POP';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    body{
        margin: 0px;
        padding: 0px;
    }
</style>
<script>
    window.addEventListener('DOMContentLoaded', function(){
        var restTime=4;
        setInterval(() => {
            document.getElementById('counting').innerText=restTime;
            restTime=restTime-1;
        }, 1000);
        
        


    });
</script>
<body>
    <div style="background-color: rgb(255, 102, 0); height: 250px;width: 100%;"></div>

    <br>
    <br>
    <br>
    <br>
    <span style="font-size: 70px;">라면위키</span>
    <br>
    <br>

    현재 페이지는 리뉴얼 중인 서비스 입니다. <br>
    좀 더 나은 서비스를 위해 노력중입니다. <br>
    감사합니다.
    <br>
    <br>
    <span id="counting">5</span>초후에 홈으로 돌아갑니다.


    <br>
    <br>
    <br>
    <br>
    <br>

    <div style="background-color: rgb(255, 102, 0); height: 700px;width: 100%;"></div>
</body>
</html>
