import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Interface.IMessageInputType;
import Interface.IMessageService;
import Interface.IReportService;
import Model.MessageType1;
import Model.MessageType2;
import Service.MessageService;
import Service.SalesReportService;

public class ReportTest {
	private IReportService salesReportService;
	private final ByteArrayOutputStream outReport = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	// Mock variables
	private IMessageService messageService;

	@Before
	public void setUp() {
		// set up stream
	    System.setOut(new PrintStream(outReport));
	    
	    messageService = new MessageService();
	}

	@After
	public void restore() {
		// restore stream
	    System.setOut(originalOut);
	}
	
	@Test
	public void displaySalesReport() {
	    // Arrange
	    IMessageInputType message1 = new MessageType1("apple", 40.0);
	    IMessageInputType message2 = new MessageType2("apple", 12.0, 10);
	    IMessageInputType message3 = new MessageType1("banana", 5.0);
	    IMessageInputType message4 = new MessageType2("pineapple", 2.0, 3);
	    
	    messageService.notifyMessage(message1);
	    messageService.notifyMessage(message2);
	    messageService.notifyMessage(message3);
	    messageService.notifyMessage(message4);
	    
	    // Action
	    salesReportService = new SalesReportService(messageService);
	    salesReportService.generate();
	    
	    // Assert
	    assertTrue(outReport.toString().contains("Product name: apple | Total quantity: 11 | Total sales: 160.00p"));
	    assertTrue(outReport.toString().contains("Product name: banana | Total quantity: 1 | Total sales: 5.00p"));
	    assertTrue(outReport.toString().contains("Product name: pineapple | Total quantity: 3 | Total sales: 6.00p"));
	}
}
