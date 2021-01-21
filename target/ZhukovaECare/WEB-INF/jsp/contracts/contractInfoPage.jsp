<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contract Manager</title>
    <c:import url="../general/template.jsp"/>
</head>
<body>
<div class="container">
    <h2>Contract ${contract.number}</h2>
    <br>
    <div>
        <dl class="row">
            <dt class="col-sm-1">ID:</dt>
            <dd class="col-sm-11">${contract.id}</dd>

            <dt class="col-sm-1">Number:</dt>
            <dd class="col-sm-11">${contract.number}</dd>

            <dt class="col-sm-1">Tariff:</dt>
            <dd class="col-sm-11">${contract.tariff.name}</dd>

            <dt class="col-sm-1">ClientID:</dt>
            <dd class="col-sm-11">${contract.clientId}</dd>
        </dl>
    </div>
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
            <c:forEach items="${contract.options}" var="option">
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