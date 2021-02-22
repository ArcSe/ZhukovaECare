<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Option Manager</h2>
    <br>
    <div>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/managers/options/new" role="button">New Option</a>
    </div>
    <br>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Price</th>
                <th scope="col" class="text-center">Service Cost</th>
                <th scope="col" class="text-center">Mandatory Options</th>
                <th scope="col" class="text-center">Banned Options</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOption}" var="option">
                <tr class="text-center">
                    <td class="text-center">${option.name}</td>
                    <td class="text-center">${option.price}</td>
                    <td class="text-center">${option.serviceCost}</td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${option.mandatoryOptions.values()}" var="mandatoryOption">
                                <li>${mandatoryOption}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">
                        <ul>
                            <c:forEach items="${option.bannedOptions.values()}" var="bannedOption">
                                <li>${bannedOption}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="text-center">
                        <div class="row row-cols-auto">
                            <div class="col">
                                <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/options/edit?id=${option.id}" role="button">Edit</a>
                            </div>
                            <div>
                                <form action="${pageContext.request.contextPath}/managers/options/delete?id=${option.id}" method="post">
                                    <input type="hidden" name="optionId" value="${option.id}"/>
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>