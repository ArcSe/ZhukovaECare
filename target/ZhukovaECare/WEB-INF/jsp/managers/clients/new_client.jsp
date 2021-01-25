<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
    <title>New Client</title>
    <c:import url="../../general/template.jsp"/>
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
                <label for="address">Address</label>
                <form:input type="text" class="form-control" path="address" placeholder="new number"/>
            </div
                <%
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate currentDate = LocalDate.now();
                    request.setAttribute("currentDate", currentDate);
                %>
            <div class="form-group col-md-7 mt-1">
                <div>
                <label for="address">Birthday</label>
                </div>
                <div>
                <input type="date" name="calendar" max=${currentDate} min="1900-01-01"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>