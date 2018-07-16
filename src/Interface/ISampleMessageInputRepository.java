package Interface;

import java.util.ArrayList;

public interface ISampleMessageInputRepository {
	public void generateMessageList();
	public ArrayList<IMessageInputType> getMessageInput();
}
