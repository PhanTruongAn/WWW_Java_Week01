<%--
  Created by IntelliJ IDEA.
  User: My Pc
  Date: 20/09/2023
  Time: 7:55 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/lib.jsp" %>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="com.example.fit.entities.GrantAccess" %>

<%--<link href="css/user.css" rel='stylesheet' type='text/css'>--%>
<html>
<head>
    <%
        Account ac = (Account) session.getAttribute("userLogin");
        if (ac == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
        }

        .info {
            margin-bottom: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        label {
            font-weight: bold;
            width: 100px;
        }

        p {
            margin: 0;
            padding: 0;
        }

        .status {
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 3px;
            color: #fff;
        }

        .status-active {
            background-color: #2ecc71;
        }

        .status-deactive {
            background-color: #f1c40f;
        }

        .status-delete {
            background-color: #e74c3c;
        }
        .logout-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #3498db;
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .logout-button:hover {
            background-color: #2980b9;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Xin chào <%=ac.getFull_name()%>
    </h1>
    <div class="info">
        <label>ID:</label>
        <p name="userID"><%=ac.getAccount_id()%>
        </p>
    </div>
    <div class="info">
        <label for="fullname">Full Name:</label>
        <p id="fullname"><%=ac.getFull_name()%>
        </p>
    </div>
    <div class="info">
        <label for="email">Email:</label>
        <p id="email"><%=ac.getEmail()%>
        </p>
    </div>
    <div class="info">
        <label for="phone">Phone:</label>
        <p id="phone"><%=ac.getPhone()%>
        </p>
    </div>
    <div class="info">
        <label for="status">Status:</label>
        <p id="status" class="status status-active">Active</p>
    </div>
    <form action="logout" method="post">
        <button class="logout-button">Đăng Xuất</button>
    </form>
</div>
</body>

</html>
