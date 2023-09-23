<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/lib.jsp" %>
<%--<%@ include file="addAccount.jsp"%>--%>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="com.example.fit.entities.GrantAccess" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.example.fit.entities.Status" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%--<link href="css/dashboard.css" rel='stylesheet' type='text/css'>--%>
<html>
<head>
    <%
        GrantAccess gr = (GrantAccess) session.getAttribute("admin-role");
    %>
    <style>
        .logout-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: green;
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .logout-button:hover {
            background-color: #e74c3c;
        }
    </style>
    <div class="container">
        <div class="row">
            <div class="col-12 mt-4">
                <h3 class="center-text" style="text-align: center">Xin chào <%=gr.getAccount_id().getFull_name()%>
                </h3>
            </div>
        </div>
    </div>
</head>
<body style="background: cadetblue">
<div class="container">
    <div class="row">
        <div class="col-12 mt-4">
            <table class="table">
                <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">FullName</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody style="background: #f4f4f4">
                <% List<Account> list = (List<Account>) session.getAttribute("dsAcc");
                %>
                <%

                    for (Account dsAccount: list) {

                %>
                <tr>
                    <td><%=dsAccount.getAccount_id()%>
                    </td>
                    <td><%=dsAccount.getFull_name()%>
                    </td>
                    <td><%=dsAccount.getEmail()%>
                    </td>
                    <td><%=dsAccount.getPhone()%>
                    </td>
                    <td>
                        <%
                            Status status = dsAccount.getStatus();
                            String statusText = null;
                            if (status == Status.Active) {
                                statusText = "Active";
                            } else if (status == Status.Inactive) {
                                statusText = "Inactive";
                            } else if (status == Status.Delete) {
                                statusText = "Delete";
                            } else {
                                statusText = "null";
                            }
                        %>
                        <%=statusText%>
                    </td>
                    <td>
                        <button class="btn btn-primary bi bi-trash"
                                onclick=""
                                name="delete">Delete
                        </button>
                        <button class="btn btn-warning bi bi-pencil-square"
                        <%--                                onclick="updateClick(<%grantAccess.getAccount_id().getEmail();%>)"--%>
                                name="update">
                            Update
                        </button>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <form action="logout" method="post">
        <button class="logout-button" name="logout-button">Đăng Xuất</button>
    </form>
    <form action="formAdd" method="post">
        <button class="btn btn-success">Thêm Account</button>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</div>
</body>
</html>
