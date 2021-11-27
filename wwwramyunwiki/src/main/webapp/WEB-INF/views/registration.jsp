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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/regist.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:영양성분 -라면위키</title>

</head>
<body>
    <%@ include file="./header.jsp" %>
    <!-- 상단패널 -->
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : 등록하기
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link">역사</div>
                                <div class="sections_link"><i class="far fa-thumbs-up"></i> 추천</div>
                                <div class="sections_link">편집</div>
                                
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
                            분류 : 키워드 등록
                        </div>
                        


                        <div style="width: 100%; height: 40px;">

                        </div>
                        <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                        <form action="./regist.do" method="post"  >
                            <div id="regist_info">
                                <div id="regist_items">
                                등록명 : <input type="text" name="register" maxlength="39" style="height: 24px; margin-right: 30px;">
                                구분 : 
                                <select name="type" style="height: 24px; ">
                                    <option value="ramyun">라면</option>
                                    <option value="ingredient">원재료명</option>
                                    <option value="manufactory">공장정보</option>
                                </select>
                                <input type="submit" value="등록" style="height: 24px;">
                                
                                </div>
                                
                                
                                
                                
                            </div>
                            
                        </form>
                        <!-- //////////////////////////본문  끝 /////////////////////////////////////////////////-->
                        
                        
                        
                        
                    </div>
                       


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