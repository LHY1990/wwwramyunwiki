<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<div class="right_info_tab">
    <div id="recent_update_list" class="dotted">
        <div id="update_list">
            <br>
            <p style="text-align: center; margin: 0px; padding: 0px;">최근 업데이트</p> 
            <c:forEach var="aRamyun" items="${ramyunList}" >
                <div style="width: 100%;height: 0px; outline: 1px dotted rgb(0, 168, 87, 0.2);margin-top:10px;   text-overflow: ellipsis;"></div>

                <p style=" text-align: left;  display: table-cell;vertical-align: middle; padding-top: 3px;padding-bottom: 3px; padding-left: 10px">  
	                <a id="no_a_deco" href="/findramyun.do?name=${aRamyun.brandNameKor}" style="text-decoration: none">${aRamyun.brandNameKor}</a><br> 
	                <fmt:parseDate value="${aRamyun.updatedDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	                <fmt:formatDate pattern="yyyy. MM. dd  HH : mm : ss" value="${parsedDateTime}"/>
                
                </p>
            </c:forEach>
        </div>
        
    </div>
    <div id="empty_space">

    </div>
    <div id="todays_random" class="dotted">
        <!-- 여기에  bxslider가 적용되어있다.-->
        <ul id="random_list" class="bxslider" >
            <c:forEach var="randomRamyunImage" items="${randomRamyunImageList}" >
                <li>
                    <a href="/findramyun.do?name=${randomRamyunImage.brandNameKor}" >
                        <img src="http://ramyun.wiki/resources/images/${randomRamyunImage.brandNameKor}.png" alt="${randomRamyunImage.brandNameKor}" style="width: 280px;height: 300px;" title="${randomRamyunImage.brandNameKor}">
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    
</div> 

