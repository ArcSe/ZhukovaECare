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
        <a class="btn btn-info" href="/managers/tariffs/new" role="button">New Tariff</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col-lg-2" class="text-center">Name</th>
                <th scope="col-lg-2" class="text-center">Price</th>
                <th scope="col-lg-2" class="text-center">Option</th>
                <th scope="col-lg-4" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listTariff}" var="tariff">
                    <tr>
                        <td class="text-center">${tariff.name}</td>
                        <td class="text-center">${tariff.price}</td>
                        <td class="text-center">
                            <ul>
                                <c:forEach items="${tariff.options}" var="option">
                                    <li>${option.name}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td class="text-center">
                            <form action="${pageContext.request.contextPath}/managers/tariffs/delete" method="post">
                                <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/tariffs/getById?id=${tariff.id}" role="button">Show Details</a>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/managers/tariffs/edit?id=${tariff.id}" role="button">Edit</a>
                                <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>