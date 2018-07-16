import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Interface.IMessageInputType;
import Interface.IMessageService;
import Model.MessageType1;
import Model.MessageType2;
import Model.MessageType3;
import Service.MessageService;

public class MessageServiceTest {
	public IMessageService messageService;
	
	@Before
	public void setUp() {
		messageService = new MessageService();
	}
	
	/**
	 * This application will keep all sales and all ajustment seperately.
	 */
	@Test
	public void keepAllSalesAndAdjustments() {
		// Arrange
		IMessageInputType message1 = new MessageType1("apple", 10.0);
		IMessageInputType message2 = new MessageType2("banana", 20.0, 2);
		IMessageInputType message3 = new MessageType3("apple", 5.0, "add");
		
		// Action
		messageService.notifyMessage(message1);
		messageService.notifyMessage(message2);
		messageService.notifyMessage(message3);
		
		HashMap<String, ArrayList<String>> allSales = messageService.getAllSales();
		HashMap<String, ArrayList<String>> allAjdustment = messageService.getAdjustments();
		
		// Assert
		assertEquals(2, allSales.size());
		assertEquals(1, allAjdustment.size());
	}
	
	@Test
	public void adjustSales() {
		IMessageInputType message1 = new MessageType1("apple", 10.0);
		IMessageInputType message3 = new MessageType3("apple", 5.0, "add");
		
		// Action
		messageService.notifyMessage(message1);
		
		// Assert
		HashMap<String, ArrayList<String>> allSales = messageService.getAllSales();
		ArrayList<String> appleMessages = allSales.get("apple");
		assertEquals("1 apple at 10.0p", appleMessages.get(0));
	
		// Action
		messageService.notifyMessage(message3);
		
		// Assert
		allSales = messageService.getAllSales();
		appleMessages = allSales.get("apple");
		assertEquals("1 apple at 15.0p", appleMessages.get(0));
	}
}
