package ServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Model.Item;
import Service.ItemService;

public class ItemServiceImpl implements ItemService  {
	private DataSource dataSource;
	

	public ItemServiceImpl(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}

	@Override
	public List<Item> getAllItem() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM item";
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			List<Item> items = new ArrayList<Item>();
			while(resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getInt("id"));
				item.setName(resultSet.getString("name"));
				item.setPrice(resultSet.getInt("price"));
				item.setTotal_number(resultSet.getInt("total_number"));
				items.add(item);
			}
			System.out.println(items);
			return items;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void saveItem(Item item) {
	    System.out.println("saveItem called with: " + item);
	    String query = "INSERT INTO item (name, price, total_number) VALUES (?, ?, ?)";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, item.getName());
	        preparedStatement.setDouble(2, item.getPrice());
	        preparedStatement.setInt(3, item.getTotal_number());

	        preparedStatement.executeUpdate();
	        System.out.println("Item saved successfully");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public Item findItemByID(int id) {
		// TODO Auto-generated method stub
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
		        e.printStackTrace();
		    }
		return null;
	}

	@Override
	public void updateIte(Item item) {
		// TODO Auto-generated method stub
		String query = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        preparedStatement.setString(1, item.getName());
	        preparedStatement.setDouble(2, item.getPrice());
	        preparedStatement.setInt(3, item.getTotal_number());
	        preparedStatement.setInt(4, item.getId());
	        
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		 String query = "DELETE FROM item WHERE id = ?";
		    try (Connection connection = dataSource.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}
	

}
