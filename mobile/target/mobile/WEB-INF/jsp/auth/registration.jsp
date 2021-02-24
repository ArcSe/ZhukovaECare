<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../general/template.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Registration </title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col align-self-center">
                <h2>Registration</h2>
                <div class="mt-2">
                    <form:form action="/auth/registration" method="post" modelAttribute="user">
                            <div class="form-group row">
                                <label for="email" class="col-sm-2 col-form-label">Email: </label>
                                <div class="col-lg-4">
                                    <c:choose>
                                        <c:when test="${emailError==null}">
                                            <form:input  class="form-control" type="email" path="email" placeholder="Email"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input  class="form-control is-invalid " type="email" path="email" placeholder="Email"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${emailError!=null}">
                                        <div class="invalid-feedback">
                                                ${emailError}
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-sm-2 col-form-label">Password: </label>
                                <div class="col-lg-4">
                                    <c:choose>
                                        <c:when test="${passwordError==null}">
                                            <form:input  class="form-control" type="password" path="password" placeholder="Password"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input  class="form-control is-invalid " type="password" path="password" placeholder="Password"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${passwordError!=null}">
                                        <div class="invalid-feedback">
                                                ${passwordError}
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-sm-2 col-form-label">Confirm Password: </label>
                                <div class="col-lg-4">
                                    <c:choose>
                                        <c:when test="${passwordConfirmError==null}">
                                            <form:input  class="form-control" type="password" path="passwordConfirm" placeholder="Confirm Password"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input  class="form-control is-invalid " type="password" path="passwordConfirm" placeholder="Confirm Password"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${passwordConfirmError!=null}">
                                        <div class="invalid-feedback">
                                                ${passwordConfirmError}
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
                            <div class="row ">
                                <button type="submit" class="btn btn-primary"> Sign In</button>
                                <a class="btn btn-light" role="button" href="/login"> Login</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>