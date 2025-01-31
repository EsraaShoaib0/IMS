package Controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Model.ItemDetails;
import Service.ItemDetailsService;
import ServiceImpl.ItemDetailsServiceImpl;

@WebServlet("/ItemDetailsController")
public class ItemDetailsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/web_item")
    private DataSource ds;

    private ItemDetailsService itemDetailsService;

    @Override
    public void init() throws ServletException {
        if (ds == null) {
            throw new ServletException("DataSource injection failed. DataSource is null.");
        }
        itemDetailsService = new ItemDetailsServiceImpl(ds);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "VIEW_DETAILS"; 

        switch (action) {
            case "ADD_DETAILS":
                addItemDetails(request, response);
                break;
            case "DELETE_DETAILS":
                deleteItemDetails(request, response);
                break;
            case "UPDATE_DETAILS":
                updateItemDetails(request, response);
                break;
            case "VIEW_DETAILS":
                viewItemDetails(request, response);
                break;
            default:
                response.sendRedirect("ItemController?action=LOAD_ITEMS");
                break;
        }
    }

    private void addItemDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        String typeModel = request.getParameter("type_model");
        String description = request.getParameter("description");
        String itemIdStr = request.getParameter("id");

        if (model == null || typeModel == null || description == null || itemIdStr == null) {
            request.setAttribute("error", "All fields must be filled out.");
            request.getRequestDispatcher("jsp/AddItemDetails.jsp").forward(request, response);
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdStr);

            ItemDetails newItemDetails = new ItemDetails();
            newItemDetails.setModel(model);
            newItemDetails.setType_model(typeModel);
            newItemDetails.setDescription(description);
            newItemDetails.getItem().setId(itemId);

            itemDetailsService.saveItem(newItemDetails);

            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Item ID must be a valid number.");
            request.getRequestDispatcher("jsp/AddItemDetails.jsp").forward(request, response);
        }
    }

    private void updateItemDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String model = request.getParameter("model");
        String typeModel = request.getParameter("type_model");
        String description = request.getParameter("description");

        if (idStr == null || model == null || typeModel == null || description == null) {
            request.setAttribute("error", "All fields must be filled out.");
            request.getRequestDispatcher("jsp/updateDetails.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            ItemDetails updatedItemDetails = new ItemDetails();
            updatedItemDetails.setItem_details_id(id);
            updatedItemDetails.setModel(model);
            updatedItemDetails.setType_model(typeModel);
            updatedItemDetails.setDescription(description);

            itemDetailsService.updateItem(updatedItemDetails);

            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Item Details ID.");
            request.getRequestDispatcher("jsp/updateDetails.jsp").forward(request, response);
        }
    }

    private void deleteItemDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("error", "Item Details ID is required to delete.");
            request.getRequestDispatcher("jsp/ViewDetails.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            itemDetailsService.deleteItem(id);
            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Item Details ID.");
            request.getRequestDispatcher("jsp/ViewDetails.jsp").forward(request, response);
        }
    }

    private void viewItemDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdStr = request.getParameter("id");

        try {
            int itemId = Integer.parseInt(itemIdStr);
            ItemDetails itemDetail = itemDetailsService.findItemByID(itemId);

            if (itemDetail != null && itemDetail.getItem_details_id() > 0) { 
                request.setAttribute("itemDetail", itemDetail);
                request.getRequestDispatcher("jsp/ViewDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("ItemDetailsController?action=ADD_DETAILS&id=" + itemId);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Item ID.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
