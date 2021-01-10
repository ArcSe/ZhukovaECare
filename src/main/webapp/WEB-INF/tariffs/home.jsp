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
    <br>
    <div>
        <a class="btn btn-info" href="/tariffs/new" role="button">New Tariff</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Option</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listTariff}" var="tariff">
                <c:forEach items="${tariff.options}" var="option">
                    <td>${tariff.id}</td>
                    <td>${tariff.name}</td>
                    <td>${tariff.options}</td>
                    <td>${option.name}</td>
                    <td>${option.price}</td>
                    <td>
                        <a href="/tariffs/edit?id=${tariff.id}">Edit</a>

                        <a href="/tariffs/delete?id=${tariff.id}">Delete</a>
                    </td>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>