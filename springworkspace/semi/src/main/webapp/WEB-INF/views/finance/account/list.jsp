<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/finance/account/list.css">
    <script defer src="./account.js"></script>


</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="menu-item active"><img src = "./logo.png"></div>
            <div class="menu-item dropdown">
                <div class = "title-menu">공용</div>
                <ul class="dropdown-menu">
                  <li>회사 조직도</li>
                  <li>회사 연혁도</li>
                  <li>직원 검색</li>
                </ul>
            </div>
            <div class="menu-item dropdown1">
                <div class = "title-menu">개인</div>
                <ul class="dropdown-menu1">
                  <li>마이페이지</li>
                  <li>휴가 내역 조회</li>
                  <li>출·퇴근내역 조회</li>
                  <li>급여명세서 조회</li>
                </ul>
            </div>
            <div class="menu-item dropdown2">
                <div class = "title-menu">인사</div>
                <ul class="dropdown-menu2">
                  <li>사원 관리</li>
                  <li>근태 관리</li>
                  <li>초과 근무 관리</li>
                  <li>휴가 관리</li>
                  <li>급여 관리</li>
                </ul>
            </div>
            <div class="menu-item dropdown3">
                <div class = "title-menu">품질</div>
                <ul class="dropdown-menu3">
                  <li>상품 관리</li>
                  <li>재고 현황 조회</li>
                  <li>불량상품 관리</li>
                  <li>AS 요청 관리</li>
                  <li>AS 작업 관리</li>
                  <li>품질 관리</li>
                  <li>담당자 관리</li>
                  <li>불량코드 관리</li>
                  <li>고장코드 관리</li>
                </ul>
            </div>
            <div class="menu-item dropdown4">
                <div class = "title-menu">재무</div>
                <ul class="dropdown-menu4">
                  <li>거래처 관리</li>
                  <li>회사 계좌 관리</li>
                  <li>매출 관리</li>
                  <li>매입 관리</li>
                  <li>경비 관리</li>
                  <li>급여 지급 관리</li>
                  <li>재무상태표 관리</li>
                  <li>손익계산서 조회</li>
                </ul>
            </div>
        </div>


        <!-- Main Content -->
		<div class="main-content">
            <div class="top-bar">
                <button class="button">글쓴이</button>
                <div class="image-circle">IMAGE</div>
            </div>

            <div class="content-area">
                <div class="top-content-area">
                    <div id="navigator">navi</div>
                    <div></div>

                </div>

                <div class="table-area">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>제목</th>
                                        <th>카테고리</th>
                                        <th>조회수</th>
                                        <th>작성자</th>
                                        <th>작성일시</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <td>로딩중...</td>
                                </tbody>
                            </table>
                        </div>

                    <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input type="search" id="longbar"></div>
                    <div class="search-bar"><button class="crud-button-white">검색</button></div>
                    </form>
                </div>


                <div class="middle-content-area">

                    <div class="table">


                      </div>
                </div>



                <div class="bottom-content-area">
                    <div>
                        <button class="crud-button-white">삭제</button>

                    </div>
                    <div>
                        <!-- <button class="ok-button">확인</button> -->

                    </div>

                    <div>
                        <!-- <button class="cancle-button">취소</button> -->

                    </div>
                    <div>

                        <div class="pagination">

                            <a href="#" class="page-button previous">&laquo;</a>


                            <a href="#" class="page-button">1</a>
                            <a href="#" class="page-button">2</a>
                            <a href="#" class="page-button">3</a>
                            <a href="#" class="page-button">4</a>
                            <a href="#" class="page-button">5</a>
                            <a href="#" class="page-button">6</a>
                            <a href="#" class="page-button">7</a>
                            <a href="#" class="page-button">8</a>
                            <a href="#" class="page-button">9</a>
                            <a href="#" class="page-button">10</a>
                            <a href="#" class="page-button active ">88</a>

                            <a href="#" class="page-button next">&raquo;</a>


                         </div>
                    </div>
                    <div class="bottom-content-area-5"><button class="crud-button-white">수정</button></div>
                    <div><button id="write" class="crud-button-white">등록</button></div>
                </div>

            </div>


        </div>
</body>
</html>
