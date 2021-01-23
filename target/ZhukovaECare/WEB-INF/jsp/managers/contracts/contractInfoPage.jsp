<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contract Manager</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div class="container">
    <h2>Contract #${contract.number}</h2>
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
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Price</th>
                <th scope="col" class="text-center">Service Cost</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contract.options}" var="option">
                <tr>
                    <td scope="row" class="text-center">${option.id}</td>
                    <td class="text-center">${option.name}</td>
                    <td class="text-center">${option.price}</td>
                    <td class="text-center">${option.serviceCost}</td>
                    <td class="text-center">
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