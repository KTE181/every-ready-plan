
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
                    <div class="modal-title">사원 등록</div>
                    <div class="modal-subtitle">기본정보</div>
                    <div class="modal-profile">
                        <div class="modal-profile-index">
                            <img src="${pageContext.request.contextPath}${loginEmployeeVo.profileImage}" alt="기본이미지" />
                        </div>
                    </div>
                    
                    <div class="modal-cont">
                        <label>사번</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-cont">
                        <label>사원명</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-cont">
                        <label>생년월일</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-cont">
                        <label>성별</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-cont">
                        <label>전화번호</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-cont">
                        <label>비상연락처</label>
                        <input type="text" id="price" />
                    </div>
                    <div class="modal-long">
                        <label></label>이메일</label>
                        <input type="text" id="factoryAdress" />
                    </div>
                    <div class="modal-long">
                        <label></label>주소</label>
                        <input type="text" id="factoryAdress" />
                    </div>
        
                </div>
                <div class="modal-second">
                    <div class="modal-subtitle">인사정보</div>
                    <div class="modal-cont">
                        <label>소속부서</label>
                        <input type="text" id="deptN" />
                    </div>
                    <div class="modal-cont">
                        <label>직급</label>
                        <input type="text" id="positionN" />
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>입사일</label>
                        <input type="text" id="enterDate" />
                    </div>
                    <div class="modal-cont">
                        <label>재직상태</label>
                        <input type="text" id="status" />
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>퇴사일</label>
                        <input type="text" id="outDate" />
                    </div>
                    <div class="modal-cont">
                        <label>연봉</label>
                        <input type="text" id="salary" />
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>은행명</label>
                        <input type="text" id="bankN" />
                    </div>
                    <div class="modal-cont">
                        <label>계좌번호</label>
                        <input type="text" id="accountNume" />
                    </div>
                    <div></div>
                    <div class="modal-cont">
                        <label>총휴가일수</label>
                        <input type="text" id="totalVacationDays" />
                    </div>
                </div>   
            </div>




        </div>
    </div>
</body>
</html>
