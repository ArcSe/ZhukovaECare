<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tariff Manager</title>
</head>
<body>
<div align="center">
    <h2>Tariff Manager</h2>
    <h3><a href="/tariffs/new">New Tariff</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Service Cost</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${listTariff}" var="tariff">
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.name}</td>
                <td>
                    <a href="/tariffs/edit?id=${tariff.id}">Edit</a>

                    <a href="/tariffs/delete?id=${tariff.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>