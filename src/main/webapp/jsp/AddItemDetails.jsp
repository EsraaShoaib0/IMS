<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Item Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style.css"> 
</head>
<body>
    <h1>Add Item Details</h1>
    
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

        <form action="${pageContext.request.contextPath}/ItemDetailsController" method="POST">
        <input type="hidden" name="action" value="ADD">
        <input type="hidden" name="item_id" value="${param.id}">
        
        <div class="form-group">
            <label for="model">Model:</label>
            <input type="text" id="model" name="model" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="type_model">Type Model:</label>
            <input type="text" id="type_model" name="type_model" class="form-control" required>
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" class="form-control" required></textarea>
        </div>

        <button type="submit" class="add-item">Add Item Details</button>
        <a href="itemList.jsp"><button type="button" class="add-item">Back to Item List</button></a>
    </form>
</body>
</html>
