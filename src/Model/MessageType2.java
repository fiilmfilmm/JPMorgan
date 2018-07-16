package Model;

import Interface.IMessageInputType;

public class MessageType2 implements IMessageInputType {
	private String message;
	
	public MessageType2(String name, double price, int quantity) {
		// Sample message = "apple at 10p"
		this.message = quantity + " sales of " + name + " at " + price + "p each";
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
