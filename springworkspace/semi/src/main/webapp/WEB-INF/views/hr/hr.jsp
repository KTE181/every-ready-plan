
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EVERY READY PLAN</title>
    <link rel="stylesheet" href="/css/pb/pb.css">
    <link rel="stylesheet" href="/css/common/index.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>
    <script defer src="/js/hr/hr.js"></script>
    <link rel="stylesheet" href="/css/hr/hr.css">

</head>
<body>
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

                    <canvas id="myChart"></canvas>
                
                </div>
    
                <!-- Modal Area -->
    
                <!-- Small modal -->
                <%@ include file="/WEB-INF/views/common/modal.jsp" %> 
    
    
            </div>
        </div>
    </body>
</body>
</html>
