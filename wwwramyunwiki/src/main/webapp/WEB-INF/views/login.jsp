<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/login.css">

    <script type="text/javascript" src="../../resources/javascript/login.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <title>라면위키:로그인 -라면위키</title>

</head>
<body>
    <!-- 상단 패널 시작 -->
    <div class="top_pannel" id="header">
        
        
        <div class="top_pannel_link" >
            <div class="inner_top_pannel_link" id="logo_icon">
                <!-- 대표아이콘 -->
                <i class="fas fa-book"></i>
            </div>
            <div class="inner_top_pannel_link" id="title">
                <!-- 대표 이름 -->
                <div>
                    <a href="./home" >라면위키</a> 
                </div>
                   
            </div>
            <div class="inner_top_pannel_link" >
                <div class="tab_linking">
                    <a href="./recentupdating">최근변경</a>
                </div>
            </div>
            <div class="inner_top_pannel_link">
                <div class="tab_linking">
                    <a href="./nutrient">영양성분</a>
                </div>
                
            </div>
            <div class="inner_top_pannel_link">
                <div class="tab_linking">
                    <a href="./factory">제조공장</a>
                </div>
                
            </div>
            <div class="inner_top_pannel_link">
                <div class="tab_linking">
                    <a href="./tag">태그검색</a>
                </div>
                
            </div>

            <div class="search_box">
                <div id="inner_search_box">
                    <div id="random_icon" >
                        <i class="fas fa-random"></i>
                    </div>
                    <textarea id="search_box_textarea"  name="searchBoxInput" id="" cols="20" rows="1" placeholder="Search"></textarea>
                    <div id="search_icon" >

                        <i class="fas fa-search"></i>
                    </div>
                    <div id="member_icon" onclick="popup()">
    
                        <!-- 회원정보 아이콘 -->
                            <!-- <i class="fas fa-user-alt"></i> -->
                            <i class="far fa-user"></i>
                        
                        
                    </div>

                    <!-- 팝업창 로그인 시작 -->
                    <div id="login_popup">
                        <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;">
                            로그인
                        </div>
                        <div style="height: 10%; width: 80%; margin-left: 10%;">
                            설정
                        </div>
                        <!-- 분할 -->
                        <div style="width: 84%;margin-left: 7%; outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                        <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;">
                            내가 기여한 라면
                        </div>
                        
                        <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;">
                            0000 개
                        </div>
                        <!-- 분할 -->
                        <div style="width: 84%; margin-left: 7%;outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                        <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;">
                            받은 추천 수
                        </div>
                        <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;">
                            0000 개
                        </div>
                    </div>
                    <!-- 팝업창 로그인 끝 -->

                </div>
                
            </div>
            
               

        </div>
    </div>

 
    <!-- 상단 패널 끝 -->
    

    <!-- 홈 프레임 시작(컨텐츠 프레임 + 우측프레임) -->
    <div class="home_frame">
        <div class="inner_frame">
            
            <div class="contents_frame dotted">
                <div id="contents_margin">
                <!-- 모든컨텐츠는 여기서 시작해서 -->
                    <div id="information_tab">
                        <div id="section">
                            라면위키 : 로그인
                        </div>
                        <div id="section_linkings">
                            <div id="section_linkings_frame">
                                <div class="sections_link"></div>
                                <div class="sections_link"></div>
                                <div class="sections_link"></div>
                                
                            </div>
                            
                        </div>
   
                        <div style="height: 100px;">

                        </div>
                        
                        <div id="sorting_category">
                            분류 : LOG IN
                        </div>
                        <!-- 로그인 시작 -->

                        <div id="login_div">
                            
                            <form action="./home" method="post">
                                <div style="margin-top : 50px;"></div>
                                <div id="type_id">아이디</div>
                                <input id="type_id" type="text" name="memberId"><br><br>
                                <div id="type_password">비밀번호</div>
                                <input id="type_password" type="password" name="memberPassword">
                                <div id="finding_id_password">아이디/비밀번호 찾기</div><br>
                                <div id="join_member"><a href="./rules">회원가입</a></div>
                                <div id="member_login" ><input type="submit" value="로그인" id="member_login" style="background-color: rgb(113, 113, 223, 0.3);"></div>

                            </form>


                        </div>
                        <!-- 로그인끝 -->
                        <div style="width: 100%; height: 5px;">

                        </div>

                        <div style="width: 100%; outline: rgb(122, 103, 129) 1px dotted;"></div>


                        
                        
                        
                        
                    </div>
                       


                <!-- 여기서 끝나야한다 -->
                </div>

                
                
            </div>   
            <!-- 우측 탭 시작 -->
            <div class="right_info_tab">
                <div id="recent_update_list" class="dotted">
                    <div id="update_list">
                        최근 업데이트 
                    </div>
                    
                </div>
                <div id="empty_space">

                </div>
                <div id="todays_random" class="dotted">
                    <div id="random_list">
                        오늘의 라면
                    </div>
                </div>
                
                
            </div> 
            <!-- 우측 탭 끝 -->
        </div>
        
        
    </div>

    <div id="footer">
        <div id="footer_contents">
            <br>
            <br>
            모든 라면위키의 저작물은 <a href="https://creativecommons.org/licenses/by-nc-sa/2.0/kr/">CC BY-NC-SA 2.0</a>  규칙에 따라 이용 할 수 있습니다. 
            저작권이 표기된 이미지 및 문서, 링크등은 제외됩니다. <br>
            또한 각 문서의 저작권은 작성자에게 있으며, 모든 작성자는 기여한 부분의 저작권을 갖습니다. <br><br>
    
             라면위키는 백과 사전이 아니며 과학적이지 않거나 편향적이거나 잘못된 설명이 있을 수 있습니다. <br>
            라면위키 서비스는 한국의 모든 라면을 좋아하는 사람들에게 각각의 지식을 공유하기 위해 제작되었습니다. <br>
            이용하는 모든 사용자에게 즐거운 시간이 되길 바랍니다.
            <br><br><br>
            <div style="text-align: left; font-size: 9px;">라면위키는 비상업적인 목적으로 운영됩니다.</div>
        </div>
        

    </div>
    

    
</body>
</html>