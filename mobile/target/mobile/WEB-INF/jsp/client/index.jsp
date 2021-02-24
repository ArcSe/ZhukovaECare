<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../general/template.jsp"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title> Ecare</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row px-5">
            <div class="col-lg-2">
                <h1 class="display-4" >Tariffs</h1>
            </div>
        </div>
        <div class="row pt-3 pl-5">
            <c:forEach items="${tariffs}" var="tariff">
                <div class="card my-2 mx-1 an" style="width: 15rem;">
                    <div class="card-body">
                        <h5 class="card-title">${tariff.name}</h5>
                        <p class="card-text" >
                            Options:
                            <br/>
                            <c:forEach items="${tariff.options}" var="option">
                                ${option.name}
                            </c:forEach>
                        </p>
                        <security:authorize access="isAuthenticated()">
                            <a href="#" class="btn btn-dark">Include to new contract </a>
                            <a href="#" class="btn btn-dark">Change tariff in exit contract </a>
                        </security:authorize>
                            <a href="#" class="btn btn-dark">Show more</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
