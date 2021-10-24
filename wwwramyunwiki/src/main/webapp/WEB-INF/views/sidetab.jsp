<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<div class="right_info_tab">
    <div id="recent_update_list" class="dotted">
        <div id="update_list">
            <br>
            <p style="text-align: center; margin: 0px; padding: 0px;">최근 업데이트</p> 
            <c:forEach var="aRamyun" items="${ramyunList}" >
                <div style="width: 100%;height: 0px; outline: 1px dotted rgba(0, 0, 0, 0.123);margin-top:10px;   text-overflow: ellipsis;"></div>

                <p style=" text-align: left;  display: table-cell;vertical-align: middle; padding-top: 3px;padding-bottom: 3px; padding-left: 10px">  
                <a href="./findramyun.do?name=${aRamyun.brandNameKor}" style="text-decoration: none">${aRamyun.brandNameKor}</a><br> 
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${aRamyun.updatedDate}
                
                </p>
            </c:forEach>
        </div>
        
    </div>
    <div id="empty_space">

    </div>
    <div id="todays_random" class="dotted">
        <div id="random_list">
            <a href="./ramyun">오늘의 라면</a><br>
            <a href="./userinfo.html">내 정보</a>
        </div>
    </div>
    
    
</div> 