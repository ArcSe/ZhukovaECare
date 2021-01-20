<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Option</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div class="container">
    <h2>Edit User</h2>
    <br>
    <form:form action="updateUser" method="post" modelAttribute="user">
    <div class="form-group row">
        <label for="id" class="col-sm-2 col-form-label">ID: </label>
        <div class="col-sm-7">
            <div>${user.id}</div>
            <form:hidden class="form-control" path="id" />
            <form:hidden class="form-control" path="password" />
            <form:hidden class="form-control" path="active" />
        </div>
    </div>
    <div class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">Name: </label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" path="email"/>
        </div>
    </div>
        <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Roles: </label>
            <div class="col-sm-4">
                <c:forEach items="${user.roles}" var="role">
                    <div class="form-check">
                        <form:checkboxes path="roles" items="${roles}" />
                    </div>
                    <br>
                </c:forEach>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
</body>
</html>