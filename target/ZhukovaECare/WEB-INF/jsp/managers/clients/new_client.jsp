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
    <form:form action="new" method="post" modelAttribute="client">
        <div>
            <div class="form-group col-md-4">
                <label for="name">Name</label>
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
            <div class="form-group col-md-4">
                <label for="surname">Surname</label>
                <c:choose>
                    <c:when test="${surnameError==null}">
                        <form:input  class="form-control" type="text" path="surname" placeholder="Surname"/>
                    </c:when>
                    <c:otherwise>
                        <form:input  class="form-control is-invalid " type="text" path="surname" placeholder="Surname"/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${surnameError!=null}">
                    <div class="invalid-feedback">
                            ${surnameError}
                    </div>
                </c:if>
            </div>
            <div class="form-group col-md-4">
                <label for="passport">Passport</label>
                <c:choose>
                    <c:when test="${passportError==null}">
                        <form:input  class="form-control" type="text" path="passport" placeholder="Passport"/>
                    </c:when>
                    <c:otherwise>
                        <form:input  class="form-control is-invalid " type="text" path="passport" placeholder="Passport"/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${passportError!=null}">
                    <div class="invalid-feedback">
                            ${passportError}
                    </div>
                </c:if>
            </div>
            <div class="form-group col-md-4">
                <label for="address">Address</label>
                <c:choose>
                    <c:when test="${addressError==null}">
                        <form:input  class="form-control" type="text" path="address" placeholder="Address"/>
                    </c:when>
                    <c:otherwise>
                        <form:input  class="form-control is-invalid " type="text" path="address" placeholder="Address"/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${addressError!=null}">
                    <div class="invalid-feedback">
                            ${addressError}
                    </div>
                </c:if>
            </div
                <%
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate currentDate = LocalDate.now();
                    request.setAttribute("currentDate", currentDate.minusYears(18));
                %>
            <div class="form-group col-md-7 mt-1">
                <div>
                <label for="address">Birthday</label>
                </div>
                <div class="form-group col-md-4">
                    <c:choose>
                        <c:when test="${birthdayError==null}">
                            <input type="date" name="calendar" class="form-control" max=${currentDate} min="1900-01-01"/>
                        </c:when>
                        <c:otherwise>
                            <input type="date" name="calendar" class="form-control is-invalid" max=${currentDate} min="1900-01-01"/>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${birthdayError!=null}">
                        <div class="invalid-feedback">
                                ${birthdayError}
                        </div>
                    </c:if>
                </div>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>