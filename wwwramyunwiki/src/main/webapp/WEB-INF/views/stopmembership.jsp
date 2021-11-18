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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">


    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/login.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
                            라면위키 : 회원 탈퇴
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
                            분류 : 회원 탈퇴 | 이용해 주셔서 진심으로 감사합니다. -라면위키 개발자 올림- 
                        </div>
                        <!-- 로그인 시작 -->
                        <div id="login_div">
                            
                            <div>
                                
                                <form action="withdraw.do" method="post" onsubmit="return checkPasswordEqualForWithdraw()" >
                                    <br>

                                    <div>
                                        회원 탈퇴를 위한 비밀번호 입력
                                        <br>
                                        <input type="password" class="password_form" id="withdrawPassword" name="withdrawPassword" maxlength="40" placeholder="비밀번호 입력" >
                                        <br>
                                        <br>
                                        비밀번호 확인
                                        <br>
                                        <input type="password" class="password_form" id="withdrawPasswordCheck" name="withdrawPasswordCheck" maxlength="40"  placeholder="비밀번호 확인">
                                    </div>
                                        <br>
                                    <button style="float: right;" >회원탈퇴요청</button>
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
<%if(session.getAttribute("isMemberWithdrawDone")=="withdrawMemberFaild"){%>
    <script>
        alert("회원님의 아이디와 입력한 비밀번호가 맞지 않습니다.");

    </script>
<%}%>