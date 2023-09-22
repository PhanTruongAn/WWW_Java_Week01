<%--
  Created by IntelliJ IDEA.
  User: My Pc
  Date: 14/09/2023
  Time: 1:33 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/lib.jsp"%>
<%@ page import="com.example.fit.entities.GrantAccess" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <h1>Hello Admin</h1>
  <%
    GrantAccess gr = (GrantAccess) request.getAttribute("user");
  %>
  <td><%=gr.getAccount_id().getAccount_id()%>
  </td>
  <td><%=gr.getAccount_id().getFull_name()%>
  </td>
  <td><%=gr.getAccount_id().getEmail()%>
  </td>
  <td><%=gr.getAccount_id().getPhone()%>
  </td>
  <td><%=gr.getRole_id().getRole_name()%>
  </td>
  <td><%=gr.getAccount_id().getStatus()%>
  </td>
</head>
<body>

</body>
</html>
