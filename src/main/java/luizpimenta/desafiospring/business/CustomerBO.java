package luizpimenta.desafiospring.business;

import luizpimenta.desafiospring.model.Customer;
import luizpimenta.desafiospring.util.DataWarehouse;

public class CustomerBO implements BusinessInterface {

	public CustomerBO(){}
	
	public void create(String line) {
		Customer customer = new Customer();		
		String lineArray[] = line.split("ç");
		
		customer.setCnpj(lineArray[1]);
		customer.setName(lineArray[2]);
		customer.setBusiness_area(lineArray[3]);
		
		DataWarehouse.addCustomer(customer);
	}

}
