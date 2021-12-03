<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 문자열 길이 체크 태그 -->
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>라면위키 관리자모드</title>
</head>
<style>
    td{outline: 1px rgba(0, 0, 0, 0.3) solid;}
    a{text-decoration: none;}
</style>
<body>
    <!-- 맨위 태그 접근 -->
    <form action="/admin/management" method="get">
        <select name="selectedtag" id="selectedtag">
            <option value="none">${currunttab}</option>
            <option>라면정보</option>
            <option>라면로그</option>
            <option>공장정보</option>
            <option>영양성분</option>
            <option>멤버관리</option>

        </select>
        <button>이동</button>
        <br>
        <span style="color: blue; font:bold;">-${currunttab} 탭 실행중</span>
    </form>



    <!-- 라면정보 데이터 뿌리기 -->
    <c:if test="${currunttab eq '라면정보'}">
        <!-- [이전]버튼 -->
        <c:if test="${pagenation.prev}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range-2)*pagenation.rangeSize+1}&range=${pagenation.range-1}">[이전]</a></c:if>
        <!-- [1]버튼 -->
        <c:forEach var="index" begin ="${pagenation.startPage}" end="${pagenation.endPage}">
            <a href="/admin/management?selectedtag=${currunttab}&page=${index}&range=${pagenation.range}">[${index}]</a>
        </c:forEach>
        <!-- [다음]버튼 -->
        <c:if test="${pagenation.next}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range*pagenation.rangeSize)+1}&range=${pagenation.range+1}">[다음]</a></c:if>

        <p>
            <table>
                <th>라면이름</th>
                <th>날짜</th>
                <th>삭제</th>
                <c:forEach var="ramyun" items="${ramyunList}" >
                    <tr>
                        <td>
                            <span>${ramyun.brandNameKor}</span>
                        </td>
                        <td>
                            <span>${ramyun.updatedDate}</span>
                        </td>
                        <td>
                            <span><a href="/admin/deleteramyun?name=${ramyun.brandNameKor}">삭제</a></span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </p>
    </c:if>


    <!-- 라면로그 데이터 뿌리기 -->
    <c:if test="${currunttab eq '라면로그'}">
        <!-- [이전]버튼 -->
        <c:if test="${pagenation.prev}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range-2)*pagenation.rangeSize+1}&range=${pagenation.range-1}">[이전]</a></c:if>
        <!-- [1]버튼 -->
        <c:forEach var="index" begin ="${pagenation.startPage}" end="${pagenation.endPage}">
            <a href="/admin/management?selectedtag=${currunttab}&page=${index}&range=${pagenation.range}">[${index}]</a>
        </c:forEach>
        <!-- [다음]버튼 -->
        <c:if test="${pagenation.next}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range*pagenation.rangeSize)+1}&range=${pagenation.range+1}">[다음]</a></c:if>

        <p>
            <table>
                <th>라면로그이름</th>
                <th>로그번호</th>
                <th>작성자</th>
                <th>삭제</th>
                <c:forEach var="ramyunHistory" items="${ramyunHistoryList}" >
    
                    <tr>
                        <td>
                            <span>${ramyunHistory.brandNameKor}</span>
                        </td>
                        <td>
                            <span>${ramyunHistory.id}</span>
                        </td>
                        <td>
                            <span>${ramyunHistory.writer}</span>
                        </td>
                        <td>
                            <span><a href="/admin/deleteramyunhistory?id=${ramyunHistory.id}">삭제</a></span>
                        </td>
                    </tr>
                    
                </c:forEach>
                </table>
        </p>
    </c:if>

    <!-- 공장정보 데이터 뿌리기 -->
    <c:if test="${currunttab eq '공장정보'}">
        <!-- [이전]버튼 -->
        <c:if test="${pagenation.prev}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range-2)*pagenation.rangeSize+1}&range=${pagenation.range-1}">[이전]</a></c:if>
        <!-- [1]버튼 -->
        <c:forEach var="index" begin ="${pagenation.startPage}" end="${pagenation.endPage}">
            <a href="/admin/management?selectedtag=${currunttab}&page=${index}&range=${pagenation.range}">[${index}]</a>
        </c:forEach>
        <!-- [다음]버튼 -->
        <c:if test="${pagenation.next}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range*pagenation.rangeSize)+1}&range=${pagenation.range+1}">[다음]</a></c:if>

        <p>
            <table>
                <th>공장이름</th>
                <th>날짜</th>
                <th>삭제</th>
                <c:forEach var="manufactory" items="${manufactoryList}" >
                    <tr>
                        <td>
                            <span>${manufactory.factoryName}</span>
                        </td>
                        <td>
                            <span>${manufactory.updatedDate}</span>
                        </td>
                        <td>
                            <span><a href="/admin/deletemanufactory?name=${manufactory.factoryName}">삭제</a></span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </p>
    </c:if>

    <!-- 영양성분 데이터 뿌리기 -->
    <c:if test="${currunttab eq '영양성분'}">
        <!-- [이전]버튼 -->
        <c:if test="${pagenation.prev}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range-2)*pagenation.rangeSize+1}&range=${pagenation.range-1}">[이전]</a></c:if>
        <!-- [1]버튼 -->
        <c:forEach var="index" begin ="${pagenation.startPage}" end="${pagenation.endPage}">
            <a href="/admin/management?selectedtag=${currunttab}&page=${index}&range=${pagenation.range}">[${index}]</a>
        </c:forEach>
        <!-- [다음]버튼 -->
        <c:if test="${pagenation.next}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range*pagenation.rangeSize)+1}&range=${pagenation.range+1}">[다음]</a></c:if>

        <p>
            <table>
                <th>영양성분이름</th>
                <th>날짜</th>
                <th>삭제</th>
                <c:forEach var="ingredient" items="${ingredientList}" >
    
                    <tr>
                        <td>
                            <span>${ingredient.name}</span>
                        </td>
                        <td>
                            <span>${ingredient.updatedDate}</span>
                        </td>
                        <td>
                            <span><a href="/admin/deleteingredient?name=${ingredient.name}">삭제</a></span>
                        </td>
                    </tr>
                    
                </c:forEach>
            </table>
        </p>
    </c:if>

    <!-- 멤버관리 데이터 뿌리기 -->
    <c:if test="${currunttab eq '멤버관리'}">
        <!-- [이전]버튼 -->
        <c:if test="${pagenation.prev}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range-2)*pagenation.rangeSize+1}&range=${pagenation.range-1}">[이전]</a></c:if>
        <!-- [1]버튼 -->
        <c:forEach var="index" begin ="${pagenation.startPage}" end="${pagenation.endPage}">
            <a href="/admin/management?selectedtag=${currunttab}&page=${index}&range=${pagenation.range}">[${index}]</a>
        </c:forEach>
        <!-- [다음]버튼 -->
        <c:if test="${pagenation.next}"><a href="/admin/management?selectedtag=${currunttab}&page=${(pagenation.range*pagenation.rangeSize)+1}&range=${pagenation.range+1}">[다음]</a></c:if>

        <p>
            <table>
                <th>회원번호</th>
                <th>회원아이디</th>
                <th>닉네임</th>
                <th>가입일</th>
                <th>삭제</th>
                <c:forEach var="member" items="${memberList}" >
    
                    <tr>
                        <td>
                            <span>${member.memberNumber}</span>
                        </td>
                        <td>
                            <span>${member.memberId}</span>
                        </td>
                        <td>
                            <span>${member.nickname}</span>
                        </td>
                        <td>
                            <span>${member.joinDate}</span>
                        </td>
                        <td>
                            <span><a href="/admin/deletemember?number=${member.memberNumber}">삭제</a></span>
                        </td>
                    </tr>
                    
                </c:forEach>
    
    
    
                </table>
        </p>
    </c:if>



</body>
</html>