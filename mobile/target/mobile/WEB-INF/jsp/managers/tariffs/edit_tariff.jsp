<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Tariff</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
<div class="container">
    <h2>Edit Tariff</h2>
    <br>
    <form:form action="edit" method="post" modelAttribute="tariff">
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
            <br/>
            <div>
                <a class="btn btn-light" href="${pageContext.request.contextPath}/managers/tariffs/addOption?id=${tariff.id}" role="button">Change options</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>