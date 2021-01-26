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
                <h1 class="display-4">${user.client.name} ${user.client.surname}</h1>
            </div>
            <div class="pt-4">
                <a class="btn btn-light" href="#" role="button">Setting</a>
            </div>
        </div>
        <dl class="row">
            <dt class="col-sm-1">Birthday:</dt>
            <dd class="col-sm-11">${user.client.birthday}</dd>

            <dt class="col-sm-1">Passport:</dt>
            <dd class="col-sm-11">${user.client.passport}</dd>

            <dt class="col-sm-1">Address:</dt>
            <dd class="col-sm-11">${user.client.address}</dd>

            <dt class="col-sm-1">Email:</dt>
            <dd class="col-sm-11">${user.email}</dd>
        </dl>
        <table class="table pt-5">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Number</th>
                <th scope="col">Tariff</th>
                <th scope="col">Options</th>
                <th scope="col">Actions</th>
                <th scope="col">Locked</th>
                <th scope="col">ByAdmin</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${user.client.contracts}" var="contract">
                    <tr>
                        <td>${contract.id}</td>
                        <td>${contract.number}</td>
                        <td>${contract.tariff.name}</td>
                        <td>${contract.options}</td>
                        <td>${contract.locked}</td>
                        <td>${contract.lockedByAdmin}</td>
                        <td>
                            <c:choose>
                                <c:when test="${!contract.locked}">
                                    <a class="btn btn-danger" href="#" role="button">Show details</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" role="button"class="btn btn-danger disabled" tabindex="-1"  aria-disabled="true">
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
                                <c:otherwise>
                                    <a  href="/client/lockedContract?contractId=${contract.id}" role="button" class="btn btn-danger disabled" tabindex="-1"  aria-disabled="true">
                                        Lock
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
