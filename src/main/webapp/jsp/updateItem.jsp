<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style2.css">
</head>
<body>

    <!-- Container for the Update Item Form -->
    <div class="container">
        <div class="card">
            <h2>Update Item</h2>

            <!-- Error Handling: Display Error Message if any -->
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <!-- Form for Updating an Item -->
            <form action="${pageContext.request.contextPath}/ItemController" method="POST">
                <input type="hidden" name="action" value="UPDATE">
                <input type="hidden" name="id" value="${item.id}">  <!-- Pass Item ID for update -->

                <!-- Item Name Field -->
                <div class="form-group">
                    <label for="name">Item Name</label>
                    <input type="text" id="name" name="name" class="form-control" value="${item.name}" required>
                </div>

                <!-- Price Field -->
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" id="price" name="price" class="form-control" value="${item.price}" required>
                </div>

                <!-- Total Number Field -->
                <div class="form-group">
                    <label for="total_number">Total Number</label>
                    <input type="number" id="total_number" name="total_number" class="form-control" value="${item.total_number}" required>
                </div>

                <!-- Submit Button -->
                <button type="submit" class="add-item">Update Item</button>
            </form>

            <!-- Back Button to Item List -->
            <a href="${pageContext.request.contextPath}/ItemController?action=LOAD_ITEMS">
                <button class="back">Back to Item List</button>
            </a>
        </div>
    </div>

</body>
</html>
