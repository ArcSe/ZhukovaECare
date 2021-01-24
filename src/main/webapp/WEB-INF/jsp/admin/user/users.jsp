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
    <h2 align="centre">User Manager</h2>
    <br/>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Email</th>
                <th scope="col">Client ID</th>
                <th scope="col">Roles</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td scope="row">${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.clientId}</td>
                    <td>${user.roles}</td>
                    <td>
                        <div class="row-group">
                            <a class="btn btn-light" href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}" role="button">Edit</a>
                            <a class="btn btn-light" href="${pageContext.request.contextPath}/admin/users/addClientId?id=${user.id}" role="button">Add Client Id</a>
                            <form action="${pageContext.request.contextPath}/admin/users/delete" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>