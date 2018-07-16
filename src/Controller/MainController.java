package Controller;

import java.util.ArrayList;

import Interface.IMessageInputService;
import Interface.IMessageInputType;
import Interface.IMessageService;
import Interface.IReportService;
import Service.AdjustmentReportService;
import Service.MessageInputService;
import Service.MessageService;
import Service.SalesReportService;

public class MainController {
	public static void main (String [] arguments) {
		// Variable Declaration
		ArrayList<IMessageInputType> messageInputList;
		
		IMessageInputService messageInputService = new MessageInputService();
		IMessageService messageService = new MessageService();
		IReportService salesReport = new SalesReportService(messageService);
		IReportService adjustmentReport = new AdjustmentReportService(messageService);
		
		// Get input messages
		messageInputList = messageInputService.getMessageList();
		
		// Each message will be sent to application
		for(int i = 0; i < messageInputList.size(); i++) {
			messageService.notifyMessage(messageInputList.get(i));
			
			//After every 10th message, log a report
			if((i+1)%10 == 0) {
				salesReport.generate();
			}
			
			// When a number of message reach to 50 items, log a report of adjustment
			if((i+1) == 50) {
				adjustmentReport.generate();
			}
		}
	}
}
