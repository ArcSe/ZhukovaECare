<%@ page import="com.javaschool.model.Role" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Manager</title>
    <c:import url="../../general/template.jsp"/>
</head>
<body>
    <div align="center">
        <h2>User Manager</h2>
    </div>
    <br/>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Email</th>
                <th scope="col">Client ID</th>
                <security:authorize access="hasRole('ADMIN')">
                    <th scope="col">Roles</th>
                </security:authorize>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td scope="row">${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.clientId}</td>
                    <security:authorize access="hasRole('ADMIN')">
                        <td>${user.roles}</td>
                    </security:authorize>
                    <td>
                        <div class="row">
                            <%
                                Role role = Role.ROLE_USER;
                                request.setAttribute("roleUser", role);
                            %>
                            <c:choose>
                                <c:when test="${user.roles.contains(roleUser)}">
                                    <a class="btn btn-light" href="${pageContext.request.contextPath}/admin/users/addClientId?id=${user.id}" role="button">
                                        Add Client Id
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a  href="${pageContext.request.contextPath}/admin/users/addClientId?id=${user.id}" role="button"
                                       class="btn btn-light disabled" tabindex="-1"  aria-disabled="true">
                                        Add Client Id
                                    </a>
                                </c:otherwise>
                            </c:choose>

                            <security:authorize access="hasRole('ADMIN')">
                                <form action="${pageContext.request.contextPath}/admin/users/delete" method="post">
                                    <a class="btn btn-light" href="${pageContext.request.contextPath}/admin/users/edit?id=${user.id}" role="button">
                                        Edit
                                    </a>
                                    <input type="hidden" name="userId" value="${user.id}"/>
                                    <input type="hidden" name="action" value="delete"/>
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </security:authorize>
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