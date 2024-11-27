<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/list.css">
</head>

    <div class="middle-content-area">
        <table class="list-area">
            <thead>
                <tr>
                    <th><input type="checkbox" name=""></th>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach begin="1" end="14">
                    <tr>
                        <td><input type="checkbox" name=""></td>
                        <td>1</td>
                        <td>테이블 디자인 예제</td>
                        <td>홍길동</td>
                        <td>2024-11-26</td>
                        <td>123</td>
                        <td>2024-11-26</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>