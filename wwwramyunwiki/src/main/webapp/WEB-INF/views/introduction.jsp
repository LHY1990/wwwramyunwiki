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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/howto.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:소개 -라면위키</title>

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
                            라면위키 : 소개
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                

                            </div>
                            
                        </div>
                        <div id="edited_time">
                            <c:set var="now" value="<%=new java.util.Date()%>"/>
							<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set> 
                            최근 접속 시각 : <fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" />
                            
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 라면위키 소개
                        </div>
                        
                    </div>

                    <div style="width: 100%; height: 170px;"></div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <div id="introduction" style="text-align: left; font-size: 14px;">
                        이 문서는 추후 기능 개선을 통해 사용자로 하여금 소개와 규정을 정할수 있도록 만들 예정입니다. <br>

                        1. 전문<br>
                        <br>
                        라면위키는 2021년 12월 4일에 프로토타입이 완성된 대한민국에 출시되었던 라면들을 좀 더 접근하기 쉽게 하기위해 만들어졌습니다. 
                        위키백과와 나무위키에서 많은 영향을 받은 라면위키는 라면을 사랑하는 사람들의 관심과 사랑을 전제로 디자인 되었습니다. 
                        <br>
                        대한민국의 라면은 그 탄생부터 저렴한 음식이 아니었지만, 굶주린 사람들에게 제대로 된 식사를 제공하고자 했던 한 기업가로 말미암아 시작되었습니다. 
                        일본 묘조식품의 기술 원조로 시작된 한국 인스턴트 라면 산업은 한국 나름의 식문화를 이루고 다른 나라들에게 수출되고 있습니다. 
                        이 정도로 규모를 가진 시장이 있음에도 라면에 대한 위키가 없음에 직접 라면위키를 만들게 되었습니다. 
                        <br>
                         라면위키는 기존 위키들보다 보다 많은 정보를 강요합니다. 또한 수 많은 해외 라면들이 있음에도 국내 라면에 한정지어 서비스가 설계되었습니다. 
                        추후 추가 될 수도 있지만 국내라면들에 대한 정보들이 정리가 된 이후로 보고 기존의 서비스에 충실하려합니다. 
                        <br>
                         국내의 라면들의 영문이름은 각 기업의 정책에 따라 다른데, 신라면(Shin Ramyun)같이 ramyun으로 표기 되거나 라면사리(Ramensari) 처럼 라멘으로 표기된 제품들이 같이 유통되고 있습니다. 
                        ramyun보다는 ramen이 좀 더 익숙하고 검색 및 외우기 쉬움에도 ramyun.wiki 라는 도메인을 선택하였습니다.
                         한국에서 수출되는 만두나 고추장의 영문표기가 덤플링dumpling과 레드페퍼페이스트red pepper paste에서 만두mandu와 고추장Gochujang으로 바뀌어가듯 한국의 인스턴트 라면도 고유함을 
                        지금보다 더 인정받기를 바랍니다. 
                        <br>
                        

                        

                         







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

    <!-- 푸터시작 -->
    <%@ include file="./footer.jsp" %>
    <!-- 푸터끝 -->
    

    
</body>
</html>