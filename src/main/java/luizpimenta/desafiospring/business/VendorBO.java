package luizpimenta.desafiospring.business;

import java.math.BigDecimal;

import luizpimenta.desafiospring.model.Vendor;
import luizpimenta.desafiospring.util.DataWarehouse;

public class VendorBO implements BusinessInterface {

	public VendorBO(){}
	
	public void create(String line) {
		Vendor vendor = new Vendor();		
		String lineArray[] = line.split("ç");
		
		vendor.setCpf(lineArray[1]);
		vendor.setName(lineArray[2]);
		vendor.setSalary(new BigDecimal(lineArray[3]));
		
		DataWarehouse.addVendors(vendor);
	}

}
