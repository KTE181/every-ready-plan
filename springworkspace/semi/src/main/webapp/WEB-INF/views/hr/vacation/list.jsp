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
            <link rel="stylesheet" href="/css/common/search.css">
            <link rel="stylesheet" href="/css/common/list.css">
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
                                <div class="top-title-area">
                                        <div class="menu-name">휴가 관리</div>
                                        <div>
                                            <form action="" class="top-title-area-form">
                                                <!-- <label for="">연월일</label> -->
                                                <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                                                <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                                                <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->

                                                <label for="연월 date"></label>
                                                <div class="search-bar"><input type="month" name=""></div>

                                                <label for="연월일 date"></label>
                                                <div class="search-bar"><input type="date" name=""></div>

                                                <label for="select"></label>
                                                <div class="search-bar">
                                                    <select name="" id="">
                                                        <option value="1">소속부서 전체</option>
                                                        <option value="2">재무팀</option>
                                                        <option value="2">인사팀</option>
                                                    </select>
                                                </div>

                                                <label for="검색어"></label>
                                                <div class="search-bar">
                                                    <select name="searchType" id="">
                                                        <option value="1">제목</option>
                                                        <option value="2">내용</option>
                                                        <option value="2">제목+내용</option>
                                                    </select>
                                                </div>
                                                <div class="search-bar"><input type="search" id="longbar"></div>
                                                <div class="search-bar"><button class="button">검색</button></div>
                                            </form>
                                        </div>
                                    </div>

                                    <!-- List Area -->

                                        <div class="middle-content-area">
                                                <table class="list-area">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" name=""></th>
                                                            <th>순번</th>
                                                            <th>휴가사용일자</th>
                                                            <th>사원번호</th>
                                                            <th>사원명</th>
                                                            <th>소속부서</th>
                                                            <th>직급</th>
                                                            <th>휴가유형</th>
                                                            <th>총휴가일수</th>
                                                            <th>사용휴가일수</th>
                                                            <th>남은휴가일수</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${vacationListVo}" var="vo">
                                                            <tr>
                                                                <td><input type="checkbox" name=""></td>
                                                                <td>${vo.no}</td>
                                                                <td>${vo.thisDate}</td>
                                                                <td>${vo.empNo}</td>
                                                                <td>${vo.ename}</td>
                                                                <td>${vo.dname}</td>
                                                                <td>${vo.pname}</td>
                                                                <td>${vo.type}</td>
                                                                <td>${vo.allVacation}</td>
                                                                <td>${vo.useVacation}</td>
                                                                <td>${vo.vacation}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        <!-- Bottom Area -->
                                        <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
                                            <!-- 모달 구조  test-->
                                              <!--                                    휴가 등록 모달          -->
                                            <div>
                                                <!-- <div class="test-area">
                                                    <div class="test3">TEST3</div>
                                                </div> -->
                                                <form action="/api/hr/vacation/write" method="post" id="vacationmodal" class="vacationmodal">
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
                                            <!--                                    휴가 상세조회 모달          -->
                                            <div>
                                                <!-- <div class="test-area">
                                                    <div class="test3">TEST3</div>
                                                </div> -->
                                                <form action="/api/hr/vacation/write" method="post" id="vacationselectmodal" class="vacationselectmodal">
                                                    <div class="vacationselectmodal-content">
                                                        <span class="vacationselectmodal-close">&times;</span>
                                                        <div class="vacationselectmodal-title">휴가 상세조회</div>
                                                        <div><span class="title-span" id="overtimespan">*는 필수입력
                                                                사항입니다</span></div>
                                                        <div class="vacationselectmodal-cont">
                                                            <label for="employeeId"><span
                                                                    class="title-span">*</span>사번</label>
                                                            <div><input type="text" id="employeeId" class="employeeId"
                                                                    name="empNo" disabled="false"> <input type="button" value="사번선택"
                                                                    class="employee-select-btn" disabled="false"></div>
                                                        </div>
                                                        <div class="vacationselectmodal-cont">
                                                            <label for="name"><span
                                                                    class="title-span">*</span>사원명</label>
                                                            <input type="text" id="name" name="name" disabled="false"/>
                                                        </div>
                                                        <div class="vacationselectmodal-cont" id="department">
                                                            <label for="position"><span
                                                                    class="title-span">*</span>소속부서</label>
                                                            <input type="text" name="dname" disabled="false">
                                                        </div>
                                                        <div class="vacationselectmodal-cont">
                                                            <label for="phone"><span
                                                                    class="title-span">*</span>직급</label>
                                                            <input type="text" name="pname" disabled="false" />
                                                        </div>
                                                        <div class="vacationselectmodal-cont">
                                                            <label for="region"><span class="title-span">*</span>일자
                                                                ex)2024-11-26</label>
                                                            <input type="text" name="thisDate" disabled="false" />
                                                        </div>
                                                        <div class="vacationselectmodal-cont">
                                                            <label for="region"><span class="title-span">*</span>휴가유형
                                                                <select name="code" id="overtime-type" disabled="false">
                                                                    <option value="1">연차</option>
                                                                    <option value="2">반차</option>
                                                                    <option value="3">법정휴가</option>
                                                                    <option value="4">연가</option>
                                                                </select></label>
                                                        </div>
                                                        <div class="vacationselectmodal-cont" id="content-area">
                                                            <label for="position"><span class="title-span">*</span>휴가사유
                                                            </label>
                                                            <textarea name="reason" disabled="false"></textarea>

                                                        </div>

                                                        <div></div>
                                                        <div class="btn-area">
                                                            <div class="button-container"><button>수정</button></div>
                                                            <div class="button-container"><button>삭제</button></div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
<!--                                    사원조회 모달          -->
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