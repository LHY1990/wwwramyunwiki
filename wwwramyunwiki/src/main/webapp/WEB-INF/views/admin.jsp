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
    <details>
        <summary>라면 데이터 베이스</summary>
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
                        <span><a href="/deleteramyun?name=${ramyun.brandNameKor}">삭제</a></span>
                    </td>
                </tr>
                
            </c:forEach>



            </table>
        </p>
    </details>
    <details>
        <summary>라면로그 데이터 베이스</summary>
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
                        <span><a href="/deleteramyunhistory?id=${ramyunHistory.id}">삭제</a></span>
                    </td>
                </tr>
                
            </c:forEach>



            </table>
        </p>
    </details>
    <details>
        <summary>영양성분 데이터 베이스</summary>
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
                        <span><a href="/deleteingredient?name=${ingredient.name}">삭제</a></span>
                    </td>
                </tr>
                
            </c:forEach>



            </table>
        </p>
    </details>

    <details>
        <summary>제조공장 데이터 베이스</summary>
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
                        <span><a href="/deletemanufactory?name=${manufactory.factoryName}">삭제</a></span>
                    </td>
                </tr>
                
            </c:forEach>



            </table>
        </p>
    </details>
    <details>
        <summary>회원 데이터 베이스</summary>
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
                        <span><a href="./deletemember?number=${member.memberNumber}">삭제</a></span>
                    </td>
                </tr>
                
            </c:forEach>



            </table>
        </p>
    </details>




</body>
</html>