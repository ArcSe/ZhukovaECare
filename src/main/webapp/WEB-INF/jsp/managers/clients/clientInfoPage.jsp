<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client profile</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-4">
            <h1 class="display-4">${client.name} ${client.surname}</h1>
        </div>
    </div>
    <dl class="row">
        <dt class="col-sm-1">Birthday:</dt>
        <dd class="col-sm-11">${client.birthday}</dd>

        <dt class="col-sm-1">Passport:</dt>
        <dd class="col-sm-11">${client.passport}</dd>

        <dt class="col-sm-1">Address:</dt>
        <dd class="col-sm-11">${client.address}</dd>

        <dt class="col-sm-1">Email:</dt>
        <dd class="col-sm-11">${client.email}</dd>
    </dl>
    <div class="row">
        <div>
                <a  class=" btn btn-primary" href="${pageContext.request.contextPath}/managers/client" role="button">Back to Clients</a>
                <a class="btn btn-info" href="${pageContext.request.contextPath}/managers/client/addContract?id=${client.id}" role="button">Add Contracts</a>
        </div>
    </div>
    <h3 class="text-center"> Contract Table</h3>
    <table class="table pt-5">
        <thead>
        <tr>
            <th scope="col" class="text-center">#</th>
            <th scope="col" class="text-center">Number</th>
            <th scope="col" class="text-center">Tariff</th>
            <th scope="col" class="text-center">Options</th>
            <th scope="col" class="text-center">Locked</th>
            <th scope="col" class="text-center">ByAdmin</th>
            <th scope="col" class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${client.contracts}" var="contract">
            <tr>
                <td class="text-center">${contract.id}</td>
                <td class="text-center">${contract.number}</td>
                <td class="text-center">${contract.tariff.name}</td>
                <td class="text-center">
                    <ul>
                        <c:forEach items="${contract.options}" var="option">
                            <li>${option.name}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td class="text-center">${contract.locked}</td>
                <td class="text-center">${contract.lockedByAdmin}</td>
                <td class="text-center">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/managers/contracts/getById?id=${contract.id}" role="button">Show details</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
