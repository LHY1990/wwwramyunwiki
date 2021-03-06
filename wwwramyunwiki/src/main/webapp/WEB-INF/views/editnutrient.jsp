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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrient.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:영양성분 -라면위키</title>

</head>
<body>
    <%@ include file="./header.jsp" %>
    <%@ include file="./instruct.jsp" %>
    <!-- 상단패널 -->
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : 영양 성분
                        </div>

                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <!-- <div class="sections_link">역사</div> -->
                            </div>
                        </div>

                        <div id="edited_time">
                            <!-- 최근 수정 시각 : ${ingredient.updatedDate} -->
                        </div>

                        <div style="height: 100px;"></div>
                        
                        <div id="sorting_category">
                            분류 : 영양 성분 | ${ingredient.name}
                        </div>

                        <a href="#submit_button" style="float : right; text-decoration: none; font-size: 12px; color: black;">[수정 버튼으로 가기]</a>
                    </div>

                    <div style="width: 100%; height: 170px;">

                    </div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <form action="/editingredient" method="post"  >
                        <div id="ingredient_info">
                            <div id="ingredient_items">
                            원재료명 : ${ingredient.name}<br>
                            분류 : 
                            <input type="text" maxlength="40" name="name" value="${ingredient.name}" style="display: none;">
                            <select name="type">
                                <option value="${ingredient.type}">${ingredient.type}</option>
                                <option value="동물성">동물성</option>
                                <option value="식물성">식물성</option>
                                <option value="조절제">조절제</option>
                            </select><br>
                            </div>
                        </div>
                        <div id="ingredient_information" style="margin-top: 10px;"></div>
                        <span id="error_alerting_span_A" style="color: red;"></span><br>
                        <div id="user_description" style="margin: 0px;"><textarea id="nutrition_description" name="description" maxlength="8000" id="description" style="width: 99%; height: 1200px; resize: none;overflow-y: scroll;">${ingredient.description}</textarea></div>
                        <span id="error_alerting_span_B" style="color: red; line-height: 24px;"> </span>
                        <div style="width: 99%;">
                            <input id="submit_button" type="submit" value="등록" style="width: 100%;">
                        </div>
                    </form>
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