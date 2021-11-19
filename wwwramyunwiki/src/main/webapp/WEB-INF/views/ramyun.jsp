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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ramyun.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:라면정보 -라면위키</title>

</head>
<body>
    <%@ include file="./header.jsp" %>
    <!-- 윗줄들이다. -->
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section" style="width: 500px;">
                            라면위키 : ${ramyun.brandNameKor}
                        </div>
                        
                        <div id="section_linkings">
                            <div id="section_linkings_frame">

                                <!-- 역사 -->
                                <div class="sections_link">
                                    <a href="./ramyunhistory.do?name=${ramyun.brandNameKor}" style="vertical-align: unset; color:black">역사</a>
                                </div>
                                
                                
                                <!-- 신고 -->
                                <%if(session.getAttribute("isMember")=="true"){%>
                                <button class="sections_link linking_button" id="ramyunreporting" name="ramyunName" type="button">
                                    <i class="fas fa-thumbs-down"></i>
                                    <div id="reporting_thumb" style="float: right;">신고</div>
                                </button>
                                <%}else{%>
                                <button class="sections_link linking_button" id="ramyunreporting" name="ramyunName" type="submit">
                                    <a href="./login"  style="vertical-align: unset; color:black" onclick="login_needed()">
                                        <i class="fas fa-thumbs-down"></i> 신고</button>   
                                    </a>
                                </button>
                                <%}%>
                                <!-- 추천  -->
                                <%if(session.getAttribute("isMember")=="true"){%>
                                <button class="sections_link linking_button" id="ramyunlikes" name="ramyunName" type="button">
                                    <i class="far fa-thumbs-up" ></i>
                                    <div id="recommand_thumb" style="float: right;">추천</div>
                                </button>
                                <%}else{%>
                                <button class="sections_link linking_button" id="ramyunlikes" name="ramyunName" type="submit" >
                                    <a href="./login"  style="vertical-align: unset; color:black" onclick="login_needed()">
                                        <i class="far fa-thumbs-up"></i> 추천</button>   
                                    </a>
                                <%}%>
                                <!-- 편집 -->
                                <%if(session.getAttribute("isMember")=="true"){%>
                                <div class="sections_link">
                                    <a href="./editramyun.do?name=${ramyun.brandNameKor}" style="vertical-align: unset; color:black">편집</a>
                                </div>
                                <%}else{%>
                                <div class="sections_link">
                                    <a href="./login" style="vertical-align: unset; color:black" onclick="login_needed()">편집</a>
                                </div>   
                                <%}%>
                                
                            </div>
                            
                        </div>
                        <div id="edited_time">
                        		최근 수정 시각 : 
                        		<fmt:parseDate value="${ramyun.updatedDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                			<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" value="${parsedDateTime}"/>
                            
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 라면 정보 | '${ramyun.brandNameKor}'
                        </div>
                        
                    </div>
                    
                    <div style="width: 100%; height: 40px;"></div>
                    <!--/////////////// 본문이 여기서 부터 시작된다 //////////////////-->
                    <div id="ramyun_info_div">
                        <div id="summary_infomation">
                            <!-- 이름 사진 회사가 들어가는 왼쪽 칸 -->
                            <div id="ramyun_picture_div">
                                <div id="brand_name_kr">
                                    <div style="width: 100%;height: 60%; line-height: 200%; font-size: 25px;">${ramyun.brandNameKor}</div>
                                    <div style="width: 100%;height: 40%; line-height: 100%;">(${ramyun.brandNameEng})</div>
                                    <!-- 안보여주고 값만보낼것 ajax포함 -->
                                    <input id="ramyunID" type="text" style="display: none;" value="${ramyun.brandNameKor}">
                                    
                                </div>
                                <div id="image">
                                    <img src="http://ramyun.wiki/resources/images/${ramyun.brandNameKor}.png" alt="라면 이미지" style="width: 100%; height: 100%;">
                                </div>
                                <div >
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">제조원</div>
                                        <div class="ramyun_info_value">${ramyun.corporateName}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">출시일</div>
                                        <div class="ramyun_info_value">${ramyun.developedDate}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">식품유형</div>
                                        <div class="ramyun_info_value">${ramyun.foodCategory}</div>
                                    </div>
                                </div>

                            </div>
                            <!-- 왼쪽 칸 정리 -->

                            <!-- 이외 기타 기본 정보가 들어가는 칸 오른쪽칸-->
                            <div id="ramyun_summary_info">
                                <div >
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key" style="width: 100%;">기본 정보</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">총 중량</div>
                                        <div class="ramyun_info_value">${ramyun.weight}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">열량</div>
                                        <div class="ramyun_info_value">${ramyun.calorie}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">스코빌 수치</div>
                                        <div class="ramyun_info_value">${ramyun.scovilleUnit}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">유통기한</div>
                                        <div class="ramyun_info_value">${ramyun.expirationDate}</div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key" style="width: 100%;">영양성분표</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">나트륨</div>
                                        <div class="ramyun_info_value">${ramyun.natrium}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">탄수화물</div>
                                        <div class="ramyun_info_value">${ramyun.carbohydrate}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">당류</div>
                                        <div class="ramyun_info_value">${ramyun.sugars}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">지방</div>
                                        <div class="ramyun_info_value">${ramyun.fat}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">트랜스지방</div>
                                        <div class="ramyun_info_value">${ramyun.transfat}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">포화지방</div>
                                        <div class="ramyun_info_value">${ramyun.saturatedfat}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">콜레스테롤</div>
                                        <div class="ramyun_info_value">${ramyun.cholesterol}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">단백질</div>
                                        <div class="ramyun_info_value">${ramyun.protein}</div>
                                    </div>
                                    <div class="ramyun_info_div">
                                        <div class="ramyun_info_key">칼슘</div>
                                        <div class="ramyun_info_value">${ramyun.calcium}</div>
                                    </div>
                                    
                                    </div>
                                </div>
                                

                            </div>
                            <!-- 오른쪽칸 끝 -->
                            
                        </div>
                        <!-- 아래 추가정보 -->
                        <div id="additional_info">
                                <!-- 여기까지가 늘어나는 가변성 리스트 시작 -->
                            <div  class="class_value_cover">
                                <div class="horizon_key"  style="height: 30px;">
                                    원재료명
                                </div>
                                <div class="horizon_value">
                                    <!-- 여기에 원재료명 리스트가 들어간다. -->
                                    ${ramyun.materialList}
                                </div>
                            </div>
                            <div id="related_list" class="class_value_cover">
                                <div class="horizon_key" style="height: 30px;">
                                    관련라면
                                </div>
                                <div class="horizon_value">
                                    <!-- 연관된 라면을 적는다. -->
                                    ${ramyun.relatedRamyun}
                                </div>
                            </div>
                            <div id="recipe_list" class="class_value_cover">
                                <div class="horizon_key" style="height: 30px;">
                                    조리법
                                </div>
                                <div class="horizon_value" >
                                    <!-- 레서피를 넣는다. -->
                                    ${ramyun.recipe}
                                </div>
                                
                            </div>

                            <div id="water_capacity_by_number" class="class_value_cover">
                                <div class="horizon_key" style="height: 30px;">
                                    갯수별 물량
                                </div>
                                <div class="horizon_value" >
                                    <!-- 갯수별 물량을 적는다.. -->
                                    ${ramyun.waterCapacityByNumber}
                                </div>
                                
                            </div>
                            <!-- 여기까지가 늘어나는 가변성 리스트 끝 -->
                            
                            <!-- 여기서 부터 가로 표 시작 -->
                            <div id="horizon_double_section">
                                <div class="horizon_double_key">바코드</div>
                                <div class="horizon_double_value">${ramyun.barcode}</div>
                                <div class="horizon_double_key">품목보고번호</div>
                                <div class="horizon_double_value">${ramyun.itemReportNumber}</div>
                            </div>
                            
                            <div id="horizon_double_section">
                                <div class="horizon_double_key">면 모양</div>
                                <div class="horizon_double_value">${ramyun.noodleShape}</div>
                                <div class="horizon_double_key">단종시기</div>
                                <div class="horizon_double_value">${ramyun.discontinuance}</div>
                            </div>
                            <!-- 여기서 가로 표 끝 -->
                            <!--세로위치 하나 추가  -->
                            <div id="soup_info" class="class_value_cover">
                                <div class="horizon_key" style="height: 30px;">
                                    스프 구성
                                </div>
                                <div class="horizon_value" >
                                    <!-- 스프구성을 적는다 -->
                                    
                                    ${ramyun.soupComposition}
                                </div>
                                <div class="horizon_key" style="height: 30px;">
                                    스프 위치
                                </div>
                                <div class="horizon_value" >
                                    <!-- 스프위치를 적는다.. -->
                                    ${ramyun.soupPosition}
                                </div>
                                
                            </div>
                            <!-- 세로 위치 하나 추가끝 -->
                        </div>
                        <!-- 추가 정보 끝 -->

                        <div id="user_made_section" >${ramyun.userEditedContents}</div>
                        
                    </div>

                    <!-- ///////////////본문의 여기서 끝난다. /////////////////////-->
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

    <%@ include file="./footer.jsp" %>
    

    
</body>
</html>