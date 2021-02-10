<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../general/template.jsp"%>
<html>
<head>
    <title>Personal profile</title>
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
        <div class="pt-4">
            <a class="btn btn-light" href="/client/contract?id=${client.id}" role="button">New contract</a>
        </div>
        <br>
        <table class="table pt-5">
            <thead>
            <tr>
                <th scope="col">Number</th>
                <th scope="col">Tariff</th>
                <th scope="col">Options</th>
                <th scope="col">Locked</th>
                <th scope="col">ByAdmin</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${client.contracts}" var="contract">
                    <tr>
                        <td>${contract.number}</td>
                        <td>${contract.tariff.name}</td>
                        <td>
                            <ul>
                            <c:forEach items="${contract.options}" var="option">
                                <li>${option.name}</li>
                            </c:forEach>
                            </ul>
                        </td>
                        <td>${contract.locked}</td>
                        <td>${contract.lockedByAdmin}</td>
                        <td>
                            <c:choose>
                                <c:when test="${!contract.locked}">
                                    <a class="btn btn-light" href="/client/addTariff?id=${contract.id}" role="button">Change tariff</a>
                                    <a class="btn btn-danger" href="/client/contractInfo?id=${contract.id}" role="button">Show details</a>
                                </c:when>
                                <c:otherwise>
                                    <a  href="/client/addTariff?id=${contract.id}" role="button" class="btn btn-light disabled"
                                        tabindex="-1"  aria-disabled="true">Change tariff</a>
                                    <a href="/client/contractInfo?id=${contract.id}" role="button"class="btn btn-danger disabled"
                                       tabindex="-1"  aria-disabled="true">
                                        Show details
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${!contract.locked}">
                                    <a class="btn btn-primary" href="/client/lockedContract?contractId=${contract.id}" role="button">
                                        Lock
                                    </a>
                                </c:when>
                                <c:when test="${contract.lockedByAdmin}">
                                    <a  href="/client/lockedContract?contractId=${contract.id}" role="button"
                                        class="btn btn-danger disabled" tabindex="-1"  aria-disabled="true">
                                        Unlock
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a  href="/client/lockedContract?contractId=${contract.id}" role="button" class="btn btn-danger">
                                        Unlock
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
