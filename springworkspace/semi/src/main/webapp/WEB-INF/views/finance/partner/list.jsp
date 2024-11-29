
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
                <%@ include file="/WEB-INF/views/common/search.jsp" %>

                <!-- List Area -->
                <%@ include file="/WEB-INF/views/common/list.jsp" %>

                <!-- Bottom Area -->
                <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
            
            </div>

            <!-- Modal Area -->

            <!-- Small modal -->
            <%@ include file="/WEB-INF/views/common/modal.jsp" %> 


        </div>
    </div>
</body>
</html>
