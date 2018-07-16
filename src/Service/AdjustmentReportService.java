package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Interface.IMessageService;
import Interface.IReportService;

public class AdjustmentReportService implements IReportService {
	public IMessageService messageService;
	
	public AdjustmentReportService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	@Override
	public void generate() {
		displayReport();
		displayAllsales();
	}
	
	private void displayAllsales() {
		HashMap<String, ArrayList<String>> messages = this.messageService.getAllSales();
		System.out.println("=================================================== All Messages Received ===================================================");
		for(Map.Entry<String, ArrayList<String>> entry: messages.entrySet()) {
			System.out.println("All sales of " + entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}
	
	private void displayReport() {
		HashMap<String, ArrayList<String>> messages = this.messageService.getAdjustments();
		System.out.println("=================================================== Adjustment Report ===================================================");
		for(Map.Entry<String, ArrayList<String>> entry: messages.entrySet()) {
			System.out.println("Adjustment of " + entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}

}
