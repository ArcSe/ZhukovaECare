<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../general/index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional/RUS"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Contract</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>New Contract</h2>
    <form:form action="save" method="post" modelAttribute="contract">
        <div>
            <div class="form-group col-md-4">
                <label for="number">Number</label>
                <form:input type="text" class="form-control" path="number" placeholder="new number"/>
            </div>
            <div class="form-group col-md-4">
                <label >Tariff</label>
                <select  name="tariff.id" class="form-control">
                    <c:forEach items="${tariffs}" var="tariff">
                        <option value="${tariff.id}">${tariff.name}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>