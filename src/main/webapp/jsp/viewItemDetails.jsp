<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Item Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style.css">
    <style>
        .card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            width: 80%;
            margin: 20px auto;
            background-color: #f9f9f9;
        }
        .card h2 {
            margin-top: 0;
        }
        .card table {
            width: 100%;
            border-collapse: collapse;
        }
        .card table, .card th, .card td {
            border: 1px solid #ddd;
        }
        .card th, .card td {
            padding: 10px;
            text-align: left;
        }
        .card .action-buttons {
            margin-top: 20px;
        }
        .card .action-buttons a {
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>View Item and Item Details</h1>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <div class="card">
        <h2>Item Information</h2>
        <table>
            <tr>
                <th>Name</th>
                <td>${item.name}</td>
            </tr>
            <tr>
                <th>Price</th>
                <td>${item.price}</td>
            </tr>
            <tr>
                <th>Total Number</th>
                <td>${item.total_number}</td>
            </tr>
        </table>
    </div>

    <div class="card">
        <h2>Item Details</h2>
        <table>
            <tr>
                <th>Model</th>
                <td>${itemDetails.model}</td>
            </tr>
            <tr>
                <th>Type Model</th>
                <td>${itemDetails.type_model}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${itemDetails.description}</td>
            </tr>
            <tr>
                <th>Item ID</th>
                <td>${itemDetails.item_id}</td>
            </tr>
        </table>
    </div>

    <div class="action-buttons">
        <a href="ItemDetailsController?action=LOAD_ITEM_DETAIL&id=${itemDetails.item_details_id}">
            <button class="update-btn">Update Item Details</button>
        </a>
        <a href="ItemDetailsController?action=DELETE&id=${itemDetails.item_details_id}" onclick="return confirm('Are you sure you want to delete this item detail?');">
            <button class="delete-btn">Delete Item Detail</button>
        </a>
    </div>

    <a href="ItemController?action=LOAD_ITEMS">
        <button class="back-btn">Back to Items List</button>
    </a>

</body>
</html>
