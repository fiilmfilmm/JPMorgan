package Model;

import Interface.IMessageInputType;

public class MessageType1 implements IMessageInputType {
	private String message;
	
	public MessageType1(String name, double price) {
		// Sample message = "apple at 10p"
		this.message = name + " at " + price + "p";
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
