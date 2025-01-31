package ServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.Item;
import Model.ItemDetails;
import Service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService {
    private DataSource dataSource;

    public ItemDetailsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ItemDetails> getAllItem() {
        List<ItemDetails> items = new ArrayList<>();
        String sql = "SELECT * FROM item_details";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ItemDetails item = new ItemDetails();
                item.setItem_details_id(resultSet.getInt("item_details_id"));
                item.setModel(resultSet.getString("model"));
                item.setType_model(resultSet.getString("type_model"));
                item.setDescription(resultSet.getString("description"));
                
                Item relatedItem = new Item();
                relatedItem.setId(resultSet.getInt("item_id"));
                item.setItem(relatedItem);
                
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void saveItem(ItemDetails item) {
        String sql = "INSERT INTO item_details (model, type_model, description, item_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, item.getModel());
            statement.setString(2, item.getType_model());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getItem().getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemDetails findItemByID(int id) {
        ItemDetails item = null;
        String sql = "SELECT * FROM item_details WHERE item_details_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new ItemDetails();
                    item.setItem_details_id(resultSet.getInt("item_details_id"));
                    item.setModel(resultSet.getString("model"));
                    item.setType_model(resultSet.getString("type_model"));
                    item.setDescription(resultSet.getString("description"));
                    
                    Item relatedItem = new Item();
                    relatedItem.setId(resultSet.getInt("item_id"));
                    item.setItem(relatedItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void updateItem(ItemDetails item) {
        String sql = "UPDATE item_details SET model = ?, type_model = ?, description = ? WHERE item_details_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, item.getModel());
            statement.setString(2, item.getType_model());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getItem_details_id());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int id) {
        String sql = "DELETE FROM item_details WHERE item_details_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
