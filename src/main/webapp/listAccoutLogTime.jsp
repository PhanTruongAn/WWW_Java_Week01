<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.fit.entities.Status" %>
<%@ page import="com.example.fit.entities.Log" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <style>
        .btn btn-success{
            background-color: green;
            top: 10px;
            right: 10px;
            border-radius: 3px;
            cursor: pointer;
            border: none;
            color: #fff;
        }
        .btn btn-success:hover{
            background-color: #e74c3c;
        }
        a{
            color: #f4f4f4;
        }
        a:hover{
            color: #f4f4f4;
        }
    </style>
</head>
<body style="background: cadetblue">
<div class="container">
    <div class="row">
        <div class="col-12 mt-4">
            <table class="table table-hover">
                <thead class="table-light">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">AccountName</th>
                    <th scope="col">TimeLogin</th>
                    <th scope="col">TimeLogout</th>
                    <th scope="col">Note</th>
                </tr>
                </thead>
                <tbody style="background: #f4f4f4">
                <% List<Log> list = (List<Log>) session.getAttribute("dsTimeLog");
                %>
                <%

                    for (Log dsLog: list) {

                %>
                <tr>
                    <td><%=dsLog.getId()%>
                    </td>
                    <td><%=dsLog.getAccount_log()%>
                    </td>
                    <td><%
                        LocalDateTime timeLog = dsLog.getTime_log();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String timeLogin = timeLog.format(formatter);
                    %>
                        <%=timeLogin%>
                    </td>
                    <td><%
                        LocalDateTime timeOut = dsLog.getTime_out();
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String timeLogout = timeOut.format(formatter);
                    %>
                        <%=timeLogout%>
                    </td>
                    <td>
                        <%=dsLog.getNotes()%>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <button class="btn btn-success"><a style="text-decoration: none" class="corner-link" href="dashboard.jsp">Quay lại menu</a></button>
</div>
</body>
</html>
