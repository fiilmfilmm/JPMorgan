package Interface;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMessageRepository {
	public void updateSales(String key, String message);
	public void adjustSales(String key, String message);
	public HashMap<String, ArrayList<String>> getSales();
	public HashMap<String, ArrayList<String>> getAgjustments();
}
