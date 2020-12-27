<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
</head>
<body>
<div align="center">
    <h2>Option Manager</h2>
    <form method="get" action="search">
        <input type="text" name="keyword" />
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/new">New Option</a></h3>
    <table border="1" cellpadding="5">
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
                    <a href="/edit?id=${option.id}">Edit</a>

                    <a href="/delete?id=${option.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>