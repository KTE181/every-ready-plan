<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="/css/login/login.css">
    <script defer src="/js/login/login.js"></script>
</head>
<body>
<div class="login-container">
    <div class="tab">일반</div>
    <form class="login-form" action="/login" method="post">
            <label>E-MAIL</label>
            <input type="text" name="email" placeholder="이메일 입력">
            <label>PWD</label>
            <input type="password" name="pwd" placeholder="비밀번호 입력">
            <input type="submit" class="login-btn" value="로그인">
    </form>
</div>
</body>
</html>
