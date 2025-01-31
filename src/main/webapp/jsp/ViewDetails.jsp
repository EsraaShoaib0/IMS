<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Item Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style.css">
</head>
<body>
    <h1>Item Details</h1>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <div class="card">
        <h2>Item Information</h2>
        <p><strong>Name:</strong> ${item.name}</p>
        <p><strong>Price:</strong> ${item.price}</p>
        <p><strong>Amount:</strong> ${item.total_number}</p>
    </div>

    <c:if test="${not empty itemDetail}">
        <div class="card">
            <h2>Item Details</h2>
            <p><strong>Model:</strong> ${itemDetail.model}</p>
            <p><strong>Type Model:</strong> ${itemDetail.type_model}</p>
            <p><strong>Description:</strong> ${itemDetail.description}</p>
        </div>
    </c:if>

    <div class="action-buttons">
        <a href="ItemDetailsController?action=DELETE&id=${itemDetail.item_details_id}" onclick="return confirm('Are you sure you want to delete these details?');">
            <button class="delete-btn">Delete Details</button>
        </a>
        <a href="jsp/updateDetails.jsp?id=${itemDetail.item_details_id}">
            <button class="update-btn">Update</button>
        </a>
    </div>

    <a href="ItemController?action=LIST_ITEMS">
        <button class="back-btn">Back to Item List</button>
    </a>
</body>
</html>
