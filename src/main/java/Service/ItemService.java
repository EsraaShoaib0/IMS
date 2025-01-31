package Service;

import java.util.List;

import Model.Item;

public interface ItemService {
	public List<Item> getAllItem();
	void saveItem(Item item);
	Item findItemByID(int id);
	void updateItem(Item item);
	void deleteItem (int id);

}
