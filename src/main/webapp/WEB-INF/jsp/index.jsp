<%--
  Created by IntelliJ IDEA.
  User: Icebear
  Date: 01.10.2017
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/main.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="mainArea">
        <div class="leftArea">
            <input type="button" id="createCategory" value="Create category"/>
            <ul id="categoriesList"></ul>
        </div>
        <div class="rightArea">
            <textarea id="description" name="description" class="textAreaDescription"></textarea>
            <br>
            Price: <input type="number" id="price" name="price" step="0.01" disabled>
            Add to order: <input type="number" id="quantity" name="quantity" disabled>
            <input type="button" id="addToOrder" value="Add to order" disabled/>
        </div>
    </div>
</div>

<div class="modal fade" id="createOrder">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleOrder"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="orderForm">
                    <table id="orderTable">
                        <thead>
                        <tr>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sum</th>
                        </tr>
                        </thead>

                    </table>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="userName" name="userName"
                                   placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="userEmail" name="userEmail"
                                   placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="createOrder()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editCategory">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleCategory"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="categoryForm">
                    <table id="orderTable">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="categoryName" name="categoryName"
                                   placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="categoryDescription" name="categoryDescription"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="createCategory()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
