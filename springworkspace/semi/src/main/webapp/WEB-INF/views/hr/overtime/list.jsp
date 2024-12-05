<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>EVERY READY PLAN</title>
            <link rel="stylesheet" href="/css/common/index.css">
            <script defer src="/js/hr/overtime/list.js"></script>
            <link rel="stylesheet" href="/css/common/search.css">
            <link rel="stylesheet" href="/css/hr/overtime/list.css">
            <link rel="stylesheet" href="/css/common/list.css">
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
                                    <div class="menu-name">초과근무 관리</div>
                                    <div>
                                        <form action="/api/hr/overtime/list" method="get" class="top-title-area-form">
                                            <!-- <label for="">연월일</label> -->
                                            <!-- <div class="search-bar"><input type="text" name="year" maxlength="4" placeholder="연도"></div>
                                                <div class="search-bar"><input type="text" name="month" maxlength="2" placeholder="월"></div>
                                                <div class="search-bar"><input type="text" name="day" maxlength="2" placeholder="일"></div> -->

                                                <label for="연월 date"></label>
                                                <div class="search-bar"></div>
    
                                                <label for="연월일 date"></label>
                                                <div class="search-bar"><input type="date" name="searchYear"></div>
    
                                                <label for="select"></label>
                                                <div class="search-bar">
                                                    <select name="searchOption" id="">
                                                        <option value="" selected>부서</option>
                                                        <option value="1">인사팀</option>
                                                        <option value="2">품질팀</option>
                                                        <option value="3">재무팀</option>
                                                        <option value="4">마케팅</option>
                                                        <option value="5">CS</option>
                                                        <option value="6">헬프데스크</option>
                                                        <option value="7">경영</option>
                                                        <option value="8">기획</option>
                                                        <option value="9">디자인</option>
                                                
                                                    </select>
                                                </div>
    
                                                <label for="검색어"></label>
                                                <div class="search-bar">
                                                    <select name="searchType" id="">
                                                        <option value="" selected>카테고리</option>
                                                        <option value="A.EMP_NO">사원번호</option>
                                                        <option value="B.NAME">이름</option>
                                                    </select>
                                                </div>
                                                <div class="search-bar"><input type="searchValue" id="longbar" name="searchValue"></div>
                                                <div class="search-bar"><button class="searchbutton">검색</button></div>
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
                                                <th>일자</th>
                                                <th>사원번호</th>
                                                <th>사원명</th>
                                                <th>소속부서</th>
                                                <th>직급</th>
                                                <th>초과근무구분</th>
                                                <th>초과근무시간</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${overTimeVoList}" var="vo">
                                                <tr>
                                                    <td class="checkbox-td"><input type="checkbox" name="del" value="${vo.no}"></td>
                                                    <td>${vo.no}</td>
                                                    <td>${vo.thisDate}</td>
                                                    <td>${vo.empNo}</td>
                                                    <td>${vo.name}</td>
                                                    <td>${vo.dname}</td>
                                                    <td>${vo.pname}</td>
                                                    <td>${vo.type}</td>
                                                    <td>${vo.workHour}</td>
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
                                                    <a href="/api/hr/overtime/list?pno=${pvo.startPage-1}" class="page-button previous">&laquo;</a>
                                                </c:if>
                                                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                                    <a href="/api/hr/overtime/list?pno=${i}" class="page-button">${i}</a>
                                                </c:forEach>
                                                <c:if test="${pvo.endPage != pvo.maxPage}">
                                                    <a href="/api/hr/overtime/list?pno=${pvo.endPage+1}" class="page-button next">&raquo;</a>
                                                </c:if>
                                            </div>
                                           
                                        </div>
                                    </div>
                            
                                    <!-- <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div> -->
                                     
                            
                                    <div><button class="crud-button-white" id="create">등록</button></div>
                                </div>

                                    <!-- 모달 초과 근무구조 -->


                                    <div>
                                        <form action="/api/hr/overtime/write" method="post" id="overmodal1"
                                            class="overmodal1">
                                            <div class="overmodal1-content">
                                                <span class="overmodal1-close">&times;</span>
                                                <div class="overmodal1-title">초과근무 등록 </div>
                                                <div><span class="title-span" id="overtimespan">*는 필수입력 사항입니다</span>
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="employeeId"><span class="title-span">*</span>사번</label>
                                                    <div><input type="text" id="employeeId" class="employeeId"
                                                            name="empNo"> <input type="button" value="사번선택"
                                                            class="employee-select-btn"></div>
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="name"><span class="title-span">*</span>사원명</label>
                                                    <input type="text" id="name" name="name" />
                                                </div>
                                                <div class="overmodal1-cont" id="department">
                                                    <label for="position"><span class="title-span">*</span>소속부서</label>
                                                    <input type="text" name="dname">
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="phone"><span class="title-span">*</span>직급</label>
                                                    <input type="text" name="pname" />
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="region"><span class="title-span">*</span>초과근무일자
                                                        ex)2024-11-26</label>
                                                    <input type="date" id="region" name="thisDate" />
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="region"><span
                                                            class="title-span">*</span>초과근무구분&nbsp;&nbsp;&nbsp;
                                                        <input type="radio" id="region" name="type" value="주말근무" />주말근무
                                                        <input type="radio" id="region" name="type"
                                                            value="야간근무" />야간근무</label>
                                                </div>
                                                <div class="overmodal1-cont">
                                                    <label for="position"><span class="title-span">*</span>초과근무시간
                                                        <select name="hour" id="overtime-hour">
                                                            <option value="00">0</option>
                                                            <option value="01">1</option>
                                                            <option value="02">2</option>
                                                            <option value="03">3</option>
                                                            <option value="04">4</option>
                                                            <option value="05">5</option>
                                                            <option value="06">6</option>
                                                            <option value="07">7</option>
                                                            <option value="08">8</option>
                                                            <option value="09">9</option>
                                                            <option value="10">10</option>
                                                            <option value="11">11</option>
                                                            <option value="12">12</option>
                                                        </select>시
                                                        <select name="minute" id="overtime-minute">
                                                            <option value="00">0</option>
                                                            <option value="30">30</option>
                                                        </select>분
                                                    </label>

                                                </div>
                                                <div class="overmodal-cont"></div>
                                                <div></div>
                                                <div class="btn-area">
                                                    <div class="button-container"><input type="submit" value="등록"></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <!--                    초과근무 상세조회         -->

                                    <div class="overselectmodal1">

                                        <div class="overselectmodal1-content">
                                            <span class="overselectmodal1-close">&times;</span>
                                            <div class="overselectmodal1-title">초과근무 등록 </div>
                                            <div><span class="title-span" id="overtimespan">*는 필수입력 사항입니다</span></div>
                                            <div class="overselectmodal1-cont">
                                                <label for="employeeId"><span class="title-span">*</span>사번</label>
                                                <div><input type="text" id="employeeId" class="employeeId" name="empNo">
                                                    <input type="button" value="사번선택" class="employee-select-btn"></div>
                                            </div>
                                            <div class="overselectmodal1-cont">
                                                <label for="name"><span class="title-span">*</span>사원명</label>
                                                <input type="text" id="name" name="name" />
                                            </div>
                                            <div class="overselectmodal1-cont" id="department">
                                                <label for="position"><span class="title-span">*</span>소속부서</label>
                                                <input type="text" name="dname">
                                            </div>
                                            <div class="overselectmodal1-cont">
                                                <label for="phone"><span class="title-span">*</span>직급</label>
                                                <input type="text" name="pname" />
                                            </div>
                                            <div class="overselectmodal1-cont">
                                                <label for="region"><span class="title-span">*</span>초과근무일자
                                                    </label>
                                                <input type="date" id="region" name="thisDate" />
                                            </div>
                                            <div class="overselectmodal1-cont">
                                                <label for="region"><span
                                                        class="title-span">*</span>초과근무구분&nbsp;&nbsp;&nbsp;
                                                    <input type="radio" id="region" name="type" value="주말근무" />주말근무
                                                    <input type="radio" id="region" name="type"
                                                        value="야간근무" />야간근무</label>
                                            </div>
                                            <div class="overselectmodal1-cont">
                                                <label for="position"><span class="title-span">*</span>초과근무시간
                                                    <select name="hour" id="overtime-hour">
                                                        <option value="00">0</option>
                                                        <option value="01">1</option>
                                                        <option value="02">2</option>
                                                        <option value="03">3</option>
                                                        <option value="04">4</option>
                                                        <option value="05">5</option>
                                                        <option value="06">6</option>
                                                        <option value="07">7</option>
                                                        <option value="08">8</option>
                                                        <option value="09">9</option>
                                                        <option value="10">10</option>
                                                        <option value="11">11</option>
                                                        <option value="12">12</option>
                                                    </select>시
                                                    <select name="minute" id="overtime-minute">
                                                        <option value="00">0</option>
                                                        <option value="30">30</option>
                                                    </select>분
                                                </label>

                                            </div>
                                            <div class="overselectmodal1-cont"></div>
                                            <div></div>
                                            <div class="btn-area">
                                                <div class="button-container"></div>
                                            </div>
                                        </div>

                                    </div>
                                    <!--                                    사원조회 모달          -->
                                    <div id="btnmodal" class="btnmodal">
                                    
                                        <div class="btnmodal-content">
                                            <span class="btnmodal-close">&times;</span>
                                            <div class="btnmodal-title">사원번호 조회<span id="searchEmpbar">사번<input type="text" placeholder="사번을 입력" name="searchEmpNo">이름<input type="text"placeholder="이름을 입력" name="searchEname"><button id="choice">조회</button></span></div>
                                            


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



                                            
                                        </div>
                                </div>




                            </div>
                    </div>
        </body>

        </html>