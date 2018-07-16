package Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import Interface.IMessageInputType;
import Interface.ISampleMessageInputRepository;
import Model.MessageType1;
import Model.MessageType2;
import Model.MessageType3;

/**
 * This class is used for creating sample messages that will be sent by external company
 *
 */
public class SampleMessageInputRepository implements ISampleMessageInputRepository {
	private ArrayList<String> operation;
	private ArrayList<String> productType;
	private ArrayList<IMessageInputType> messageList;
	
	public SampleMessageInputRepository() {
		operation = new ArrayList<String>();
		productType = new ArrayList<String>();
		messageList = new ArrayList<IMessageInputType>();
		// initial values
		this.productType.add("apple");
		this.productType.add("banana");
		this.productType.add("orange");
		this.productType.add("pineapple");
		this.productType.add("mango");
		
		this.operation.add("add");
		this.operation.add("subtract");
		this.operation.add("multiply");
	}
	
	@Override
	public void generateMessageList() {
		this.messageList = this.generateMessages();
	}

	@Override
	public ArrayList<IMessageInputType> getMessageInput() {
		return this.messageList;
	}

	// Generate randomly 50 messages
	private ArrayList<IMessageInputType> generateMessages() {
		ArrayList<IMessageInputType> messages = new ArrayList<IMessageInputType>();
		
		// 50 messages
		for(int i = 0; i < 50; i++) {
			// random message type 1 to 3
			int randMessageType = getRandomIntegerBetweenRange(1,3);
			
			// Noted: product's price can be between 0p and 10p;
			if(randMessageType == 1) {
				try {
					messages.add(new MessageType1(this.productType.get(getRandomIntegerBetweenRange(0, 4)), getRandomDoubleBetweenRange(0,10)));
				}
				catch (Exception e) {
					System.out.println("Cannot generate message type1: " + e.getMessage());
				}
			}
			else if(randMessageType == 2) {
				try {
					messages.add(new MessageType2(this.productType.get(getRandomIntegerBetweenRange(0, 4)), getRandomDoubleBetweenRange(0,10), getRandomIntegerBetweenRange(2, 100)));
				}
				catch (Exception e) {
					System.out.println("Cannot generate message type2: " + e.getMessage());
				}
			}
			else if(randMessageType == 3) {
				try {
					messages.add(new MessageType3(this.productType.get(getRandomIntegerBetweenRange(0, 4)), getRandomDoubleBetweenRange(0,10), this.operation.get(getRandomIntegerBetweenRange(0,2))));
				}
				catch (Exception e) {
					System.out.println("Cannot generate message type3: " + e.getMessage());
				}
			}
		}
		
		return messages;
	}
	
	private int getRandomIntegerBetweenRange(int min, int max) {
		return (int)(Math.random()*((max-min)+1))+min;
	}
	
	private double getRandomDoubleBetweenRange(double min, double max) {
		BigDecimal bd = new BigDecimal((Math.random()*((max-min)+1))+min);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}
}
