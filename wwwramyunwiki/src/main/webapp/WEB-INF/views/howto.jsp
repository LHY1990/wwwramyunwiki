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
    
    <link rel="stylesheet" href="../../resources/css/sidetab.css">
    <link rel="stylesheet" href="../../resources/css/home.css">
    <link rel="stylesheet" href="../../resources/css/howto.css">

    <script type="text/javascript" src="../../resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:영양성분 -라면위키</title>

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
                            라면위키 : 영양 성분
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                

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
                            분류 : 작성방법
                        </div>
                        
                    </div>

                    <div style="width: 100%; height: 170px;"></div>
                    <!-- /////////////////////////본문시작//////////////////////////////////////////////// -->
                    <div id="howto_outer">
                        
                        <div id="howto_left_top_top">작성방법</div>
                        <div id="howto_right_top"> 출력 </div>
                        <div id="description_howto_top">설명</div>

                        <div id="howto_left"> (줄넘김)</div>
                        <div id="howto_right"> 다음줄 </div>
                        <div id="description_howto"> 입력 갯수만큼 다음줄로 넘어갑니다.</div>

                        <div id="howto_left">(글자색)[#ff1111]라면(/글자색)</div>
                        <div id="howto_right"><span style="color: #ff1111;">라면</span> </div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(배경색)[#11ff11]라면(/배경색)</div>
                        <div id="howto_right"><span style="background-color: #11ff11;">라면</span> </div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(굵은글자)라면(/굵은글자)</div>
                        <div id="howto_right"><strong>라면</strong></div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(큰글자)라면(/큰글자)</div>
                        <div id="howto_right"> <b>라면</b></div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(작은글자)라면(/작은글자)</div>
                        <div id="howto_right"><small>라면</small> </div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(밑줄)라면(/밑줄)</div>
                        <div id="howto_right"><u>라면</u></div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(기울임)라면(/기울임)</div>
                        <div id="howto_right"><i>라면</i> </div>
                        <div id="description_howto"></div>

                        <div id="howto_left">(취소선)라면(/취소선)</div>
                        <div id="howto_right"><strike>라면</strike></div>
                        <div id="description_howto"></div>
                        
                        <div id="howto_left">(윗글자)라면(/윗글자)</div>
                        <div id="howto_right"><sup>라면</sup></div>
                        <div id="description_howto"></div>
                        
                        <div id="howto_left">(아래글자)라면(/아래글자)</div>
                        <div id="howto_right"><sub>라면</sub></div>
                        <div id="description_howto"></div>
                        
                        <div id="howto_left">(링크)https://www.naver.com, 네이버(/링크)</div>
                        <div id="howto_right"><a href="https://www.naver.com">네이버</a></div>
                        <div id="description_howto"></div>
                        
                       

                    </div>
                    <!-- //////////////////////////본문  끝 /////////////////////////////////////////////////-->
                    
                    <!-- 여기서 끝나야한다 -->
>
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