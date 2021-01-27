<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:import url="general/template.jsp"/>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<div >
    <div class="container pt-2">
        <div class="row justify-content-md-center">
            <h1 class="display-4">Hello!</h1>
            <security:authorize access="!isAuthenticated()">
                <h1 class="display-4">Please,
                    <a href="${pageContext.request.contextPath}/login">login</a>
                    to start </h1>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <h1 class="display-4">  Let's start!</h1>
            </security:authorize>
        </div>
        <div class="row justify-content-md-center">
            <security:authorize access="isAuthenticated()">
                <img src="../../resources/icons/index.png" class="rounded-circle" style="align-content: center" height="400" width="400" alt="hello">
            </security:authorize>
        </div>
    </div>
</div>

</body>
</html>
