<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/lib.jsp" %>
<%@ page import="com.example.fit.entities.Account" %>
<%@ page import="com.example.fit.entities.GrantAccess" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="css/dashboard.css" rel='stylesheet' type='text/css'>
<html>
<head>
    <%
        GrantAccess gr = (GrantAccess) request.getAttribute("admin-role");

    %>
    <div class="container">
        <div class="row">
            <div class="col-12 mt-4">
                <h3 class="center-text" style="text-align: center">Xin chào <%=gr.getAccount_id().getFull_name()%>
                </h3>
                <!-- Các dòng HTML khác ở đây -->
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
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody style="background: #f4f4f4">
                <% List<GrantAccess> list = (List<GrantAccess>) request.getAttribute("dsAcc");%>
                <%
                    for (int i = 0; i < list.size(); i++) {
                        GrantAccess dsAccount = list.get(i);

                %>
                <tr>
                    <td><%=dsAccount.getAccount_id().getAccount_id()%>
                    </td>
                    <td><%=dsAccount.getAccount_id().getFull_name()%>
                    </td>
                    <td><%=dsAccount.getAccount_id().getEmail()%>
                    </td>
                    <td><%=dsAccount.getAccount_id().getPhone()%>
                    </td>
                    <td>

                        <select class="form-select" aria-label="Default select example">
                            <option selected><%=dsAccount.getRole_id().getRole_name()%>
                            </option>
                            <option value="1">Customer</option>
                            <option value="2">User</option>
                        </select>
                    </td>
                    <td><%=(dsAccount.getAccount_id().getStatus() == 1) ? "active" :
                            (dsAccount.getAccount_id().getStatus() == 0) ? "deactive" :
                                    (dsAccount.getAccount_id().getStatus() == -1) ? "xóa" : "null"
                    %>
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
    <form action="ControlServlet?action=logout" method="post">
        <!-- ... -->
        <button class="logout-button">Đăng Xuất</button>
    </form>
    <form action="ControlServlet?action=addAccount" method="post">
        <button class="btn btn-success">Thêm Account</button>
    </form>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
