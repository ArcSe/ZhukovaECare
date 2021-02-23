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
    <c:if test="${newContractError!=null}">
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
                ${newContractError}
        </div>
    </c:if>

    <form:form action="save" method="post" modelAttribute="contract">
        <div>
            <div class="form-group col-md-4">
                <form:hidden class="form-control"  path="number" />
                <form:hidden class="form-control" path="clientId" />
            </div>
            <c:if test="${clients != null}">
                <div class="form-group col-md-4">
                    <label >Client</label>
                    <select  name="client.id" class="form-control">
                        <c:forEach items="${clients}" var="client">
                            <option value="${client.id}">${client.email}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <div class="form-group col-md-4">
                <label >Tariff</label>
                <select  name="tariff.id" class="form-control">
                    <c:forEach items="${tariffs}" var="tariff">
                        <option value="${tariff.id}">${tariff.name}</option>
                    </c:forEach>
                </select>
            </div>

            <c:choose>
                <c:when test="${newContractError!=null}">
                    <button type="submit" class="btn btn-primary" disabled>Submit</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
</body>
</html>