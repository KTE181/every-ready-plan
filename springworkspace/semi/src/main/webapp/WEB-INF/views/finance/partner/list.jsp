
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/common/index.css">
    <link rel="stylesheet" href="/css/finance/partner/list.css">
    <link rel="stylesheet" href="/css/finance/partner/write.css">
    <link rel="stylesheet" href="/css/finance/partner/detail.css">
    <link rel="stylesheet" href="/css/finance/partner/edit.css">
    <link rel="stylesheet" href="/css/common/bottom.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script defer src="/js/finance/partner/list.js"></script>
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
                    <form action="" class="top-title-area-form">
                    <!-- <div class="search-bar"><label for="">날짜&nbsp&nbsp</label><input type="text" name="year" maxlength="4">년
                        <input type="text" name="month" maxlength="2">월
                        <input type="text" name="day" maxlength="2">일</div> -->
                    <div class="search-bar" ><label for="">&nbsp&nbsp&nbsp&nbsp&nbsp카테고리&nbsp&nbsp&nbsp&nbsp&nbsp</label><select name="" id="">
                        <option value="1">회사명</option>
                        <option value="2">사업자등록번호</option>
                        <option value="3">사업자명</option>
                        <option value="4">업종</option>
                        </select>
                    </div>
                    <div class="search-bar"><label for="">내용검색&nbsp&nbsp</label><input type="search" id="longbar"></div>
                    <div class="search-bar"><button class="crud-button-white">검색</button></div>
                    </form>
                </div>

                <!-- List Area -->
                <div class="middle-content-area">
                    <table class="list-area">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name=""></th>
                                <th>거래처코드</th>
                                <th>업종코드</th>
                                <th>업종</th>
                                <th>회사명</th>
                                <th>사업자 등록 번호</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${partnerVoList}" var="partnerVo">
                                <tr id="partner-list" onclick="partnerDetail('${partnerVo.no}');">
                                    <td><input type="checkbox" name=""></td>
                                    <td>${partnerVo.no}</td>
                                    <td>${partnerVo.businessCode}</td>
                                    <td>${partnerVo.bizType}</td>
                                    <td>${partnerVo.name}</td>
                                    <td>${partnerVo.bizRegistNo}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>


             <!-- Bottom Area -->
                <div class="bottom-content-area">
                    <div><button class="crud-button-white">삭제</button></div>
                    <div>
                        <div class="pagination">
                            <!-- 이전 페이지 버튼 -->
                            <a href="#" class="page-button previous">&laquo;</a>

                            <!-- 페이지 번호 버튼들 -->
                            <a href="#" class="page-button active">1</a>
                            <a href="#" class="page-button">2</a>
                            <a href="#" class="page-button">3</a>
                            <a href="#" class="page-button">4</a>
                            <a href="#" class="page-button">5</a>
                            <a href="#" class="page-button">6</a>
                            <a href="#" class="page-button">7</a>
                            <a href="#" class="page-button">8</a>
                            <a href="#" class="page-button">9</a>
                            <a href="#" class="page-button">10</a>
                            <!-- 다음 페이지 버튼 -->
                            <a href="#" class="page-button next">&raquo;</a>
                         </div>
                    </div>
                    <div><button class="crud-button-white" id="partner-write-btn" onclick="partnerWrite();">등록</button></div>
                </div>


        <!-- Write Modal -->
            <div id="partner-write">
                <form action="/finance/partner/write" method="post" onsubmit="return confirm('등록하시겠습니까?')";
                <div class="write-content">
                    <span class="write-close" onclick="partnerWriteClose();">&times;</span>
                    <div class="modal-title">거래처 등록</div>
                    <div id="required-text"></div>
                    
                    <div class="title-text"></div>
                    
                    <div class="modal-cont">
                        <label for="">거래처 코드</label>
                        <div>
                            <input type="text" name="no" >
                        </div>
                    </div>

                    <div class="modal-cont">
                        <label for="">업종코드</label>
                        <div>
                            <input type="text" name="businessCode" >
                        </div>
                    </div>
                    <div class="modal-cont">
                        <label for="">상호명</label>
                        <div>
                            <input type="text" name="name" >
                        </div>
                    </div>
                    <div class="modal-cont">
                        <label for="">사업자등록번호</label>
                        <div>
                            <input type="text" name="bizRegistNo" >
                        </div>
                    </div>
                    <div class="modal-cont">
                        <label for="">대표자명</label>
                        <div>
                            <input type="text" name="ceoName" >
                        </div>
                    </div>
                    <div class="modal-cont">
                        <label for="">주소</label>
                        <div>
                            <input type="text" name="address" >
                        </div>
                    </div>
                    <div></div>
                    <div class="button-container"><input type="submit" value="등록" ></div>
                </div>
                </form>
            </div>


            <!-- Detail Modal -->
                <div id="partner-detail">
                    <form action='/finance/partner/delete' method="get" onsubmit="return confirm('삭제하시겠습니까?')">
                    <div class="detail-content">
                        <span class="detail-close" onclick="partnerDetailClose();">&times;</span>
                        <div class="modal-title">거래처 상세</div>
                        <div id="required-text"></div>
                        
                        <div class="title-text"></div>
                        
                        <div class="modal-cont">
                            <label for="">거래처 코드</label>
                            <div>
                                <input type="text" name="no" disabled>
                            </div>
                        </div>

                        <div class="modal-cont">
                            <label for="">업종코드</label>
                            <div>
                                <input type="text" name="businessCode" disabled>
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">상호명</label>
                            <div>
                                <input type="text" name="name" disabled>
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">사업자등록번호</label>
                            <div>
                                <input type="text" name="bizRegistNo" disabled>
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">대표자명</label>
                            <div>
                                <input type="text" name="ceoName" disabled>
                            </div>
                        </div>
                        <div class="modal-cont">
                            <label for="">주소</label>
                            <div>
                                <input type="text" name="address" disabled>
                            </div>
                        </div>
                        <div></div>
                        <div class="button-container">
                            <div><input id="partner-edit-btn" type="button" value="수정"></div>
                            <div><input type="submit" value="삭제"></div>
                        </div>
                    </div>
                    </form>
                </div>

                <!-- Edit Modal -->
                <div id="partner-edit">
                    <form id="partner-edit-form" action="/finance/partner/edit" method="post" onsubmit="return confirm('저장하시겠습니까?');">
                        <div class="edit-content">

                            <span class="edit-close" onclick="partnerEditClose();">&times;</span>

                            <div class="modal-title">거래처 수정</div>
                            <div id="required-text">* 는 필수입력사항입니다.</div>

                            <div class="modal-cont">
                                <label for="">거래처코드</label>
                                <div>
                                    <input type="text" name="no" disabled>
                                </div>
                            </div>
                            <div class="modal-cont">
                                <label for="">업종코드</label>
                                <div>
                                    <input type="text" name="businessCode" disabled>
                                </div>
                            </div>
                            <div class="modal-cont">
                                <label for="">상호명</label>
                                <div>
                                    <input type="text" name="name" disabled>
                                </div>
                            </div>
                            <div class="modal-cont">
                                <label for="">사업자등록번호</label>
                                <div>
                                    <input type="text" name="bizRegistNo" disabled>
                                </div>
                            </div>
                            <div class="modal-cont">
                                <label for="">대표자명</label>
                                <div>
                                    <input type="text" name="ceoName" disabled>
                                </div>
                            </div>
                            <div class="modal-cont">
                                <label for="">주소</label>
                                <div>
                                    <input type="text" name="address" disabled>
                                </div>
                            </div>

                            <div></div>
                            <div class="button-container"><input type="submit" value="저장" ></div>
                        </div>

                    </form>
                </div>

            </form>
            </div>
            
        </div>
    </div>
            

</body>
</html>
