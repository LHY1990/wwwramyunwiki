<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>


<details style="position: fixed; margin-top: 50px; background-color: white; font-size: 11px;outline: rgb(0, 168, 87) dotted 1px ;">
    <summary> 라면 위키 작성법 </summary>
        <p>
            <strong style="color: red;"># 각 게시물의 본문에만 적용됩니다.</strong>
            <table>
            <th  style="outline: rgb(0, 168, 87) dotted 1px ;">작성방법</th>
            <th  style="outline: rgb(0, 168, 87) dotted 1px ;">출력</th>
            <th  style="outline: rgb(0, 168, 87) dotted 1px ;">설명</th>

                <tr>
                    <td>
                        <span>(줄넘김) 또는 (줄바꿈)</span>
                    </td>
                    <td>
                        <span>다음줄</span>
                    </td>
                    <td>
                        <span>줄바꿈을 합니다. 기본적으로 엔터로 줄바꿈이 됩니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(글자색)[#ff1111]라면(/글자색)</span>
                    </td>
                    <td>
                        <span><span style="color: #ff1111;">라면</span></span>
                    </td>
                    <td>
                        <span>글자색을 변경합니다.#RRGGBB 의 헥스코드를 따릅니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(배경색)[#11ff11]라면(/배경색)</span>
                    </td>
                    <td>
                        <span><span style="background-color: #11ff11;">라면</span></span>
                    </td>
                    <td>
                        <span>배경색을 변경합니다.#RRGGBB 의 헥스코드를 따릅니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(굵은글자)라면(/굵은글자)</span>
                    </td>
                    <td>
                        <span><strong>라면</strong></span>
                    </td>
                    <td>
                        <span>글자를 두껍게 합니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(큰글자)라면(/큰글자)</span>
                    </td>
                    <td>
                        <span><b>라면</b></span>
                    </td>
                    <td>
                        <span>글자를 크게 합니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(큰글자1)라면(/큰글자1)</span>
                    </td>
                    <td>
                        <span> <h1 style="line-height: 0px;">라면</h1></span>
                    </td>
                    <td>
                        <span>글자를 크게 합니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(큰글자2)라면(/큰글자2)</span>
                    </td>
                    <td>
                        <span><h2 style="line-height: 0px;">라면</h2></span>
                    </td>
                    <td>
                        <span>글자를 크게 합니다. </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(큰글자3)라면(/큰글자3)</span>
                    </td>
                    <td>
                        <span><h3 style="line-height: 0px;">라면</h3></span>
                    </td>
                    <td>
                        <span>글자를 크게 합니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(큰글자4)라면(/큰글자4)</span>
                    </td>
                    <td>
                        <span><h4 style="line-height: 0px;">라면</h4></span>
                    </td>
                    <td>
                        <span>글자를 크게 합니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(작은글자)라면(/작은글자)</span>
                    </td>
                    <td>
                        <span><small>라면</small></span>
                    </td>
                    <td>
                        <span>글자를 작게 합니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(밑줄)라면(/밑줄)</span>
                    </td>
                    <td>
                        <span><u>라면</u></span>
                    </td>
                    <td>
                        <span>밑줄을 긋습니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(기울임)라면(/기울임)</span>
                    </td>
                    <td>
                        <span><i>라면</i></span>
                    </td>
                    <td>
                        <span>글자를 기울입니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(취소선)라면(/취소선)</span>
                    </td>
                    <td>
                        <span><strike>라면</strike></span>
                    </td>
                    <td>
                        <span>취소선을 넣습니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(윗글자)라면(/윗글자)</span>
                    </td>
                    <td>
                        <span><sup>라면</sup></span>
                    </td>
                    <td>
                        <span>작은 윗글자로 만듭니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(아래글자)라면(/아래글자)</span>
                    </td>
                    <td>
                        <span><sub>라면</sub></span>
                    </td>
                    <td>
                        <span>작은 아래글자로 만듭니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(링크)https://www.naver.com, 네이버(/링크)</span>
                    </td>
                    <td>
                        <span>><a href="https://www.naver.com">네이버</a></span>
                    </td>
                    <td>
                        <span>링크를 연결합니다. 띄어쓰기와 경로명에 주의합니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>라면(각주)[1](/각주)</span>
                    </td>
                    <td>
                        <span>라면<a href="#comment1"><sup>[1]</sup></a></span>
                    </td>
                    <td>
                        <span>각주를 만듭니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(각주내용)[1]내용(/각주내용)</span>
                    </td>
                    <td>
                        <span><span id="comment1">[1]내용</span></span>
                    </td>
                    <td>
                        <span>[]안의 값과 대응하는 각주를 연결합니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(구분선)</span>
                    </td>
                    <td>
                        <span><div style="outline: 1px dotted rgba(128, 128, 128, 0.527); width: 30px; margin-left: 15%;margin-top: 22%;"></div></span>
                    </td>
                    <td>
                        <span>구분선을 긋습니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(외곽선)(/외곽선)</span>
                    </td>
                    <td>
                        <span><div style="outline: 1px dotted rgba(128, 128, 128,1); margin:5px; padding:5px;width:fit-content;">라면</div></span>
                    </td>
                    <td>
                        <span>외곽선을 만듭니다.</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <span>(목차)[1](/목차)</span>
                    </td>
                    <td>
                        <a href="#index1" style="vertical-align: unset;">1.</a>
                    </td>
                    <td>
                        <span>목차를 만듭니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <span>(목차내용)[1](/목차내용)</span>
                    </td>
                    <td>
                        <span id="index1">1.</span>
                    </td>
                    <td>
                        <span>목차를 링크합니다.</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>(이미지)이미지경로(/이미지)</span>
                    </td>
                    <td>
                        <span id="index1">image</span>
                    </td>
                    <td>
                        <span>이미지를 출력합니다</span>
                    </td>
                </tr>


            </table>
        </p>
</details>
