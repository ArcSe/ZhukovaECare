<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light">
        <a class="navbar-brand" href="/">Ecare</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <security:authorize access="hasRole('MANAGER')">
                    <li class="nav-item">
                        <a class="nav-link" href="/managers/options">Options</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/managers/tariffs">Tariffs</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/managers/client"> Clients </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/managers/contracts"> Contracts </a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                    <li class="nav-item ">
                        <a class="nav-link" href="/admin/users"> Users </a>
                    </li>
                </security:authorize>

            </ul>
            <security:authorize access="!isAuthenticated()">
                <a class="nav-link" href="/login"> Login </a>
            </security:authorize>
            <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                <a class="nav-link" href="/logout"> Logout </a>
            </security:authorize>
            <security:authorize access="hasRole('USER')">
                <div class="dropdown">
                    <button class="btn dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-expanded="false">
                        <c:if test="${shoppingCart.contracts.size()>0}">
                            <span class="badge rounded-pill bg-danger align-self-center"> </span>
                        </c:if>
                        Dropdown button
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/client/userProfile"> Client Profile </a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/cart">
                            <c:if test="${shoppingCart.contracts.size()>0}">
                                <span class="badge rounded-pill bg-danger align-self-center"> </span>
                            </c:if>
                            Cart</a></li>

                        <li><a class="nav-link" href="${pageContext.request.contextPath}/logout"> Logout </a></li>
                    </ul>
                </div>
            </security:authorize>
        </div>
</nav>
</head>