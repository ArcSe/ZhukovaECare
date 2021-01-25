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
    <title>Change Client</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div align="center">
    <h2>Change Client</h2>
        <form:form action="updateClient" method="post" modelAttribute="contract">
            <div class="col-sm-7">
                <div>${contract.id}</div>
                <input type="hidden" name="contract.id" value="${contract.id}"/>
            </div>
            <div class="form-group col-md-4">
                <label >Clients</label>
                <select  name="client.id" class="form-control">
                    <c:forEach items="${clients}" var="client">
                        <option value="${client.id}">${client.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>