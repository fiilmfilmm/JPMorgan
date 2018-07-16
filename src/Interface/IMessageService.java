package Interface;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMessageService {
	public void notifyMessage(IMessageInputType messageInputType);
	public HashMap<String, ArrayList<String>> getAllSales();
	public HashMap<String, ArrayList<String>> getAdjustments();
}
