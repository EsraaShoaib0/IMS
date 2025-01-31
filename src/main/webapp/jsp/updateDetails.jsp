<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Item Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style.css"> 
</head>
<body>
    <h1>Update Item Details</h1>
    
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    
    <div class="card">
        <form action="ItemDetailsController" method="post">
            <input type="hidden" name="action" value="UPDATE">
            <input type="hidden" name="id" value="${itemDetail.item_details_id}">
            <input type="hidden" name="item_id" value="${itemDetail.item_id}">

            <div class="form-group">
                <label for="model">Model:</label>
                <input type="text" id="model" name="model" class="form-control" value="${itemDetail.model}" required>
            </div>

            <div class="form-group">
                <label for="type_model">Type Model:</label>
                <input type="text" id="type_model" name="type_model" class="form-control" value="${itemDetail.type_model}" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" class="form-control" value="${itemDetail.description}" required>
            </div>

            <div class="action-buttons">
                <button type="submit" class="update-btn">Update</button>
                <a href="itemList.jsp" class="back-btn">Back to Item List</a>
            </div>
        </form>
    </div>
</body>
</html>
