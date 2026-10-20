<%--
  Created by IntelliJ IDEA.
  User: Kananchik
  Date: 20.10.2026
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><body>

<h2>SELECT по полю</h2>
<form method="get" action="${pageContext.request.contextPath}/select" accept-charset="UTF-8">
    <select name="type">
        <option value="email" ${param.type == 'email' ? 'selected' : ''}>email</option>
        <option value="city"  ${param.type == 'city'  ? 'selected' : ''}>city</option>
        <option value="phone" ${param.type == 'phone' ? 'selected' : ''}>phone</option>
    </select>
    <input name="value" value="${param.value}" required/>
    <button type="submit">Найти</button>
</form>

<c:if test="${not empty rows}">
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th><th>Name</th><th>Surname</th>
            <th>Age</th><th>Email</th><th>City</th><th>Phone</th>
        </tr>
        <c:forEach items="${rows}" var="u">
            <tr>
                <td>${u.id}</td><td>${u.name}</td><td>${u.surname}</td>
                <td>${u.age}</td><td>${u.email}</td><td>${u.city}</td><td>${u.phone}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body></html>