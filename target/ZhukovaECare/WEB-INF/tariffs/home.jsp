<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<c:import url="../general/index.jsp"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tariff Manager</title>
    <c:import url="../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Tariff Manager</h2>
    <h3><a href="/tariffs/new">New Tariff</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Pr</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${listTariff}" var="tariff">
            <td>${tariff.id}</td>
            <td>${tariff.name}</td>
            <td>${tariff.options}</td>
            <c:forEach items="${tariff.options}" var="option">
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.name}</td>
                <td>
                    <a href="/tariffs/edit?id=${tariff.id}">Edit</a>

                    <a href="/tariffs/delete?id=${tariff.id}">Delete</a>
                </td>
            </tr>
            </c:forEach>
            <td>
                <a href="/tariffs/edit?id=${tariff.id}">Edit</a>

                <a href="/tariffs/delete?id=${tariff.id}">Delete</a>
            </td>
        </c:forEach>
    </table>
</div>
</body>
</html>