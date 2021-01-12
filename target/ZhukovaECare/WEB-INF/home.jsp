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
    <div>
        <a class="btn btn-info" href="/options/new" role="button">New Option</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Service Cost</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOption}" var="option">
                <tr>
                    <td scope="row">${option.id}</td>
                    <td>${option.name}</td>
                    <td>${option.price}</td>
                    <td>${option.serviceCost}</td>
                    <td>
                        <a class="btn btn-light" href="/options/edit?id=${option.id}" role="button">Edit</a>

                        <a class="btn btn-danger" href="/options/delete?id=${option.id}" method = "DELETE" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>