<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../general/index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/RUS"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Client</title>
    <c:import url="../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Add Client</h2>
    <form:form action="updateOptions" method="post" modelAttribute="tariff">
    <div class="col-sm-7">
        <div>${tariff.id}</div>
        <input type="hidden" name="tariff.id" value="${tariff.id}"/>
    </div>
    <div class="form-group col-md-4">
        <label >Options</label>
        <select  name="option.id" class="form-control">
            <c:forEach items="${options}" var="option">
                <option value="${option.id}">${option.name}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</div>
</form:form>
</div>
</body>
</html>