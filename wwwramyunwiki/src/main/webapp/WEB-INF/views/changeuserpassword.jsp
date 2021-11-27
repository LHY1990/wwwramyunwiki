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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">


    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/login.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:계정정보변경 -라면위키</title>
    <style>
        /* 작은페이지여서 여기서 정의함 */
        .password_form{
            width: 90%; 
            height: 25px; 
            margin-top: 10px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="./header.jsp" %>
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : 비밀번호 변경
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link"></div>
                                <div class="sections_link"></div>
                                <div class="sections_link"></div>
                                
                            </div>
                            
                        </div>
   
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 계정 정보 변경
                        </div>
                        <!-- 로그인 시작 -->
                        <div id="login_div">
                            
                            <div>
                                
                                <form action="changingPassword.do" method="post" onsubmit="return checkPasswordEqual()" >
                                    <br>
                                    <div>현재 비밀번호
                                        <br>
                                        <input type="password" class="password_form" id="oldPassword" name="oldPassword" maxlength="30" placeholder="기존 비밀번호 입력" autofocus >
                                    </div>
                                    <br>
                                    <div>
                                        새로운 비밀번호
                                        <br>
                                        <input type="password" class="password_form" id="newPassword" name="newPassword" maxlength="30" minlength="8" placeholder="새 비밀번호 입력 (8자 이상 입력해주세요)" >
                                        <br>
                                        <br>
                                        비밀번호 확인
                                        <br>
                                        <input type="password" class="password_form" id="newPasswordCheck" name="newPasswordCheck" maxlength="30" minlength="8" placeholder="새 비밀번호 확인">
                                    </div>
                                        <br>
                                    <button style="float: right;" >비밀번호 변경</button>
                                </form>                                
                            </div>


                        </div>
                        <!-- 로그인끝 -->
                        <div style="width: 100%; height: 40px;">

                        </div>

                        <div style="width: 100%; outline: rgb(122, 103, 129) 1px dotted;"></div>


                        
                        
                        
                        
                    </div>
                       


                <!-- 여기서 끝나야한다 -->
                </div>

                
                
            </div>   
            <!-- 우측 탭 시작 -->
            <%@ include file="./sidetab.jsp" %>
            <!-- 우측 탭 끝 -->
        </div>
        
        
    </div>

    <!-- 푸터시작 -->
    <%@ include file="./footer.jsp" %>
    <!-- 푸터끝 -->
    

    
</body>
</html>
