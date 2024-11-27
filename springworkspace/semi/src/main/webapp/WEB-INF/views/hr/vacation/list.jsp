<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <script defer src="/js/hr/vacation/list.js"></script>
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

                                <!-- Search Area -->
                                <%@ include file="/WEB-INF/views/common/search.jsp" %>

                                    <!-- List Area -->
                                    <%@ include file="/WEB-INF/views/common/list.jsp" %>

                                        <!-- Bottom Area -->
                                        <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
                                            <!-- 모달 구조  test-->
                                            <div>
                                                <!-- <div class="test-area">
                                                    <div class="test3">TEST3</div>
                                                </div> -->
                                                <form action="" method="post" id="vacationmodal" class="vacationmodal">
                                                    <div class="vacationmodal-content">
                                                        <span class="vacationmodal-close">&times;</span>
                                                        <div class="vacationmodal-title">휴가 등록</div>
                                                        <div><span class="title-span" id="overtimespan">*는 필수입력
                                                                사항입니다</span></div>
                                                        <div class="vacationmodal-cont">
                                                            <label for="employeeId"><span
                                                                    class="title-span">*</span>사번</label>
                                                            <div><input type="text" id="employeeId" class="employeeId"
                                                                    name="empNo"> <input type="button" value="사번선택"
                                                                    class="employee-select-btn"></div>
                                                        </div>
                                                        <div class="vacationmodal-cont">
                                                            <label for="name"><span
                                                                    class="title-span">*</span>사원명</label>
                                                            <input type="text" id="name" name="name" />
                                                        </div>
                                                        <div class="vacationmodal-cont" id="department">
                                                            <label for="position"><span
                                                                    class="title-span">*</span>소속부서</label>
                                                            <input type="text" name="dname">
                                                        </div>
                                                        <div class="vacationmodal-cont">
                                                            <label for="phone"><span
                                                                    class="title-span">*</span>직급</label>
                                                            <input type="text" name="pname" />
                                                        </div>
                                                        <div class="vacationmodal-cont">
                                                            <label for="region"><span class="title-span">*</span>일자
                                                                ex)2024-11-26</label>
                                                            <input type="text" name="thisDate" />
                                                        </div>
                                                        <div class="vacationmodal-cont">
                                                            <label for="region"><span class="title-span">*</span>휴가유형
                                                                <select name="code" id="overtime-type">
                                                                    <option value="1">연차</option>
                                                                    <option value="2">반차</option>
                                                                    <option value="3">법정휴가</option>
                                                                    <option value="4">연가</option>
                                                                </select></label>
                                                        </div>
                                                        <div class="vacationmodal-cont" id="content-area">
                                                            <label for="position"><span class="title-span">*</span>휴가사유
                                                            </label>
                                                            <textarea name="reason"></textarea>

                                                        </div>

                                                        <div></div>
                                                        <div class="btn-area">
                                                            <div class="button-container"><button>등록</button></div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>

                                            <div>
                                                <form action="" method="post" id="btnmodal" class="btnmodal">
                                                    <div class="btnmodal-content">
                                                        <span class="btnmodal-close">&times;</span>
                                                        <div class="btnmodal-title">사원번호 조회</div>
                                                        

                                                        <div class="btnmodal-main">
                                                            <table border="1">
                                                                <thead>
                                                                    <tr>
                                                                        <th>사번</th>
                                                                        <th>사원명</th>
                                                                        <th>소속</th>
                                                                        <th>직급</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>                                                            
                                                                    <c:forEach var="vo" items="${empVoList}">
                                                                        <tr>
                                                                            <td><a href="#" id="empNo_${vo.no}"
                                                                                    onclick="changeEmpNo(this);">${vo.no}</a>
                                                                            </td>
                                                                            <td>${vo.name}</td>
                                                                            <td>${vo.dname}</td>
                                                                            <td>${vo.pname}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>



                                                        <div class="btnmodal-cont"></div>
                                                        <div class="btn-area">
                                                            <div class="button-container"><button>선택</button></div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>

                                            


                            </div>
                    </div>
        </body>

        </html>