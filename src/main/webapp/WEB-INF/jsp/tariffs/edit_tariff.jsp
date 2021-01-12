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
    <h2>Edit Option</h2>
    <br>
    <form:form action="update" method="post" modelAttribute="tariff">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">ID: </label>
        <div class="col-sm-7">
            <div>${tariff.id}</div>
            <form:hidden class="form-control" path="id" />
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name: </label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" path="name"/>
        </div>
        <div class="form-group col-md-4">
            <label >State</label>
            <select  name="option.id" class="form-control">
                <c:forEach items="${options}" var="option">
                    <option value="${option.id}">${option.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
</body>
</html>