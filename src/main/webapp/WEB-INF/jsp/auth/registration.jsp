<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../general/template.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<form:form action="/auth/registration" method="post" modelAttribute="user">
    <div><label> Email : <form:input type="email" path="email"/> </label>
        <form:errors path="email"/>
        ${emailError}
    </div>
    <div><label> Password: <form:input type="password" path="password"/> </label></div>
    <div>
        <form:input type="password" path="passwordConfirm"
                    placeholder="Confirm your password"/>
        <form:errors path="password"/>
            ${passwordError}
    </div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
    <div><input type="submit" value="Sign In"/></div>
</form:form>
</body>
</html>