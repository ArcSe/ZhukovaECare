<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Option Manager</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
    <h2 align="centre">Option Manager</h2>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">Roles</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td scope="row">${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.roles}</td>
                    <td>
                        <a class="btn btn-light" href="/admin/users/edit?id=${user.id}" role="button">Edit</a>

                        <a class="btn btn-danger" href="/options/delete?id=${option.id}" method = "DELETE" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>