package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Interface.IMessageService;
import Interface.IReportService;
import Model.Product;

public class SalesReportService implements IReportService {
	public IMessageService messageService;

	public SalesReportService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	@Override
	public void generate() {
		displayReport(getMessages());
	}
	
	private HashMap<String, ArrayList<String>> getMessages() {
		return this.messageService.getAllSales();
	}
	
	private void displayReport(HashMap<String, ArrayList<String>> messages) {
		System.out.println("=================================================== Sales Reports (After every 10th message received) ======================================");
		for(Map.Entry<String, ArrayList<String>> entry: messages.entrySet()) {
			Product product = calculateSales(entry);
			if(product != null) {
				System.out.println("Product name: " + product.name + " | Total quantity: " + product.quantity + " | Total sales: " + String.format("%1$,.2f", product.sales) + "p");
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	private Product calculateSales(Map.Entry<String, ArrayList<String>> entry) {
		try {
			Product product = new Product();
			product.name = entry.getKey();
			
			ArrayList<String> sales = entry.getValue();
			
			// Iterate through all sales
			for (String sale : sales) {
				String[] splitedSales = sale.split("\\s+"); // split string by space

				if(splitedSales[0].equals("1")) {
					String price = splitedSales[3];
					product.quantity += 1;
					product.sales += Double.parseDouble(price.substring(0, price.length()-1));
				}
				else if (splitedSales[0].equals("2")) {
					String price = splitedSales[6];
					product.quantity += Integer.parseInt(splitedSales[1]);
					product.sales += Integer.parseInt(splitedSales[1]) * Double.parseDouble(price.substring(0, price.length()-1));
				}
			}
			return product;
		}
		catch (Exception e) {
			System.out.println("Cannot calculate sales: " + e.getMessage());
		}
		return null;
	}
}
