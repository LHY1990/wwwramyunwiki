<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 문자열 길이 체크 태그 -->
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/tag.css">
    <link rel="stylesheet" href="../../resources/css/sidetab.css">

    <script type="text/javascript" src="../../resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:태그검색 -라면위키</title>

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
                            라면위키 : 태그 검색
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link">역사</div>
                                <div class="sections_link">추천수</div>
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
                            분류 : 태그 검색
                        
                        </div>
                        <br>
                        <div id="sorting_tag_div" >
                            <div id="sorting_tag_abc">가나다</div>
                            <div id="sorting_tag_brand">브랜드 순</div>
                        </div>
                        
                        
                        


                        <div style="width: 100%; height: 10px;">

                        </div>
                        <div id="tag_update_list">
                            <div class="vertical_section">
                                <c:forEach var="searching" items="${searchList}" begin="0" end="49">
                                    <p class="getLength" style="margin: 0%;padding: 0%; width: 100%;">${searching.name}</p>

                                </c:forEach>
                            </div>
                            <div class="vertical_section">
                                <c:forEach var="searching" items="${searchList}" begin="50" end="99">
                                    <p class="getLength" style="margin: 0%;padding: 0%; width: 100%;">${searching.name}</p>

                                </c:forEach>
                            </div>
                            <div class="vertical_section">
                                <c:forEach var="searching" items="${searchList}" begin="100" end="149">
                                    <p class="getLength" style="margin: 0%;padding: 0%; width: 100%;">${searching.name}</p>
                                    
                                </c:forEach>
                            </div>
                            
                            
                        </div>

                        <div style="width: 100%; height: 20px;">

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