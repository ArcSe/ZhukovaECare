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
        <a class="btn btn-info" href="/contracts/new" role="button">New Contract</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Number</th>
                <th scope="col">Tariff</th>
                <th scope="col">Option</th>
                <th scope="col">ClientId</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contracts}" var="contract">
                <tr>
                    <td scope="row">${contract.id}</td>
                    <td>${contract.number}</td>
                    <td>${contract.tariff}</td>
                    <td>${contract.options}</td>
                    <td>${contract.clientId}</td>
                    <td>
                        <a class="btn btn-light" href="/contracts/edit?id=${contract.id}" role="button">Edit</a>
                        <form id="command" action="/contracts/delete?id=${contract.id}" method="post">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <input type="submit" value="delete">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>