<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
    <c:import url="../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Client Manager</h2>
    <br>
    <div>
        <a class="btn btn-info" href="/clients/new" role="button">New Client</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Passport</th>
                <th scope="col">Email</th>
                <th scope="col">Address</th>
                <th scope="col">Contracts</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listClient}" var="client">
                <tr>
                    <td scope="row">${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.surname}</td>
                    <td>${client.passport}</td>
                    <td>${client.email}</td>
                    <td>${client.address}</td>
                    <td>${client.contracts}</td>
                    <td>
                        <a class="btn btn-light" href="/clients/edit?id=${client.id}" role="button">Edit</a>

                        <a class="btn btn-danger" href="/clients/delete?id=${client.id}" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>