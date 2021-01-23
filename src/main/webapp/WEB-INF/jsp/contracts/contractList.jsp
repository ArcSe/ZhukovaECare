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
<div align="center">
    <h2>Contract Manager</h2>
    <br>
    <div>
        <a class="btn btn-info" href="/managers/contracts/new" role="button">New Contract</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Number</th>
                <th scope="col" class="text-center">Tariff</th>
                <th scope="col" class="text-center">Option</th>
                <th scope="col" class="text-center">ClientId</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contracts}" var="contract">
                <tr>
                    <td scope="row" class="text-center">${contract.id}</td>
                    <td class="text-center">${contract.number}</td>
                    <td class="text-center">${contract.tariff.name}</td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${contract.options}" var="option">
                                <li>${option.name}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">${contract.clientId}</td>
                    <td class="text-center">
                        <a class="btn btn-light" href="/managers/contracts/addClient?id=${contract.id}" role="button">Add Client</a>
                        <a class="btn btn-light" href="/managers/contracts/getById?id=${contract.id}" role="button">Show Details</a>
                        <a class="btn btn-primary" href="/managers/contracts/edit?id=${contract.id}" role="button">Edit</a>
                        <a class="btn btn-danger" href="/admin/lockedContract?contractId=${contract.id}" method ="post" role="button">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>