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

<link href="css/user.css" rel='stylesheet' type='text/css'>
<html>
<head>
    <%
        Account ac = (Account) request.getAttribute("userLogin");
        if (ac == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
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
    <form action="ControlServlet?action=logout" method="post">
        <button class="logout-button">Đăng Xuất</button>
    </form>
</div>
</body>

</html>
