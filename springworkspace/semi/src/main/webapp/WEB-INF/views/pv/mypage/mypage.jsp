
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/pv/mypage.css">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script defer src="/js/pv/mypage.js"></script>
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
                <div class="modal-first"><!--크게 두 덩이로 나눴습니다-->
                    <div class="modal-title">마이페이지</div>
                    <div class="modal-subtitle">기본정보</div>
                    <div class="modal-profile">
                        <div class="modal-profile-index">
                            <img src="${pageContext.request.contextPath}${loginEmployeeVo.profileImage}?${System.currentTimeMillis()}" alt="프로필 이미지">
                        </div>
                    </div>
                    
                    <div class="modal-cont">
                        <label>사번</label>
                        <input type="text" id="no" value="${loginEmployeeVo.no}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>사원명</label>
                        <input type="text" id="name" value="${loginEmployeeVo.name}"readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>생년월일</label>
                        <input type="text" id="birth" value="${loginEmployeeVo.birth}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>성별</label>
                        <input type="text" id="gender" value="${loginEmployeeVo.gender}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>전화번호</label>
                        <input type="text" id="phone" value="${loginEmployeeVo.phone}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>비상연락처</label>
                        <input type="text" id="emergencyPhone" value="${loginEmployeeVo.emergencyPhone}" readonly/>
                    </div>
                    <div class="modal-long">
                        <label></label>이메일</label>
                        <input type="text" id="email" value="${loginEmployeeVo.email}" readonly/>
                    </div>
                    <div class="modal-long">
                        <label></label>주소</label>
                        <input type="text" id="address" value="${loginEmployeeVo.address}" readonly/>
                    </div>
        
                </div>
                <div class="modal-second">
                    <div class="modal-subtitle">인사정보</div>
                    <div class="modal-cont">
                        <label>소속부서</label>
                        <input type="text" id="deptName" value="${loginEmployeeVo.deptName}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>직급</label>
                        <input type="text" id="positionN" value="${loginEmployeeVo.positionName}" readonly/>
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>입사일</label>
                        <input type="text" id="enterDate"  value="${loginEmployeeVo.enterDate}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>재직상태</label>
                        <input type="text" id="empStatus" value="${loginEmployeeVo.statusName}" readonly/>
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>퇴사일</label>
                        <input type="text" id="outDate" value="${loginEmployeeVo.outDate}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>연봉</label>
                        <input type="text" id="salary" value="${loginEmployeeVo.salary}" readonly/>
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>은행명</label>
                        <input type="text" id="bankName" value="${loginEmployeeVo.bankName}" readonly/>
                    </div>
                    <div class="modal-cont">
                        <label>계좌번호</label>
                        <input type="text" id="accountNo" value="${loginEmployeeVo.accountNo}" readonly/>
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>총휴가일수</label>
                        <input type="text" id="totalVacationDays" value="${loginEmployeeVo.totalVacationDays}" readonly/>
                    </div>
                    <div id="employeeData" data-employee-id="${loginEmployeeVo.no}"></div>
                    <div class="button-container">
                        <button class="crud-button" onclick="">비밀번호 변경</button>
                        <button class="crud-button" onclick="openFileInput()">프로필 수정</button>
                        <input type="file" id="profileImageInput" style="display: none;" accept="image/*" onchange="uploadProfileImage()" />
                    </div>
                </div>   
            </div>




        </div>
    </div>
</body>
</html>
