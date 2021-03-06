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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:대문 -라면위키</title>
    
    

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
                            라면위키 : 대문 
                            
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link" style="color: grey;">역사</div>
                                <div class="sections_link" style="color: grey;"><i class="far fa-thumbs-up"></i> 추천</div>
                                <div class="sections_link" style="color: grey;">편집</div>
                                
                            </div>
                            
                        </div>
                        <div id="edited_time">
                            <c:set var="now" value="<%=new java.util.Date()%>" />
							<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set> 
                            최근 접속 시각 : <fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" />
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : HOME 
                            
                        </div>
                        

                        <div id="alert_info">
                            <div class="alert_section">
                                <div class="alert_left_section">
                                    <!-- 환영합니다. -->
                                    <i class="fas fa-hand-holding-heart"></i>
                                </div>
                                <div class="alert_right_section">
                                    
                                    <br>
                                    <strong style="font-size: larger;">라면위키에 오신것을 환영합니다!</strong><br>
                                    본 서비스가 무엇인지 궁금하다면, <a href="/introduction">소개</a>를 읽어주세요.
                                    
                                </div>
                            </div>
                            <div class="alert_section">
                                <div class="alert_left_section">
                                    <!-- 처음오셨다면? -->
                                    <i class="fas fa-question-circle"></i>


                                </div>
                                <div class="alert_right_section">
                                    <br>
                                    <strong style="font-size: larger;">작성방법</strong><br>
                                    작성방법이 궁금하다면 <a href="/howto">여기</a>를 클릭해주세요
                                </div>
                            </div>
                            <div class="alert_section">
                                <div class="alert_left_section">
                                    <!-- 작성 방법 -->
                                    <i class="far fa-file-alt"></i>
                                </div>
                                <div class="alert_right_section">
                                    <br>
                                    <strong style="font-size: larger;">다같이 만들어봐요 </strong><br>
                                    라면위키에 오신것을 진심으로 환영합니다. 지켜야할 규칙들은 <a href="rules">여기</a>에서 확인 해 주세요.
                                </div>
                            </div>
                            <div class="alert_section">
                                <div class="alert_left_section">
                                    <!-- 역사 -->
                                    <i class="fas fa-history"></i>
                                    
                                </div>
                                <div class="alert_right_section">
                                    <br>
                                    <strong style="font-size: larger;">오늘 내가 먹는 라면은 어떻게 시작되었나?</strong><br>
                                    간식으로도 식사로도 늘 우리곁에 있는 라면. <a href="history">어디서</a> 시작되었는가
                                </div>
                            </div>
                            

                        </div>
                        <div style="width: 100%; height: 40px;">

                        </div>
                        <div id="statistics">
                            <div id="inner_statistics">
                                
                                <h2 style="margin: 0%;">라면백과</h2>
                                <div style="height: 10px;"></div>
                                <div style="margin: 0%;">다같이 만들어가는 대한민국 라면 백과사전<br>
                                    ${ramyunCount} 종의 라면과 기여자 ${memberCount} 명이 함께합니다.</div>
                            </div>
                            
                            
                        </div>

                        <div style="width: 100%; height: 20px;">

                        </div>

                        <div style="width: 100%; outline: rgb(245, 77, 10) 1px dotted;"></div>

                        <span style="color: rgb(255, 115, 0); ">
                            <br>
                            # 알림 <br>
                            라면위키의 log4j가 최신 버전으로 업데이트 되었습니다(12월 11일 24:00)
                        </span>
                        
                        
                        
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