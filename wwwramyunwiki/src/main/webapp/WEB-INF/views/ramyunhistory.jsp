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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:히스토리 -라면위키</title>

</head>
<style>
    a{ text-decoration: none; color: black; vertical-align: unset;}
    #writer{color: blue;}
</style>
<body>
    <%@ include file="./header.jsp" %>
    <!-- 상단패널 -->
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame" style="height: auto;">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : 수정 히스토리
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <!-- <div class="sections_link">역사</div> -->
                                
                                
                            </div>
                            
                        </div>
                        <div id="edited_time">
                            <!-- 최근 수정 시각 : ${ingredient.updatedDate} -->
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 라면 수정 히스토리 | ${ramyun_history.brandNameKor}
                        </div>
                        
                    </div>

                    <div style="width: 100%; height: 170px;">

                    </div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <div action="./editingredient.do" method="post"  >
                        <!-- 히스토리는 여기에 들어간다. -->
                        <div id="log_section" style="width: 100%;height: auto; text-align: left;">
                        
                            <c:forEach var="ramyun_history" items="${ramyunHistoryList}" begin="0">
                                    
                                <p style="margin: 0%;padding: 0%; width: 100%;">
                                    <a id="ramyun_history_one" href="./findramyun.do?name=${ramyun_history.brandNameKor}" >
                                        <strong>${ramyun_history.brandNameKor}  </strong> 
                                    </a>
                                    |  수정 : 
                                    <fmt:parseDate value="${ramyun_history.updatedDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
                                    <fmt:formatDate pattern="yyyy.MM.dd hh:mm:ss" value="${parsedDateTime}"/>
                                    |  <strong id="writer"> 작성자 : ${ramyun_history.writer}</strong> 
                                    |   log : ${ramyun_history.id}  |  
                                    <a href="./ramyunlog.do?id=${ramyun_history.id}">(이 버전으로 보기)</a>
                                    
                                </p>

                            </c:forEach>
                        
                        
                        </div>
                        <!-- 히스토리는 여기서 끝난다. -->
                    </div>
                    <!-- //////////////////////////본문  끝 /////////////////////////////////////////////////-->
                <!-- 여기서 끝나야한다 -->
                </div>
            </div>   
            <!-- 우측 탭 시작 -->
            <%@ include file="./sidetab.jsp" %> 
            <!-- 우측 탭 끝 -->
        </div>
    </div>
    <%@ include file="./footer.jsp" %>
</body>
</html>