<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div>
    <div class="container">
        <h2 align="center">Client Manager</h2>
        <br>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/managers/client/new" role="button">New Client</a>
        <div class="col-lg-3 pt-4 pr-5 offset-lg-7">
            <input type="search" class="form-control" path="name"
                   placeholder="Enter name" style="padding: 5px 2px 5px 25px;
                            background: url('http://3.bp.blogspot.com/-4w14hQHr5yQ/Tgm6u7KwUkI/AAAAAAAACAI/Hu2poBOPx3g/s25/search.png')
                            no-repeat scroll 0 50%;"/>
        </div>
        <br>
        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Surname</th>
                <th scope="col" class="text-center">Birthday</th>
                <th scope="col" class="text-center">Passport</th>
                <th scope="col" class="text-center">Email</th>
                <th scope="col" class="text-center">Address</th>
                <th scope="col" class="text-center">Contracts</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listClient}" var="client">
                <tr>
                    <td scope="row" class="text-center">${client.id}</td>
                    <td class="text-center">${client.name}</td>
                    <td class="text-center">${client.surname}</td>
                    <td class="text-center">${client.birthday}</td>
                    <td class="text-center">${client.passport}</td>
                    <td class="text-center">${client.email}</td>
                    <td class="text-center">${client.address}</td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${client.contracts}" var="contract">
                                <li><a href="${pageContext.request.contextPath}/managers/contracts/getById?id=${contract.id}"> ${contract.number}</a></li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">
                        <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/client/getById?id=${client.id}" role="button">Show Details</a>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/managers/client/edit?id=${client.id}" role="button">Edit</a>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/managers/client/delete?id=${client.id}" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>