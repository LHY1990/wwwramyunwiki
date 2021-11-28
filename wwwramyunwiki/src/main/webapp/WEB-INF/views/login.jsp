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

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/login.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <%@ include file="./bxslider.jsp" %>
    <title>라면위키:로그인 -라면위키</title>

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
                            라면위키 : 로그인
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
                            분류 : LOG IN
                        </div>
                        <!-- 로그인 시작 -->

                        <div id="login_div">
                            
                            <form action="./afterlogin" method="post">
                                <div style="margin-top : 50px;"></div>
                                <div id="type_id">아이디</div>
                                <input id="type_id" minlength="4" type="text" name="memberId" placeholder="아이디를 입력하세요" autofocus><br><br>
                                <div id="type_password">비밀번호</div>
                                <input id="type_password" minlength="4" type="password" placeholder="비밀번호를 입력하세요"  name="memberPassword">
                                <div id="finding_id_password"><a href="./findid.do" style="text-decoration: none;">아이디/비밀번호 찾기</a></div><br>
                                <div id="join_member"><a href="./rules">회원가입</a></div>
                                <div id="member_login" ><input type="submit" value="로그인" id="member_login" style="background-color: rgb(113, 113, 223, 0.3);"></div>

                            </form>


                        </div>
                        <!-- 로그인끝 -->
                        <div style="width: 100%; height: 5px;">

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

<%if(session.getAttribute("isMember")=="false"){%>
    <script>
        alert("입력한 회원아이디와 비밀번호에 일치하는 회원이 없습니다.");
        
    </script>
    
<%}
    session.setAttribute("isMember", "");
%>