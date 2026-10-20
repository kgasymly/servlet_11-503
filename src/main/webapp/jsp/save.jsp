<%--
  Created by IntelliJ IDEA.
  User: Kananchik
  Date: 20.10.2026
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Save Users</title></head>
<body>
<h1>Добавить пользователей: ${count}</h1>
<form method="post" action="${pageContext.request.contextPath}/saveUsers" accept-charset="UTF-8">
    <input type="hidden" name="count" value="${count}"/>
    <c:forEach var="i" begin="0" end="${count - 1}">
        <fieldset style="margin-bottom:10px">
            <legend>Пользователь #${i + 1}</legend>
            Name:    <input name="name_${i}"    required/><br/>
            Surname: <input name="surname_${i}"/><br/>
            Age:     <input type="number" name="age_${i}"/><br/>
            Email:   <input name="email_${i}"/><br/>
            City:    <input name="city_${i}"/><br/>
            Phone:   <input name="phone_${i}"/><br/>
        </fieldset>
    </c:forEach>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>
