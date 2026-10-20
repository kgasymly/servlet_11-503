<%--
  Created by IntelliJ IDEA.
  User: Kananchik
  Date: 19.10.2026
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Settings</title>
</head>
<body>
<h1 style="color: ${cookie.get("color_cookie").value}">Hello!</h1>
<div>
    <form action="${pageContext.request.contextPath}/settings" method="post">
        <select size="3" name="color">
            <option value="red">Red</option>
            <option value="green">Green</option>
            <option value="black">Black</option>
            <option value="blue">Blue</option>
        </select>
        <input type="submit" value="Save color">
    </form>
</div>
</body>
</html>