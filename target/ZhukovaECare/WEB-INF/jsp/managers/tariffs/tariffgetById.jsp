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
    <h2>Tariff ${tariff.name}</h2>
    <br>
    <div>
        <dl class="row">
            <dt class="col-sm-1">ID:</dt>
            <dd class="col-sm-11">${tariff.id}</dd>

            <dt class="col-sm-1">Name:</dt>
            <dd class="col-sm-11">${tariff.name}</dd>

            <dt class="col-sm-1">Price:</dt>
            <dd class="col-sm-11">${tariff.price}</dd>
        </dl>
    </div>
    <h1 style="text-align: center"> Tariff options</h1>
    <div>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/managers/tariffs/addOption?id=${tariff.id}" role="button">Add Options</a>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/managers/tariffs" role="button">Back to Tariffs</a>
    </div>
    <div class="container mt-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="text-center">#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Price</th>
                <th scope="col" class="text-center">Service Cost</th>
                <th scope="col" class="text-center">Mandatory Options</th>
                <th scope="col" class="text-center">Banned Options</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tariff.options}" var="option">
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
                    <td class="text-center">
                        <form action="${pageContext.request.contextPath}/managers/tariffs/removeOption" method="post">
                            <input type="hidden" name="optionId" value="${option.id}"/>
                            <input type="hidden" name="tariffId" value="${tariff.id}"/>
                            <%
                                boolean isFrom = false;
                                request.setAttribute("isFrom", isFrom);
                            %>
                            <input type="hidden" name="isFromAddForm" value="${isFrom}"/>
                            <button type="submit" class="btn btn-warning">Delete</button>
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