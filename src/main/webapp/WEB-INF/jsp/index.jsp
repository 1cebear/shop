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
</body>
</html>
