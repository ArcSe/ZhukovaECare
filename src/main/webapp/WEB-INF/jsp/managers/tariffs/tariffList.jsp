<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<c:import url="../../general/template.jsp"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tariff Manager</title>
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
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Option</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listTariff}" var="tariff">
                    <tr>
                        <td class="text-center">${tariff.id}</td>
                        <td class="text-center">${tariff.name}</td>
                        <td class="text-center">
                            <ul>
                                <c:forEach items="${tariff.options}" var="option">
                                    <li>${option.name}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td class="text-center">
                            <a class="btn btn-light" href="/managers/tariff/getById?id=${tariff.id}" role="button">Show Details</a>
                            <a class="btn btn-primary" href="/managers/tariffs/edit?id=${tariff.id}" role="button">Edit</a>
                            <a class="btn btn-danger" href="/managers/tariffs/delete?id=${tariff.id}" role="button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>