package Service;

import java.util.List;

import Model.ItemDetails;

public interface ItemDetailsService {
	public List<ItemDetails> getAllItem();
	void saveItem(ItemDetails item);
	ItemDetails findItemByID(int id);
	void updateItem(ItemDetails item);
	void deleteItem (int id);

}
