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
    
    
    <link rel="stylesheet" href="../../resources/css/manufactory.css">
    <link rel="stylesheet" href="../../resources/css/sidetab.css">
    <script type="text/javascript" src="../../resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:제조공장 -라면위키</title>

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
                        <div id="section" style="float: left;">
                            라면위키 : ${manufactory.factoryName}
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link">역사</div>
                                <div class="sections_link">추천수</div>
                                <div class="sections_link"><a href="./editmanufactory.do?findname=${manufactory.factoryName}" style="vertical-align: unset;" >편집</a></div>
                                
                            </div>
                            
                        </div>
                        <div id="edited_time" >
                       		최근 수정 시각 : 
                        		<fmt:parseDate value="${manufactory.updatedDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                			<fmt:formatDate pattern="yyyy년 MM월 dd일 HH시 mm분 ss초" value="${parsedDateTime}"/>
                            
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 제조 공장 | ${manufactory.factoryName} (최근 업데이트)
                        </div>
                        
                    </div>

                    <div style="width: 100%; height: 200px;">

                    </div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <div id="manufactory_info">
                        <div id="manufactory_items">
                        공장이름 : ${manufactory.factoryName}<br>
                        회사명 : ${manufactory.corporateName}<br>
                        주소 : ${manufactory.adress}<br>
                        공장 식별 문자 : ${manufactory.identifyLetter}<br>
                        관련 품목 보고 번호 : ${manufactory.itemReportNumber}<br>
                        
                        </div>
                        
                        
                        
                        
                    </div>
                    <div id="manufactory_description" style="margin-top: 10px;">
                        ${manufactory.description}
                    </div>
                    
                    <!-- //////////////////////////본문  끝 /////////////////////////////////////////////////-->
                    <div style="width: 100%; height: 20px;">

                    </div>

                    <div style="width: 100%; outline: rgb(122, 103, 129) 1px dotted;"></div>


                        
                        
                        
                        
                    
                       


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