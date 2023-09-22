<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<link href="css/index.css" rel='stylesheet' type='text/css'>
<html>
<head>
    <title>Your Page</title>
</head>
<body>
<div class="login">
    <h2 class="active"> sign in </h2>

    <h2 class="nonactive"> sign up </h2>
    <form action="ControlServlet?action=login" method="post">
        <input type="text" class="text" name="txtUserName">
        <span>username</span>
        <br>
        <br>
        <input type="password" class="text" name="txtPassWord">
        <span>password</span>
        <br>
        <input type="checkbox" id="checkbox-1-1" class="custom-checkbox"/>
        <label for="checkbox-1-1">Keep me Signed in</label>
        <button class="signin">
            Sign In
        </button>
        <hr>
        <a href="#">Forgot Password?</a>
    </form>

</div>
</body>
</html>