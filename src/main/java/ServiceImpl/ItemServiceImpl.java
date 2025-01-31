package ServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import Model.Item;
import Service.ItemService;

public class ItemServiceImpl implements ItemService {
    private DataSource dataSource;

    public ItemServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Item> getAllItem() {
        String query = "SELECT * FROM item";
        List<Item> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setTotal_number(resultSet.getInt("total_number"));
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using logging here
        }
        return null;
    }

    @Override
    public void saveItem(Item item) {
        String query = "INSERT INTO item (name, price, total_number) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getTotal_number());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using logging here
        }
    }

    @Override
    public Item findItemByID(int id) {
        String query = "SELECT * FROM item WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setTotal_number(resultSet.getInt("total_number"));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using logging here
        }
        return null;
    }

    @Override
    public void updateItem(Item item) {
        String query = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getTotal_number());
            preparedStatement.setInt(4, item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using logging here
        }
    }

    @Override
    public void deleteItem(int id) {
        String query = "DELETE FROM item WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using logging here
        }
    }
}
