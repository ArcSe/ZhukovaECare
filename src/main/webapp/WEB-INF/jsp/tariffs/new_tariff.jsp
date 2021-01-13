<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../general/template.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/RUS"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Tariff</title>
</head>
<body>
<div align="center">
    <h2>New Tariff</h2>
    <form:form action="save" method="post" modelAttribute="tariff">
        <div>
            <div class="form-group col-md-4">
                <label for="name">Name</label>
                <form:input type="text" class="form-control" path="name" placeholder="new name"/>
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
        </div>
    </form:form>
</div>
</body>
</html>