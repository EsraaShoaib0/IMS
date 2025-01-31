<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interactive Item List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Static/CSS/style.css"> 
</head>
<body>

    <h1>Interactive Item List</h1>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${itemsData}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.total_number}</td>
                    <td class="action-buttons">
                        <a href="ItemController?action=LOAD_ITEM&id=${item.id}">
                            <button class="update-btn">Update</button>
                        </a>
                        <a href="ItemController?action=DELETE&id=${item.id}" onclick="return confirm('Are you sure you want to delete this item?');">
                            <button class="delete-btn">Delete</button>
                        </a>

                        <!-- Add or View Details Button -->
                        <c:choose>
                            <c:when test="${item.itemDetails ne null}">
                                <a href="ItemDetailsController?action=VIEW_DETAILS&id=${item.id}">
                                    <button class="view-details-btn">View Details</button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="ItemDetailsController?action=ADD_DETAILS&id=${item.id}">
                                    <button class="add-details-btn">Add Details</button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="jsp/addItem.jsp">
        <button class="add-item">Add Item</button>
    </a>

    <script>
        function addItem() {
            alert('Add item functionality not implemented yet!');
        }
    </script>

</body>
</html>
