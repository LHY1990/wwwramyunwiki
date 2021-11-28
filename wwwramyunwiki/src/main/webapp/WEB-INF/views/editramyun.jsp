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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/edit_ramyun.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidetab.css">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/home.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%@ include file="./bxslider.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:라면편집 -라면위키</title>

</head>
<body>
    <%@ include file="./header.jsp" %>
    <%@ include file="./instruct.jsp" %>
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : ${ramyun.brandNameKor} 편집
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <!-- <div class="sections_link">역사</div> -->
                                
                            </div>
                            
                        </div>
                        <div id="edited_time">
                            <!-- 최근 수정 시각 : ${ramyun.updatedDate} -->
                        </div>
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : 라면 수정 | '${ramyun.brandNameKor}'
                        </div>
                        <a href="#submit_button" style="float : right; text-decoration: none; font-size: 12px; color: black;">[수정 버튼으로 가기]</a>
                        
                    </div>
                    
                    <div style="width: 100%; height: 40px;">

                    </div>
                    <!--/////////////// 본문편집 이 여기서 부터 시작된다 //////////////////onsubmit="return false"-->
                    <form action="./edit.do" method="post" enctype="multipart/form-data" >
                        <div id="ramyun_info_div">
                            <div id="summary_infomation">
                                <!-- 이름 사진 회사가 들어가는 왼쪽 칸 -->
                                <div id="ramyun_picture_div">
                                    <div id="brand_name_kr">
                                        <div style="width: 100%;height: 60%; line-height: 200%; font-size: 25px;">${ramyun.brandNameKor}</div>
                                        <input type="text"  name="brandNameKor" value="${ramyun.brandNameKor}" style="display: none;">
                                        <div style="width: 100%;height: 40%; line-height: 100%;">
                                            <input type="text" maxlength="28" placeholder="영문 브랜드 명" name="brandNameEng" value="${ramyun.brandNameEng}">
                                        </div>
    
                                    </div>
                                    <div id="image">
                                        
                                        <input type="file" name="uploadedimage" style="margin-top:10px; height: 95%; width: 95%;"></input>
                                        
                                    </div>
                                    <div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">제조원</div>
                                            <div class="ramyun_info_value"><input type="text" maxlength="30" placeholder="ex) 농심" name="corporateName" value="${ramyun.corporateName}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">출시일</div>
                                            <div class="ramyun_info_value"><input type="text" maxlength="30" placeholder="ex) 1996년 6월" name="developedDate" value="${ramyun.developedDate}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">식품유형</div>
                                            <div class="ramyun_info_value"><input type="text" maxlength="20" placeholder="ex) 유탕면" name="foodCategory" value="${ramyun.foodCategory}"></div>
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
                                            <div class="ramyun_info_value"><input type="text" maxlength="30" placeholder="ex) 140(g)" name="weight" value="${ramyun.weight}" ></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">열량</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30" placeholder="ex) 610(kcal)" name="calorie" value="${ramyun.calorie}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">스코빌 수치</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30" placeholder="ex) 500(SHU)" name="scovilleUnit" value="${ramyun.scovilleUnit}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">유통기한</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="10"  placeholder="ex) 12개월" name="expirationDate" value="${ramyun.expirationDate}"></div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key" style="width: 100%;">영양성분표</div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">나트륨</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30"  placeholder="ex) 1,100mg 55%" name="natrium" value="${ramyun.natrium}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">탄수화물</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30"  placeholder="ex) 96g 30%" name="carbohydrate" value="${ramyun.carbohydrate}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">당류</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="20" placeholder="ex) 7g 7%" name="sugars" value="${ramyun.sugars}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">지방</div>
                                            <div class="ramyun_info_value"><input type="text" maxlength="20"  placeholder="ex) 20g 37%" name="fat" value="${ramyun.fat}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">트랜스지방</div>
                                            <div class="ramyun_info_value"><input type="text" maxlength="20"  placeholder="ex) 0g" name="transfat" value="${ramyun.transfat}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">포화지방</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30" placeholder="ex) 8g 53%" name="saturatedfat" value="${ramyun.saturatedfat}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">콜레스테롤</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="30" placeholder="ex) 5 mg 미만" name="cholesterol" value="${ramyun.cholesterol}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">단백질</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="20" placeholder="ex) 11g 20%" name="protein" value="${ramyun.protein}"></div>
                                        </div>
                                        <div class="ramyun_info_div">
                                            <div class="ramyun_info_key">칼슘</div>
                                            <div class="ramyun_info_value"><input type="text"  maxlength="20" placeholder="ex) 162mg 23%" name="calcium" value="${ramyun.calcium}"></div>
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
                                    <div class="horizon_key" style="height: 30px;">
                                        원재료명
                                    </div>
                                    <div class="horizon_value">
                                        <!-- 여기에 원재료명 리스트가 들어간다. -->
                                        <textarea name="materialList" class="textareaFix" placeholder="ex) 면/소맥분(밀 미국산, 호주산), 감자전분(독일산), 팜유(말레이시아 산), 변성전분 ..." >${ramyun.materialList}</textarea> 
                                    </div>
                                </div>
                                <div id="related_list" class="class_value_cover">
                                    <div class="horizon_key" style="height: 30px;">
                                        관련라면
                                    </div>
                                    <div class="horizon_value">
                                        <!-- 연관된 라면을 적는다. -->
                                        <textarea name="relatedRamyun" maxlength="100" class="textareaFix" placeholder="ex) 자매품을 적어주세요">${ramyun.relatedRamyun}</textarea>
                                    </div>
                                </div>
                                <div id="recipe_list" class="class_value_cover">
                                    <div class="horizon_key" style="height: 30px;">
                                        조리법
                                    </div>
                                    <div class="horizon_value" >
                                        <!-- 레서피를 넣는다. -->
                                        <textarea name="recipe" class="textareaFix" placeholder="ex) 조리 방법을 적어주세요">${ramyun.recipe}</textarea>
                                    </div>
                                    
                                </div>

                                <div id="water_capacity_by_number" class="class_value_cover">
                                    <div class="horizon_key" style="height: 30px;">
                                        갯수별 물량
                                    </div>
                                    <div class="horizon_value" >
                                        <!-- 갯수별 물량을 적는다.. -->
                                        <textarea name="waterCapacityByNumber" class="textareaFix" placeholder="ex) 갯수별 물 양을 적어주세요"  >${ramyun.waterCapacityByNumber}</textarea>
                                    </div>
                                    
                                </div>
                                <!-- 여기까지가 늘어나는 가변성 리스트 끝 -->
                                
                                <!-- 여기서 부터 가로 표 시작 -->
                                <div id="horizon_double_section">
                                    <div class="horizon_double_key">바코드</div>
                                    <div class="horizon_double_value"><input type="text"  maxlength="15" placeholder="ex) 8 801043 015226" name="barcode" value="${ramyun.barcode}"></div>
                                    <div class="horizon_double_key">품목보고번호</div>
                                    <div class="horizon_double_value"><input type="text"  maxlength="20" placeholder="ex) 19760342001163" name="itemReportNumber" value="${ramyun.itemReportNumber}"></div>
                                </div>
                                
                                <div id="horizon_double_section">
                                    <div class="horizon_double_key">면 모양</div>
                                    <div class="horizon_double_value"><input type="text"  maxlength="10" placeholder="ex) 원형" name="noodleShape" value="${ramyun.noodleShape}"></div>
                                    <div class="horizon_double_key">단종여부</div>
                                    <div class="horizon_double_value"><input type="text"  maxlength="10" placeholder="ex) 현재생산중( 또는 단종됌)" name="discontinuance" value="${ramyun.discontinuance}"></div>
                                </div>
                                <!-- 여기서 가로 표 끝 -->
                                <!--세로위치 하나 추가  -->
                                <div id="soup_info" class="class_value_cover">
                                    <div class="horizon_key" style="height: 30px;">
                                        스프 구성
                                    </div>
                                    <div class="horizon_value" >
                                        <!-- 스프구성을 적는다 -->
                                        <input type="text"  maxlength="30" placeholder="ex) 가루형 스프 1개, 건더기 스프 1개, 올리브유" name="soupComposition" value="${ramyun.soupComposition}" style="width: 99%;">
                                    </div>
                                    <div class="horizon_key" style="height: 30px;">
                                        스프 위치
                                    </div>
                                    <div class="horizon_value" >
                                        <!-- 스프위치를 적는다.. -->
                                        <input type="text"  maxlength="60" placeholder="ex) 면 위쪽(로고쪽) 또는 면 아래쪽(뒷면)" name="soupPosition" value="${ramyun.soupPosition}" style="width: 99%;">
                                    </div>
                                    
                                </div>
                                <!-- 세로 위치 하나 추가끝 -->
                            </div>
                            <!-- 추가 정보 끝 -->

                            <div id="user_made_section" style="margin-top: 10px;">
                                <span id="error_alerting_span_A" style="color: red;"></span><br>
                                <!--작성된 글 -->
                                <textarea name="userEditedContents" id="user_edited_contents" >${ramyun.userEditedContents}</textarea>
                            </div>
                            <span id="error_alerting_span_B" style="color: red; line-height: 50px;"></span>
                            <!-- 서브밋 버튼이다. -->
                            <input id="submit_button" type="submit" name="${ramyun.brandNameKor}" style="width: 100%;height: 30px;margin-top: 20px;color: white;background-color: rgba(0, 0, 0, 0.3);" value="수정 하기"></input>
                            
                        </div>
                    </form>
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