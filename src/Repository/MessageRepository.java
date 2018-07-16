package Repository;

import java.util.ArrayList;
import java.util.HashMap;

import Interface.IMessageRepository;

public class MessageRepository implements IMessageRepository {
	private HashMap<String, ArrayList<String>> sales;
	private HashMap<String, ArrayList<String>> adjustments;
	
	public MessageRepository() {
		this.sales = new HashMap<String, ArrayList<String>>();
		this.adjustments = new HashMap<String, ArrayList<String>>();
	}

	// All message are recorded in Hashmap
	@Override
	public void updateSales(String key, String message) {
		storeSales(this.sales, key, message);
	}
	
	@Override
	public void adjustSales(String key, String message) {
		storeSales(this.adjustments, key, message);
	}
	
	private void storeSales(HashMap<String, ArrayList<String>> store, String key, String message) {
		// update existing product
		if(store.containsKey(key)) {
			ArrayList<String> itemsList = store.get(key);
			itemsList.add(message);
			
			store.put(key, itemsList);
		}
		else { // add new product
			ArrayList<String> messageList = new ArrayList<String>();
			messageList.add(message);
			store.put(key, messageList);
		}
	}

	// Return all sales records
	@Override
	public HashMap<String, ArrayList<String>> getSales() {
		return this.sales;
	}

	// Return all adjustments
	@Override
	public HashMap<String, ArrayList<String>> getAgjustments() {
		return this.adjustments;
	}
}
