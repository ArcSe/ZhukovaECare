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
    <title>Add Client</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Add Mandatory Options</h2>
    <div class="col-sm-7">
        <input type="hidden" name="option.id" value="${option.id}"/>
    </div>
    <div class="form-group col-md-4">
        <label >Mandatory Options</label>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" class="text-center">#</th>
                    <th scope="col" class="text-center">Name</th>
                    <th scope="col" class="text-center">Price</th>
                    <th scope="col" class="text-center">Service Cost</th>
                    <th scope="col" class="text-center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${options}" var="optionMad">
                    <tr>
                        <td scope="row" class="text-center">${optionMad.id}</td>
                        <td class="text-center">${optionMad.name}</td>
                        <td class="text-center">${optionMad.price}</td>
                        <td class="text-center">${optionMad.serviceCost}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${option.mandatoryOptions.contains(optionMad.id)}">
                                    <form action="${pageContext.request.contextPath}/managers/options/deleteMandatoryOption" method="post">
                                            <input type="hidden" name="mandatoryOptionId" value="${optionMad.id}"/>
                                            <input type="hidden" name="optionId" value="${option.id}"/>
                                            <button type="submit" class="btn btn-warning">Delete</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/managers/options/addMandatoryOption" method="post">
                                        <input type="hidden" name="mandatoryOptionId" value="${optionMad.id}"/>
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
    <a href="/managers/options" class="btn btn-primary">Back to options</a>
</div>
</div>
</body>
</html>