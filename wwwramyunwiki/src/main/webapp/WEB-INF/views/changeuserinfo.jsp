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
    <link rel="stylesheet" href="../../resources/css/login.css">
    <link rel="stylesheet" href="../../resources/css/sidetab.css">

    <script type="text/javascript" src="../../resources/javascript/login.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:정보변경 -라면위키</title>

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
                            라면위키 : 사용자 정보변경
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
                                <form action="changinguser.do" method="post">
                                <br>
                                <div>가입번호 : ${memberNumber}</div>
                                <br>
                                <div>아이디 : ${memberId}</div>
                                <br>
                                <div>이메일 : <input type="text" maxlength="50" placeholder="이메일변경" name="memberEmail" value="${memberEmail}"></div> 
                                <br>
                                <div>가입일 :
                                    <fmt:parseDate value="${memberJoinDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                			    <fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" value="${parsedDateTime}"/>
                                </div>
                                <br>
                                <div>닉네임 : <input type="text" maxlength="12" placeholder="닉네임변경" name="nickname" value="${memberNickname}"></div> 
                                <br>
                                <br>
                                
                                    <button>회원정보변경</button>
                                </form>                                
                            </div>


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