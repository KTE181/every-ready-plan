
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/pb/employee.css">
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
                  <%@ include file="/WEB-INF/views/pb/employee/search.jsp" %>
                    <table border=>
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
                            <c:forEach var="employee" items="${employeeVoList}">
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




        </div>
    </div>
</body>
</html>
