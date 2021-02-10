<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <h3 class="text-center"> Option table</h3>
    <div class="row">
        <div>
            <security:authorize access="hasRole('MANAGER')">
            <a  class=" btn btn-primary" href="${pageContext.request.contextPath}/managers/contracts" role="button">Back to Contracts</a>
            </security:authorize>
            <security:authorize access="hasRole('USER')">
                <a  class=" btn btn-primary" href="${pageContext.request.contextPath}/client/userProfile" role="button">Back to Client page</a>
            </security:authorize>
            <c:choose>
                <c:when test="${!contract.locked}">
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/contracts/addOption?id=${contract.id}"
                       role="button">Change Options
                    </a>
                </c:when>
                <c:otherwise>
                    <a  href="${pageContext.request.contextPath}/contracts/addOption?id=${contract.id}" role="button"
                        class="btn btn-danger disabled" tabindex="-1"  aria-disabled="true">
                        Change Options
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Price</th>
                <th scope="col" class="text-center">Service Cost</th>
                <th scope="col" class="text-center">Mandatory Options</th>
                <th scope="col" class="text-center">Banned Options</th>
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
                        <ul>
                            <c:forEach items="${option.mandatoryOptions}" var="mandatoryOption">
                                <li>${mandatoryOption}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${option.bannedOptions}" var="bannedOption">
                                <li>${bannedOption}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>