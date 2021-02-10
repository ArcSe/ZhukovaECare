<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Option</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div class="container">
    <h2>New Option</h2>
    <br>
        <form:form action="new" method="post" modelAttribute="option">
            <div class="form-group row">
                <div class="input-group">
                    <label for="name" class="col-sm-2 col-form-label">Name: </label>
                    <div class="col-sm-4">
                        <c:choose>
                            <c:when test="${nameError==null}">
                                <form:input  class="form-control" type="text" path="name" placeholder="Name"/>
                            </c:when>
                            <c:otherwise>
                                <form:input  class="form-control is-invalid " type="text" path="name" placeholder="Name"/>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${nameError!=null}">
                            <div class="invalid-feedback">
                                    ${nameError}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="price" class="col-sm-2 col-form-label">Price: </label>
                <div class="col-sm-4">
                    <c:choose>
                        <c:when test="${priceError==null}">
                            <form:input  class="form-control" type="number" path="price" placeholder="Price"/>
                        </c:when>
                        <c:otherwise>
                            <form:input  class="form-control is-invalid " type="number" path="price" placeholder="Price"/>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${priceError!=null}">
                        <div class="invalid-feedback">
                                ${priceError}
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="form-group row">
                <label for="serviceCost" class="col-sm-2 col-form-label">Service Cost: </label>
                <div class="col-sm-4">
                    <c:choose>
                        <c:when test="${serviceCostError==null}">
                            <form:input  class="form-control" type="number" path="serviceCost" placeholder="Service Cost"/>
                        </c:when>
                        <c:otherwise>
                            <form:input  class="form-control is-invalid " type="number" path="serviceCost" placeholder="Service Cost"/>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${serviceCostError!=null}">
                        <div class="invalid-feedback">
                                ${serviceCostError}
                        </div>
                    </c:if>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
</div>
</body>
</html>