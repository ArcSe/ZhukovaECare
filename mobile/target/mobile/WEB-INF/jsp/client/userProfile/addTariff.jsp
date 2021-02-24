<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<c:import url="../../general/template.jsp"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tariff Manager</title>
</head>
<body>
<div align="center">
    <h2>Tariffs </h2>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col-lg-2" class="text-center">#</th>
                <th scope="col-lg-2" class="text-center">Name</th>
                <th scope="col-lg-2" class="text-center">Price</th>
                <th scope="col-lg-2" class="text-center">Option</th>
                <th scope="col-lg-4" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listTariff}" var="tariff">
                <tr>
                    <td class="text-center">${tariff.id}</td>
                    <td class="text-center">${tariff.name}</td>
                    <td class="text-center">${tariff.price}</td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${tariff.options}" var="option">
                                <li>${option.name}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${contract.tariff == tariff}">
                                <form action="${pageContext.request.contextPath}/client/addTariff" method="post">
                                    <button type="submit"  class="btn btn-danger disabled" tabindex="-1"
                                            aria-disabled="true">
                                        Chosen
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/client/addTariff" method="post">
                                    <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                    <input type="hidden" name="contractId" value="${contract.id}"/>
                                    <button type="submit" class="btn btn-danger">Choose </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>