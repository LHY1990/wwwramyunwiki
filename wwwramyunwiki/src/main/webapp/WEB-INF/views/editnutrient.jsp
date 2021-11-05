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
    <link rel="stylesheet" href="../../resources/css/nutrient.css">
    <link rel="stylesheet" href="../../resources/css/sidetab.css">

    <script type="text/javascript" src="../../resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
                            라면위키 : 영양 성분
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link">역사</div>
                                <div class="sections_link">추천수</div>
                                <div class="sections_link">편집</div>
                                
                            </div>
                            
                        </div>
                        <div id="edited_time">
                            최근 수정 시각 : ${ingredient.updatedDate}
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 영양 성분 | ${ingredient.name}
                        </div>
                        
                    </div>

                    <div style="width: 100%; height: 40px;">

                    </div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <form action="./editingredient.do" method="post"  >
                        <div id="ingredient_info">
                            <div id="ingredient_items">
                            원재료명 : ${ingredient.name}<br>
                            분류 : 
                            <input type="text" name="name" value="${ingredient.name}" style="display: none;">
                            <select name="type">
                                <option value="${ingredient.type}">${ingredient.type}</option>
                                <option value="동물성">동물성</option>
                                <option value="식물성">식물성</option>
                                <option value="조절제">조절제</option>
                            </select><br>
                            </div>
                            
                            
                            
                            
                        </div>
                        <div id="ingredient_information" style="margin-top: 10px;">
                            
                        </div>
                        <div id="user_description">
                            <textarea name="description" maxlength="8000" id="description" style="width: 99%; height: 1200px; resize: none;overflow-y: scroll;">${ingredient.description}</textarea>
                            
                        </div>
                        
                        <div style="width: 100%; height: 20px;">

                        </div>
                        <div style="width: 99%; height: 30px;">
                            <input type="submit" value="등록" style="width: 100%;height: 100%;">
                        </div>
                        
                        <div style="width: 100%; outline: rgb(122, 103, 129) 1px dotted;"></div>
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