<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../general/index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/RUS"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Contracts</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Add Contract to Client ${client.name}</h2>
    <div class="form-group col-md-4">
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" class="text-center">#</th>
                    <th scope="col" class="text-center">Number</th>
                    <th scope="col" class="text-center">Tariff</th>
                    <th scope="col" class="text-center">Option</th>
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
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${client.contracts.contains(contract)}">
                                <form action="${pageContext.request.contextPath}/managers/client/deleteContract" method="post">
                                    <input type="hidden" name="contractId" value="${contract.id}"/>
                                    <input type="hidden" name="clientId" value="${client.id}"/>
                                    <button type="submit" class="btn btn-warning">Delete</button>
                                </form>
                            </c:when>
                            <c:when test="${contract.clientId != null}">
                                <button type="submit" class="btn btn-warning" disabled>Add</button>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/managers/client/addContract" method="post">
                                    <input type="hidden" name="contractId" value="${contract.id}"/>
                                    <input type="hidden" name="clientId" value="${client.id}"/>
                                    <button type="submit" class="btn btn-danger">Add</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <a href="${pageContext.request.contextPath}/managers/client/getById?id=${client.id}" class="btn btn-primary">Back to client</a>
</div>
</div>
</body>
</html>