<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>
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
                <a href="./recentupdating.html" style="color: white;">최근변경</a>
            </div>
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./nutrient.html" style="color: white;">영양성분</a>
            </div>
            
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./factory.html" style="color: white;">제조공장</a>
            </div>
            
        </div>
        <div class="inner_top_pannel_link">
            <div class="tab_linking">
                <a href="./tag.html" style="color: white;">태그검색</a>
            </div>
            
        </div>
        

        <div class="search_box">
            <div id="inner_search_box">
                <div id="random_icon" >
                    <i class="fas fa-random"></i>
                </div>
                <form action="./findramyun.do" method="post" style="font-family: FontAwesome;">
                    <input id="search_box_textarea"  name="searchBoxInput" placeholder="Search" >
                    
                    
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
                                <a style="color: black;"><%=session.getAttribute("memberId")%> 님,</a>
                                <%
                            }
                        %>
                        
                        
                    </div>
                    <%
                        if(session.getAttribute("isMember")=="true"){
                            %>
                            <div style="height: 10%; width: 80%; margin-left: 10%;">
                                <a id="user_info_linking" href="./userinfo" >설정</a>
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
                            <div style="height: 10%; width: 80%; margin-left: 10%; text-align: left;">
                                <a href="/logout.do" style="color: black;">로그아웃</a>
                            </div>
                            <%
                        }
                        else{%>
                            <div style="height: 10%; width: 80%; margin-left: 10%;color: rgba(0, 0, 0, 0.075);">
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


<!-- 상단 패널 끝 -->