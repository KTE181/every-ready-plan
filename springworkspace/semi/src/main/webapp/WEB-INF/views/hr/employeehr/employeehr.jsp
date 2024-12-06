<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/hr/employeehr/employeehr.css">
    <link rel="stylesheet" href="/css/common/search.css">
    <link rel="stylesheet" href="/css/common/index.css">
    <link rel="stylesheet" href="/css/common/bottom.css">

    <link rel="stylesheet" href="/css/hr/employeehr/modal.css">
    <script defer src="/js/hr/employeehr/employeehr.js"></script>

</head>
<body>
    <div class="container">

        <!-- Sidebar -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Main Content -->
        <div class="main-content">

            <!-- Header -->
            <%@ include file="/WEB-INF/views/common/header.jsp" %>

            <!-- Contents Area -->
            <div class="content-area">
                <div class="top-title-area">
                    <div class="menu-name">직원검색</div>
                    <div>
                        <form action="/employeehr" method="GET" class="top-title-area-form">
                            <!-- 소속부서 검색 -->
                            <label for="dname"></label>
                            <div class="search-bar">
                                <select name="dname" id="dname">
                                    <option value="">부서</option>
                                    <option value="인사팀">인사팀</option>
                                    <option value="품질">품질</option>
                                    <option value="재무">재무</option>
                                    <option value="마케팅">마케팅</option>
                                    <option value="CS">CS</option>
                                    <option value="헬프데스크">헬프데스크</option>
                                    <option value="경영">경영</option>
                                    <option value="기획">기획</option>
                                    <option value="디자인">디자인</option>
                                </select>
                            </div>
                            <!-- 재직상태 검색 -->
                            <label for="esname"></label>
                            <div class="search-bar">
                                <select name="esname" id="esname">
                                    <option value="">재직상태</option>
                                    <option value="재직">재직</option>
                                    <option value="휴직">휴직</option>
                                    <option value="출장">출장</option>
                                    <option value="출산">출산</option>
                                    <option value="병가">병가</option>
                                    <option value="퇴사">퇴사</option>
                                </select>
                            </div>

                            <!-- 직급 검색 -->
                            <label for="pname"></label>
                            <div class="search-bar">
                                <select name="pname" id="pname">
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
                            <!-- 사번 검색 -->
                            <div class="search-bar">
                                <input type="text" name="no" id="no" placeholder="사번">
                            </div>
                            <!-- 검색 버튼 -->
                            <div class="search-bar">
                                <button type="submit" class="button">검색</button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- 검색 결과 -->
                <table border=1 class="employee-table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" onclick="toggleAll(this)" /></th>
                            <th>사번</th>
                            <th>사원명</th>
                            <th>생년월일</th>
                            <th>성별</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>부서</th>
                            <th>직급</th>
                            <th>재직상태</th>
                            <th>입사일</th>
                            <th>퇴사일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employeeVoList}">
                            <tr>
                                <td><input type="checkbox" name="employeeCheck" value="${employee.no}" /></td>
                                <td>${employee.no}</td>
                                <td>${employee.name}</td>
                                <td>${employee.birth}</td>
                                <td>${employee.gender == 'M' ? '남' : '여'}</td>
                                <td>${employee.email}</td>
                                <td>${employee.phone}</td>
                                <td>${employee.dname}</td>
                                <td>${employee.pname}</td>
                                <td>${employee.esname}</td>
                                <td>${employee.enterDate}</td>
                                <td>${employee.outDate != null ? employee.outDate : ''}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="bottom-content-area">
                <div>
                    <button class="crud-button-white" onclick="deleteEmployees()">삭제</button>
                </div>

                <div class="pagination">
                    <!-- 이전 버튼 -->
                    <c:if test="${pageVo.currentPage > 1}">
                        <a href="/employeehr?page=${pageVo.currentPage - 1}&dname=${dname}&pname=${pname}&name=${name}&esname=${esname}" 
                           class="page-button">이전</a>
                    </c:if>
                
                    <!-- 페이지 번호 -->
                    <c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
                        <a href="/employeehr?page=${i}&dname=${dname}&pname=${pname}&name=${name}&esname=${esname}" 
                           class="page-button ${i == pageVo.currentPage ? 'active' : ''}">
                           ${i}
                        </a>
                    </c:forEach>
                
                    <!-- 다음 버튼 -->
                    <c:if test="${pageVo.currentPage < pageVo.maxPage}">
                        <a href="/employeehr?page=${pageVo.currentPage + 1}&dname=${dname}&pname=${pname}&name=${name}&esname=${esname}" 
                           class="page-button">다음</a>
                    </c:if>
                </div>
                    <div><button class="crud-button-white" id="create">등록</button></div>
                </div>
            </div>
        </div>

        <!-- 모달 부분 -->
        <div id="registerModal" class="modal">
            <div class="modal-content">
                <!-- 헤더 -->
                <div class="modal-header">
                    <h2>사원 등록</h2>
                    <span class="close-button" onclick="closeModal()">&times;</span>
                </div>

                <!-- 등록 폼 -->
                <form action="/employeehr/register" method="post" enctype="multipart/form-data">
                    <!-- 기본 정보 -->
                    <fieldset>
                        <legend>기본 정보</legend>
                        <div class="grid-container">
                            <!-- 프로필 이미지 -->
                            <div class="profile-picture">
                                <img id="profilePreview" src="/images/default-profile.png" alt="프로필 사진">
                                <input type="file" id="profilePicture" name="profilePicture" accept="image/*" onchange="previewProfilePicture(event)">
                                <p>사원 이미지</p>
                            </div>

                            <!-- 사번, 사원명 -->
                            <div>
                                <label>사번</label>
                                <input type="text" name="employeeId" required>
                            </div>
                            <div>
                                <label>사원명</label>
                                <input type="text" name="employeeName" required>
                            </div>

                            <!-- 생년월일, 성별 -->
                            <div>
                                <label>생년월일</label>
                                <input type="date" name="employeeBirth" required>
                            </div>
                            <div>
                                <label>성별</label>
                                <div class="radio-group">
                                    <label><input type="radio" name="employeeGender" value="M" required> 남</label>
                                    <label><input type="radio" name="employeeGender" value="F" required> 여</label>
                                </div>
                            </div>

                            <!-- 전화번호, 비상연락처 -->
                            <div>
                                <label>전화번호</label>
                                <input type="text" name="employeePhone" required>
                            </div>
                            <div>
                                <label>비상연락처</label>
                                <input type="text" name="employeeEmergencyPhone">
                            </div>

                            <!-- 이메일, 주소 -->
                            <div>
                                <label>이메일</label>
                                <input type="email" name="employeeEmail" required>
                            </div>
                            <div>
                                <label>주소</label>
                                <input type="text" name="employeeAddress" required>
                            </div>
                        </div>
                    </fieldset>

                    <!-- 인사 정보 -->
                    <fieldset>
                        <legend>인사 정보</legend>
                        <div class="grid-container">
                            <!-- 소속부서, 직급 -->
                            <div>
                                <label>소속부서</label>
                                <select name="employeeDept" required>
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
                            <div>
                                <label>직급</label>
                                <select name="employeePosition" required>
                                    <option value="1">인턴</option>
                                    <option value="2">사원</option>
                                    <option value="3">대리</option>
                                    <option value="4">과장</option>
                                    <option value="5">차장</option>
                                    <option value="6">부장</option>
                                    <option value="7">상무</option>
                                    <option value="8">사장</option>
                                </select>
                            </div>

                            <!-- 입사일, 연봉 -->
                            <div>
                                <label>입사일</label>
                                <input type="date" name="employeeEnrollDate" required>
                            </div>
                            <div>
                                <label>연봉</label>
                                <input type="number" name="employeeSalary">
                            </div>

                            <!-- 재직상태, 퇴사일 -->
                            <div>
                                <label>재직상태</label>
                                <select name="employeeEmpstatus" required>
                                    <option value="재직">재직</option>
                                    <option value="휴직">휴직</option>
                                    <option value="퇴사">퇴사</option>
                                </select>
                            </div>
                            <div>
                                <label>퇴사일</label>
                                <input type="date" name="employeeOutDate">
                            </div>

                            <!-- 은행명, 계좌번호, 총휴가일수 -->
                            <div>
                                <label>은행명</label>
                                <select name="employeeBankName" required>
                                    <option value="1">한국</option>
                                    <option value="2">산업</option>
                                    <option value="3">기업</option>
                                    <option value="4">국민</option>
                                    <option value="5">외환</option>
                                    <option value="6">수협</option>
                                    <option value="7">농협</option>
                                    <option value="8">우리</option>
                                    <option value="9">대구</option>
                                    <option value="10">부산</option>
                                    <option value="11">신협</option>
                                </select>
                            </div>
                            <div>
                                <label>계좌번호</label>
                                <input type="text" name="employeeBankAccount">
                            </div>
                            <div>
                                <label>총휴가일수</label>
                                <input type="number" name="employeeTotalLeaveDays">
                            </div>
                        </div>
                    </fieldset>

                    <!-- 버튼 -->
                    <div class="form-actions">
                        <button type="submit">등록</button>
                        <button type="button" onclick="closeModal()">취소</button>
                    </div>
                </form>
            </div>
        </div>







    </div>

</body>
</html>