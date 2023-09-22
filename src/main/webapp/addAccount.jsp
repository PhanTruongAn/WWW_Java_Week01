<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="com.example.fit.entities.GrantAccess" %>
<link href="css/addAccount.css" rel='stylesheet' type='text/css'>
<html>
<head>
    <title>Thêm Account</title>
</head>
<body>
<header>
    <h1>Thêm Account</h1>
</header>
<div class="container">
    <form action="ControlServlet?action=submitAddAccount" method="post">
        <label >Họ và Tên:</label>
        <input type="text"  name="fullName" required>

        <label>Số điện thoại:</label>
        <input type="text"  name="phone" required>

        <label >Tên đăng nhập:</label>
        <input type="text"  name="username" required>

        <label >Status:</label>
        <input type="text"  name="status" required>

        <label >Mật khẩu:</label>
        <input type="password"  name="password" required>

        <label >Email:</label>
        <input type="text"  name="email" required>
            <button type="submit" name="add">Thêm</button>
            <button type="button" class="cancel" onclick="">Hủy</button>
    </form>
</div>
</body>
</html>
