package Model;

import Interface.IMessageInputType;

public class MessageType3 implements IMessageInputType {
	private String message;
	
	public MessageType3(String name, double price, String operation) {
		this.message = operation + " " + price + "p " + name;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
