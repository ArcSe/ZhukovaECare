<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
    <c:import url="general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Option Manager</h2>
    <br>
    <h3><a href="/options/new">New Option</a></h3>
    <br>
    <table border="1" cellpadding="5" class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Service Cost</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${listOption}" var="option">
            <tr>
                <td>${option.id}</td>
                <td>${option.name}</td>
                <td>${option.price}</td>
                <td>${option.serviceCost}</td>
                <td>
                    <a href="/options/edit?id=${option.id}">Edit</a>

                    <a href="/options/delete?id=${option.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>