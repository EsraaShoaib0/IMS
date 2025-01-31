package Controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Model.Item;
import Service.ItemService;
import ServiceImpl.ItemServiceImpl;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Injecting DataSource via the container
    @Resource(name = "jdbc/web_item")
    private DataSource ds;

    private ItemService itemService;

    @Override
    public void init() throws ServletException {
        if (ds == null) {
            throw new ServletException("DataSource injection failed. DataSource is null.");
        }
        // Initialize ItemService here, not in the constructor
        itemService = new ItemServiceImpl(ds);
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "LOAD_ITEMS";  // Default action is to load items

        switch (action) {
            case "LOAD_ITEMS":
                loadItems(request, response);
                break;
            case "ADD":
                addItem(request, response);
                break;
            case "DELETE":
                deleteItem(request, response);
                break;
            case "UPDATE":
                updateItem(request, response);
                break;
            case "LOAD_ITEM":
                loadItem(request, response);
                break;
            default:
                loadItems(request, response);
                break;
        }
    }

    // Load all items and forward to JSP page
    private void loadItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemService.getAllItem();
        request.setAttribute("itemsData", items);  // Set items as attribute
        request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);  // Forward to JSP page
    }

    // Update an item
    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String totalNumberStr = request.getParameter("total_number");

        if (idStr == null || name == null || priceStr == null || totalNumberStr == null) {
            request.setAttribute("error", "All fields must be filled out.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            int totalNumber = Integer.parseInt(totalNumberStr);

            Item updatedItem = new Item();
            updatedItem.setId(id);
            updatedItem.setName(name);
            updatedItem.setPrice(price);
            updatedItem.setTotal_number(totalNumber);

            itemService.updateItem(updatedItem);

            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price and Total Number must be valid numbers.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
        }
    }

    // Delete an item
    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("error", "Item ID is required to delete an item.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            itemService.deleteItem(id);
            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Item ID.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
        }
    }


    // Add a new item
 // Add a new item
    private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String totalNumberStr = request.getParameter("total_number");

        System.out.println("Add Item: Name=" + name + ", Price=" + priceStr + ", Total Number=" + totalNumberStr);

        if (name == null || priceStr == null || totalNumberStr == null) {
            request.setAttribute("error", "All fields must be filled out.");
            request.getRequestDispatcher("jsp/addItem.jsp").forward(request, response);
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int totalNumber = Integer.parseInt(totalNumberStr);

            Item newItem = new Item();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setTotal_number(totalNumber);

            System.out.println("New Item Created: " + newItem);

            itemService.saveItem(newItem);

            response.sendRedirect("ItemController?action=LOAD_ITEMS");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price and Total Number must be valid numbers.");
            request.getRequestDispatcher("jsp/addItem.jsp").forward(request, response);
        }
    }


    // Load a single item for editing
    private void loadItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("error", "Item ID is required to load an item.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Item item = itemService.findItemByID(id);

            if (item != null) {
                request.setAttribute("item", item);  // Set the item data to be displayed in the form
                request.getRequestDispatcher("jsp/updateItem.jsp").forward(request, response);  // Forward to update item page
            } else {
                request.setAttribute("error", "Item not found.");
                request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Item ID.");
            request.getRequestDispatcher("jsp/itemsList.jsp").forward(request, response);
        }
    }


    // Handle POST requests (forward to doGet)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
