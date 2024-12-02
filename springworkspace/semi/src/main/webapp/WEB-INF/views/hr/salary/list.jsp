<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <script defer src="/js/hr/salary/list.js"></script>
            <link rel="stylesheet" href="/css/common/search.css">
            <link rel="stylesheet" href="/css/hr/salary/list.css">
            <link rel="stylesheet" href="/css/common/bottom.css">
            <link rel="stylesheet" href="/css/hr/vacation/list.css">
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
                                    <div class="menu-name">급여 관리</div>
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
                                                <th><input type="checkbox" onclick="handleCheckbox(this);"></th>
                                                <th>순번</th>
                                                <th>지급연월</th>
                                                <th>사원번호</th>
                                                <th>사원명</th>
                                                <th>소속부서</th>
                                                <th>직급</th>
                                                <th>기본급</th>
                                                <th>식대</th>
                                                <th>통신비</th>
                                                <th>지급총액</th>
                                                <th>국민연금</th>
                                                <th>건강보험</th>
                                                <th>고용보험</th>
                                                <th>장기요양보험료</th>
                                                <th>소득세</th>
                                                <th>지방소득세</th>
                                                <th>공제총액</th>
                                                <th>실지급액</th>


                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${SalaryVoList}" var="vo">
                                                <tr>
                                                    <td class="checkbox-td"><input type="checkbox" name="del" value="${vo.no}"></td>
                                                    <td>${vo.no}</td>
                                                    <td>${vo.payYearmonth}</td>
                                                    <td>${vo.empNo}</td>
                                                    <td>${vo.ename}</td>
                                                    <td>${vo.dname}</td>
                                                    <td>${vo.pname}</td>
                                                    <td>${vo.basic}</td>
                                                    <td>${vo.mealAllowance}</td>
                                                    <td>${vo.communicationCost}</td>
                                                    <td>${vo.payment}</td>
                                                    <td>${vo.nationalPension}</td>
                                                    <td>${vo.healthInsurance}</td>
                                                    <td>${vo.employmentInsurance}</td>
                                                    <td>${vo.longtermCareInsurance}</td>
                                                    <td>${vo.incomeTax}</td>
                                                    <td>${vo.localTaxes}</td>
                                                    <td>${vo.deductions}</td>
                                                    <td>${vo.netPayment}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- Bottom Area -->
                                <div class="bottom-content-area">
                                    <div>
                                        <button class="crud-button-white" onclick="deleteNotice();">삭제</button>
                                    </div>

                                    <!-- <div>
                                                <button class="ok-button">확인</button>
                                            </div>
                                    
                                            <div>
                                                <button class="cancle-button">취소</button>
                                            </div> -->

                                    <div>
                                        <div class="pagination">
                                            <!-- 이전 페이지 버튼 -->
                                           

                                            <!-- 페이지 번호 버튼들 -->
                                            <div class="pagination">
                                                <c:if test="${pvo.startPage != 1}">
                                                    <a href="/api/hr/salary/list?pno=${pvo.startPage-1}&searchValue=${searchValue}" class="page-button previous">&laquo;</a>
                                                </c:if>
                                                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                                    <a href="/api/hr/salary/list?pno=${i}&searchValue=${searchValue}" class="page-button">${i}</a>
                                                </c:forEach>
                                                <c:if test="${pvo.endPage != pvo.maxPage}">
                                                    <a href="/api/hr/salary/list?pno=${pvo.endPage+1}&searchValue=${searchValue}" class="page-button next">&raquo;</a>
                                                </c:if>
                                            </div>
                                           
                                        </div>
                                    </div>

                                    <!-- <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div> -->


                                    <div><button class="crud-button-white" id="create">등록</button></div>
                                </div>

                                <!-- 모달 구조 -->

                                <div id="salarymodal" class="salarymodal">
                                    <form action="/api/hr/salary/write" method="post">
                                        <div class="salarymodal-content">
                                            <span class="salarymodal-close">&times;</span>
                                            <div class="salarymodal-first">
                                                <div class="salarymodal-title">급여 등록</div>
                                                <div class="salarymodal-subtitle">기본정보
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <span class="title-span">*는 필수입력 사항입니다</span>
                                                </div>

                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>사번</label>
                                                    <div><input type="text" id="employeeId" class="employeeId"
                                                            name="empNo"> <input type="button" value="사번선택"
                                                            class="employee-select-btn"></div>
                                                    </button>
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>사원명</label>
                                                    <input type="text" id="price" name="name" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>소속부서</label>
                                                    <input type="text" id="price" name="dname" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>직급</label>
                                                    <input type="text" id="price" name="pname" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel">연봉</label>
                                                    <input type="text" id="price" name="salary" />
                                                </div>




                                            </div>
                                            <div class="salarymodal-second">
                                                <div class="salarymodal-subtitle">지급정보</div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span class="title-span">*</span>지급연월
                                                        ex)2024-06</label>
                                                    <input type="text" id="price" name="payYearmonth" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel">기본급</label>
                                                    <input type="text" id="price" name="basic" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>식대</label>
                                                    <input type="text" id="price" name="mealAllowance" value="200000" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>통신비</label>
                                                    <input type="text" id="price" name="communicationCost"
                                                        value="50000" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>지급총액</label>
                                                    <input type="text" id="price" name="payment" />
                                                </div>
                                            </div>

                                            <div class="salarymodal-third">
                                                <div class="salarymodal-subtitle">공제정보</div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>국민연금</label>
                                                    <input type="text" id="price" name="nationalPension" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>건강보험</label>
                                                    <input type="text" id="price" name="healthInsurance" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>고용보험</label>
                                                    <input type="text" id="price" name="employmentInsurance" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>장기요양보험료</label>
                                                    <input type="text" id="price" name="longtermCareInsurance" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>소득세</label>
                                                    <input type="text" id="price" name="incomeTax" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>지방소득세</label>
                                                    <input type="text" id="price" name="localTaxes" />
                                                </div>
                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel">공제총액</label>
                                                    <input type="text" id="price" name="deductions" />
                                                </div>

                                                <div class="salarymodal-cont">
                                                    <label class="salarylabel">실지급액</label>
                                                    <input type="text" id="price" name="netPayment" />
                                                </div>
                                                <div></div>
                                                <div class="salarymodal-cont">
                                                    <input type="submit" value="등록" />

                                                </div>

                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!--                                   급여 상세조회         -->
                                <div id="salaryselectmodal" class="salaryselectmodal">
                                    <form action="/api/hr/salary/write" method="post">
                                        <div class="salaryselectmodal-content">
                                            <span class="salaryselectmodal-close">&times;</span>
                                            <div class="salaryselectmodal-first">
                                                <div class="salaryselectmodal-title">급여 상세 조회</div>
                                                <div class="salaryselectmodal-subtitle">기본정보
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <span class="title-span">*는 필수입력 사항입니다</span>
                                                </div>

                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>사번</label>
                                                    <div><input type="text" id="employeeId" class="employeeId"
                                                            name="empNo"> <input type="button" value="사번선택"
                                                            class="employee-select-btn"></div>
                                                    </button>
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>사원명</label>
                                                    <input type="text" id="price" name="name" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>소속부서</label>
                                                    <input type="text" id="price" name="dname" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>직급</label>
                                                    <input type="text" id="price" name="pname" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel">연봉</label>
                                                    <input type="text" id="price" name="salary" />
                                                </div>




                                            </div>
                                            <div class="salaryselectmodal-second">
                                                <div class="salaryselectmodal-subtitle">지급정보</div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span class="title-span">*</span>지급연월
                                                        ex)2024-06</label>
                                                    <input type="text" id="price" name="payYearmonth" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel">기본급</label>
                                                    <input type="text" id="price" name="basic" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>식대</label>
                                                    <input type="text" id="price" name="mealAllowance" value="200000" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>통신비</label>
                                                    <input type="text" id="price" name="communicationCost"
                                                        value="50000" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>지급총액</label>
                                                    <input type="text" id="price" name="payment" />
                                                </div>
                                            </div>

                                            <div class="salaryselectmodal-third">
                                                <div class="salaryselectmodal-subtitle">공제정보</div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>국민연금</label>
                                                    <input type="text" id="price" name="nationalPension" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>건강보험</label>
                                                    <input type="text" id="price" name="healthInsurance" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>고용보험</label>
                                                    <input type="text" id="price" name="employmentInsurance" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>장기요양보험료</label>
                                                    <input type="text" id="price" name="longtermCareInsurance" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>소득세</label>
                                                    <input type="text" id="price" name="incomeTax" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel"><span
                                                            class="title-span">*</span>지방소득세</label>
                                                    <input type="text" id="price" name="localTaxes" />
                                                </div>
                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel">공제총액</label>
                                                    <input type="text" id="price" name="deductions" />
                                                </div>

                                                <div class="salaryselectmodal-cont">
                                                    <label class="salarylabel">실지급액</label>
                                                    <input type="text" id="price" name="netPayment" />
                                                </div>
                                                <div></div>
                                                <div class="salaryselectmodal-cont">
                                                    <div class="btn-class"></div>

                                                </div>

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
                                                <div class="pagination">
                                                    <c:if test="${pvo2.startPage != 1}">
                                                        <a href="" class="page-button previous">&laquo;</a>
                                                    </c:if>
                                                    <c:forEach begin="${pvo2.startPage}" end="${pvo2.endPage}" var="i" step="1">
                                                        <a href="#" class="page-button"  data-page="${i}" onclick="loadPage(this);">${i}</a>
                                                    </c:forEach>
                                                    <c:if test="${pvo2.endPage != pvo2.maxPage}">
                                                        <a href="#" class="page-button next">&raquo;</a>
                                                    </c:if>
                                                </div>
                                            </div>
                                            



                                            <div class="btnmodal-cont"></div>
                                            <div class="btn-area">
                                                <div class="button-container"></div>
                                            </div>
                                        </div>
                                    </form>
                                </div>




                            </div>
                    </div>
        </body>

        </html>