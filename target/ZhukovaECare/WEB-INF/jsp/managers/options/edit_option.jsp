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
    <form:form action="update" method="post" modelAttribute="option">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">ID: </label>
        <div class="col-sm-7">
            <div>${option.id}</div>
            <form:hidden class="form-control" path="id" />
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name: </label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" path="name"/>
        </div>
    </div>
        <div class="form-group row">
            <label for="price" class="col-sm-2 col-form-label">Price: </label>
            <div class="col-sm-4">
                <form:input type="text" class="form-control" path="price"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="serviceCost" class="col-sm-2 col-form-label">Service Cost: </label>
            <div class="col-sm-4">
                <form:input type="text" class="form-control" path="serviceCost"/>
            </div>
        </div>
        <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/options/editMandatoryOptions?id=${option.id}" role="button">Edit Mandatory Options</a>
        <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/options/editBannedOptions?id=${option.id}" role="button">Edit Banned Options</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
</body>
</html>