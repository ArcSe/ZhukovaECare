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
    <title>Add Option</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Add Options</h2>
    <div class="col-sm-7">
        <input type="hidden" name="contract.id" value="${contract.id}"/>
    </div>
    <div class="form-group col-md-4">
        <label >Options</label>
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
            <c:forEach items="${options}" var="option">
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
                        <c:choose>
                            <c:when test="${contract.options.contains(option)}">
                                <form action="${pageContext.request.contextPath}/managers/contracts/deleteOption" method="post">
                                    <input type="hidden" name="contractId" value="${contract.id}"/>
                                    <input type="hidden" name="optionId" value="${option.id}"/>
                                    <button type="submit" class="btn btn-warning">Delete</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/managers/contracts/addOption" method="post">
                                    <input type="hidden" name="contractId" value="${contract.id}"/>
                                    <input type="hidden" name="optionId" value="${option.id}"/>
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
    <a href="/managers/contracts" class="btn btn-primary">Back to Contracts</a>
</div>
</div>
</body>
</html>