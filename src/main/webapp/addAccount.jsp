<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="com.example.fit.entities.GrantAccess" %>
<%@ page import="java.util.List" %>
<%@include file="common/lib.jsp" %>
<link href="css/addAccount.css" rel='stylesheet' type='text/css'>
<html>
<head>
    <title>Thêm Account</title>
</head>
<body>
<%
    GrantAccess gr = (GrantAccess) session.getAttribute("admin-role");
%>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }

    header {
        background-color: #007bff;
        color: #fff;
        padding: 20px;
        text-align: center;
    }

    h1 {
        margin: 0;
    }

    .container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    input[type="text"], input[type="password"], select {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    button {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button.cancel {
        background-color: #ccc;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
<header>
    <h1>Thêm Account</h1>
</header>
<div class="container">
    <form action="addAccount" method="post">
        <label>Họ và Tên:</label>
        <input type="text" name="fullName" class="text" required>

        <label>Số điện thoại:</label>
        <input type="text" name="phone" class="text" required>

        <label>Tên đăng nhập:</label>
        <input type="text" name="username" class="text" required>

        <label>Mật khẩu:</label>
        <input type="password" name="password" class="text" required>

        <label>Email:</label>
        <input type="text" name="email" class="text" required>
        <button type="submit" name="submit" value="submit">Thêm</button>
    </form>
    <form action="cancel" method="get">
        <button class="cancel" name="cancel">Hủy</button>
    </form>
</div>
</body>
</html>
