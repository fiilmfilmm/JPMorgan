package Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Interface.IMessageInputType;
import Interface.IMessageRepository;
import Interface.IMessageService;
import Model.MessageType1;
import Model.MessageType2;
import Model.MessageType3;
import Repository.MessageRepository;

/**
 * This class classify a message type
 */
public class MessageService implements IMessageService {
	public IMessageRepository messageRepository;

	public MessageService() {
		this.messageRepository = new MessageRepository();
	}

	@Override
	public void notifyMessage(IMessageInputType messageInputType) {
		String[] message = extractMessage(messageInputType);
		
		if(message != null) {
			this.messageRepository.updateSales(message[0], message[1]);
		}
	}

	@Override
	public HashMap<String, ArrayList<String>> getAllSales() {
		return this.messageRepository.getSales();
	}
	
	@Override
	public HashMap<String, ArrayList<String>> getAdjustments() {
		return this.messageRepository.getAgjustments();
	}
	
	private String[] extractMessage(IMessageInputType messageInputType) {
		String messageInput = messageInputType.getMessage();
		
		// String[0] represents product name
		// String[1] represents messages
		String[] splitedMessageInput = messageInput.split("\\s+");
		
		if(messageInputType instanceof MessageType1) {
			return new String[] {splitedMessageInput[0],"1 " + messageInput}; // put message type at the beginning of each message
		}
		else if(messageInputType instanceof MessageType2) {
			return new String[] {splitedMessageInput[3], "2 " + messageInput}; // put message type at the beginning of each message
		}
		else if(messageInputType instanceof MessageType3) {
			adjustSales(new String[] {splitedMessageInput[2], messageInput});
			return null; // Do not want to add adjustment to all sales.
		}
		
		return null;
	}
	
	private void adjustSales(String[] messageInput) {
		if(messageInput != null && messageInput[1] != null) {
			String[] splitedMessageInput = messageInput[1].split("\\s+");
			String operation = splitedMessageInput[0];
			String adjustedPrice = splitedMessageInput[1];
			String key = splitedMessageInput[2];
			
			ArrayList<String> newSales = new ArrayList<String>();
			HashMap<String, ArrayList<String>> allSales = getAllSales();
			
			for(Map.Entry<String, ArrayList<String>> entry: allSales.entrySet()) {
				
				if(entry.getKey().equals(key)) {
					
					ArrayList<String> sales = entry.getValue();
					
					// Iterate through all sales
					for (String sale : sales) {
						String[] splitedSales = sale.split("\\s+"); // split string by space
	
						if(splitedSales[0].equals("1")) {
							String newPrice = salesOperation(operation, "1", splitedSales[3], adjustedPrice);
							if(newPrice != null)
								splitedSales[3] = newPrice;
						}
						else if (splitedSales[0].equals("2")) {
							String newPrice = salesOperation(operation, "2", splitedSales[6], adjustedPrice);
							if(newPrice != null)
								splitedSales[6] = newPrice;
						}
						
						String newRecord = "";
						for(int i = 0; i < splitedSales.length; i++) {
							if(i == splitedSales.length - 1)
								newRecord += splitedSales[i];
							else
								newRecord += splitedSales[i] + " ";
						}
						newSales.add(newRecord);
					}
					if(newSales.size() > 0) {
						entry.setValue(newSales);
					}
				}
				
			}
		}
		
		// Keep all adjustments
		this.messageRepository.adjustSales(messageInput[0], messageInput[1]);
	}
	
	private double roundUp(double longDigit) {
		BigDecimal bd = new BigDecimal(longDigit);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}

	private String salesOperation(String operation, String messageType, String oldPrice, String adjustedPrice) {
		double nPrice = 0;
		double oPrice = Double.parseDouble(oldPrice.substring(0, oldPrice.length()-1));
		double aPrice = Double.parseDouble(adjustedPrice.substring(0, adjustedPrice.length()-1));
		
		if(operation.equalsIgnoreCase("add")) {
			nPrice = oPrice + aPrice;
		}
		else if (operation.equalsIgnoreCase("subtract")) {
			nPrice = oPrice - aPrice;
		}
		else if (operation.equalsIgnoreCase("multiply")) {
			nPrice = oPrice * aPrice;
		}
		nPrice = roundUp(nPrice);
		return nPrice + "p";
	}
	
}
