<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!-- 상단 패널 시작 -->
<div class="top_pannel" id="header" >
        
        
    <div class="top_pannel_link" >
        <div class="inner_top_pannel_link"  id="logo_icon">
            <!-- 대표아이콘 -->
            <i class="fas fa-book" ></i>
        </div>
        <div class="inner_top_pannel_link" id="title">
            <!-- 대표 이름 -->
            <div>
                <a href="./home" >라면위키</a> 
            </div>
               
        </div>
        <div class="inner_top_pannel_link" >
            <div class="tab_linking">
                <a href="./recentupdating" style="color: white;" >최근변경</a>
            </div>
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./nutrient" style="color: white;">영양성분</a>
            </div>
            
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./manufactory" style="color: white;">제조공장</a>
            </div>
            
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./tag?page=1" style="color: white;">태그검색</a>
            </div>
            
        </div>
        

        <div class="search_box">
            <div id="inner_search_box">
                <div id="random_icon" >
                    <i class="fas fa-random"></i>
                </div>
                <form action="./findramyun.do" method="post" style="font-family: FontAwesome;">
                    <!-- 입력창 -->
                    <!-- <div id="recommand" style="color: black;">
                        <p id="auto0" class="autos"></p>
                        <p id="auto1" class="autos"></p>
                        <p id="auto2" class="autos"></p>
                        <p id="auto3" class="autos"></p>
                        <p id="auto4" class="autos"></p>
                        
                    </div> -->
                    
                    


                    <input id="search_box_textarea" list="search_list_auto" name="searchBoxInput" placeholder="Search" autocomplete="off">
                    <!-- 입력창 -->
                    <datalist id="search_list_auto">
                        <option id="auto0"></option>
                        <option id="auto1"></option>
                        <option id="auto2" ></option>
                        <option id="auto3" ></option>
                        <option id="auto4" ></option>
                    </datalist>
                    
                </form>
                
                <div id="member_icon" onclick="popup()">

                    <!-- 회원정보 아이콘 -->
                        <i class="fas fa-user-alt"></i>
                    
                    
                </div>

                <!-- 팝업창 로그인 시작 -->
                <div id="login_popup">
                    <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;">
                        <%
                            if(session.getAttribute("memberId")==null){ 
                                %>
                                <a href="./login" style="color: black;">로그인</a>
                                <%
                            }
                            else{
                                %>
                                <a style="color: black;"><%=session.getAttribute("memberNickname")%> 님,</a>
                                <%
                            }
                        %>
                        
                        <!-- 위에 아이디 아니고 닉네임으로 변경할것 -->
                    </div>
                    <%
                        if(session.getAttribute("isMember")=="true"){
                            %>
                            <div style="height: 10%; width: 80%; margin-left: 10%;color: black;">
                                <a id="user_info_linking" href="./userinfo" style="color: black;" >설정</a>
                            </div>
                            <!-- 분할 -->
                            <div style="width: 84%;margin-left: 7%; outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;color: black;">
                                내가 기여한 라면
                            </div>
                            
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;color: black;">
                                0000 개
                            </div>
                            <!-- 분할 -->
                            <div style="width: 84%; margin-left: 7%;outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;color: black;">
                                받은 추천 수
                            </div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;color: black;">
                                0000 개
                            </div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: left;color: black;">
                                <a href="/logout.do" style="color: black;">로그아웃</a>
                            </div>
                            <%
                        }
                        else{%>
                            <div style="height: 10%; width: 80%; margin-left: 10%;color: rgba(0, 0, 0, 0.075); ">
                                설정
                            </div>
                            <!-- 분할 -->
                            <div style="width: 84%;margin-left: 7%; outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;color: rgba(0, 0, 0, 0.075);">
                                내가 기여한 라면
                            </div>
                            
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;color: rgba(0, 0, 0, 0.075);">
                                0 개
                            </div>
                            <!-- 분할 -->
                            <div style="width: 84%; margin-left: 7%;outline: rgb(89, 63, 114, 0.5) dotted 1px;"></div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; margin-top: 10px;color: rgba(0, 0, 0, 0.075);">
                                받은 추천 수
                            </div>
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: right;color: rgba(0, 0, 0, 0.075);">
                                0 개
                            </div>

                            
                        
                        
                        
                        <%}
                    
                    %>

                    
                </div>
                <!-- 팝업창 로그인 끝 -->

            </div>
            
        </div>
        
           

    </div>
</div>
<style>
    /* 이건 그냥 로컬로 구현함 */
    #recommand{
    position: absolute; 
    margin-left: 24px;
    margin-top: 25px; 
    height: 110px; 
    width: 236px;
    text-align: left; 
    background-color: rgb(255, 255, 255); 
    color: black;
    display: none;
    }
    .autos{
        margin: 0px;
        padding: 0px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>

<!-- 상단 패널 끝 -->