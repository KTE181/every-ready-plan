<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/search.css">
</head>

    <div class="top-title-area">
        <div class="menu-name">직원검색</div>
        <div>
            <form action="/employee/search" method="GET" class="top-title-area-form">

                <label for="department"></label>
                <div class="search-bar">
                    <select name="department" id="department">
                        <option value="">부서명</option>
                        <option value="1">인사팀</option>
                        <option value="2">품질</option>
                        <option value="3">재무</option>
                        <option value="4">마케팅</option>
                        <option value="5">CS</option>
                        <option value="6">헬프데스크</option>
                        <option value="7">경영</option>
                        <option value="8">기획</option>
                        <option value="9">디자인</option>
                    </select>
                </div> 

                <label for="position"></label>
                <div class="search-bar">
                    <select name="position" id="position">
                        <option value="">직급</option>
                        <option value="인턴">인턴</option>
                        <option value="사원">사원</option>
                        <option value="대리">대리</option>
                        <option value="과장">과장</option>
                        <option value="차장">차장</option>
                        <option value="부장">부장</option>
                        <option value="상무">상무</option>
                        <option value="사장">사장</option>
                    </select>
                </div>
            <!-- 이름 검색 -->
            <div class="search-bar">
                <input type="text" name="name" id="name" placeholder="이름">
            </div>

            <!-- 검색 버튼 -->
            <div class="search-bar">
                <button type="submit" class="button">검색</button>
            </div>
            </form>
        </div>
    </div>
    <!-- 검색 결과 출력 -->
    <div class="search-results">
        <table>
            <thead>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>직급</th>
                    <th>부서</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>생년월일</th>
                    <th>성별</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employee" items="${employeeList}">
                    <tr>
                        <td>${employee.no}</td>
                        <td>${employee.name}</td>
                        <td>${employee.position}</td>
                        <td>${employee.department}</td>
                        <td>${employee.phoneNumber}</td>
                        <td>${employee.email}</td>
                        <td>${employee.birth}</td>
                        <td>${employee.gender}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
