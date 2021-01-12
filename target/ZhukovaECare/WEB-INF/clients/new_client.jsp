<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../general/index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/RUS"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Client</title>
    <c:import url="../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>New Client</h2>
    <form:form action="save" method="post" modelAttribute="client">
        <div>
            <div class="form-group col-md-4">
                <label for="name">Name</label>
                <form:input type="text" class="form-control" path="name" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label for="surname">Surname</label>
                <form:input type="text" class="form-control" path="surname" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label for="passport">Passport</label>
                <form:input type="text" class="form-control" path="passport" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label for="email">Email</label>
                <form:input type="email" class="form-control" path="email" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label for="address">Address</label>
                <form:input type="text" class="form-control" path="address" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label for="address">Password</label>
                <form:input type="text" class="form-control" path="password" placeholder="new number"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>