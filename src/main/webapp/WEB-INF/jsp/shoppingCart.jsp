<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="general/template.jsp"%>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<style>
    .brd {
        border: 2px solid whitesmoke;
        background: ghostwhite;
        padding: 30px;
    }
</style>
<div class="container">
    <div class="row">
        <h1 class="display-4">Shopping Cart ${shoppingCartForManager.customerEmail}</h1>
    </div>
    <br/>
    <div>
        <c:forEach items="${shoppingCartForManager.contracts}" var="contract">
            <br/>
            <div class="brd">
                <div class="row justify-content-between">
                    <div class="col-lg-6">
                        <h2>Contract: ${contract.contract.number}</h2>
                        <h3 class="mt-1">Tariff: ${contract.contract.tariff.name}</h3>
                    </div>
                    <div class="col-lg-2">
                        <h3>Price: ${contract.price}</h3>
                        <h3 class="mt-3">Service Cost: ${contract.serviceCost}</h3>
                        <form class="mt-1" action="${pageContext.request.contextPath}/managers/contracts/removeTariff" method="post">
                            <input type="hidden" name="contractId" value="${contract.contract.id}"/>
                            <input type="hidden" name="tariffId" value="${contract.contract.tariff.id}"/>
                            <button type="submit" class="btn btn-warning">Delete</button>
                        </form>
                    </div>
                </div>
                <c:if test="${contract.optionsShoppingCart.size()>0}">
                    <h3 class="text-center mt-1">Options:</h3>
                    <table class="table mt-2">
                        <thead>
                        <tr>
                            <th scope="col" class="text-center">Name</th>
                            <th scope="col" class="text-center">Price</th>
                            <th scope="col" class="text-center">Service Cost</th>
                            <th scope="col" class="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${contract.optionsShoppingCart}" var="option">
                            <tr>
                                <td class="text-center">${option.name}</td>
                                <td class="text-center">${option.price}</td>
                                <td class="text-center">${option.serviceCost}</td>
                                <td class="text-center">
                                    <form action="${pageContext.request.contextPath}/managers/contracts/removeOption" method="post">
                                        <input type="hidden" name="contractId" value="${contract.contract.id}"/>
                                        <input type="hidden" name="optionId" value="${option.id}"/>
                                        <button type="submit" class="btn btn-warning">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <br/>
    <div class="row justify-content-between">
        <div class="col-4">
            <h2>Price: ${shoppingCartForManager.price}</h2>
            <h2 class="mt-1">ServiceCost: ${shoppingCartForManager.serviceCost}</h2>
        </div>
        <c:if test="${shoppingCartForManager.contracts.size()>0}">
            <div class="col-2 align-self-center">
                <form action="${pageContext.request.contextPath}/managers/contracts/shoppingList" method="post">
                    <button type="submit" class="btn btn-dark btn-lg">Buy</button>
                </form>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
