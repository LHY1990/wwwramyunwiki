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
    <link rel="stylesheet" href="../../resources/css/recentupdating.css">
    <link rel="stylesheet" href="../../resources/css/sidetab.css">

    <script type="text/javascript" src="../../resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:최근변경 -라면위키</title>

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
                            라면위키 : 최근 변경
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
                            분류 : 최근 변경
                        </div>
                        


                        <div style="width: 100%; height: 10px;">

                        </div>
                        
                            
                            <c:forEach var="aRamyun" items="${ramyunListWhole}" >
                                

                                <!-- <p style=" text-align: left;  display: table-cell;vertical-align: middle; padding-top: 3px;padding-bottom: 3px; padding-left: 10px">   -->
                                <div style="width: 20%;height: 100%; float: left; text-align: left; text-overflow: ellipsis;overflow: hidden;white-space: nowrap;"><a href="./findramyun.do?name=${aRamyun.brandNameKor}" style="text-decoration: none">${aRamyun.brandNameKor}</a></div>
                                <div style="width: 20%;height: 100%; float: left; text-align: left; text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">
                                    <fmt:parseDate value="${aRamyun.updatedDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	              				   <fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${parsedDateTime}"/>
                                
                                </div>
                                <div style="width: 60%;height: 100%; float: left; text-align: left; text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">${aRamyun.userEditedContents}</div>
                                <div style="width: 100%;height: 0px; outline: 1px dotted rgba(0, 0, 0, 0.123); float: right;"></div>
                                
                                <!-- </p> -->
                            </c:forEach>
                            
                            
                        

                        <div style="width: 100%; height: 20px;">

                        </div>

                        


                        
                        
                        
                        
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