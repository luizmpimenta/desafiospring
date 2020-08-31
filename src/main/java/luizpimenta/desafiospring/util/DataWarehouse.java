package luizpimenta.desafiospring.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import luizpimenta.desafiospring.model.Customer;
import luizpimenta.desafiospring.model.Sale;
import luizpimenta.desafiospring.model.Vendor;

public class DataWarehouse {

	private static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private static HashMap<String, Vendor> vendors = new HashMap<String, Vendor>();
	private static HashMap<Integer, Sale> sales = new HashMap<Integer, Sale>();
		
	public static HashMap<Integer, Sale> getSale() {
		return sales;
	}
	
	public static Sale findByExpensiveSale() {		
		List<Map.Entry<Integer, Sale>> entryList = new ArrayList<Map.Entry<Integer, Sale>>(sales.entrySet());
		
		Collections.sort(
				entryList, new Comparator<Map.Entry<Integer, Sale>>() {
            @Override
            public int compare(Map.Entry<Integer, Sale> entry1,
                               Map.Entry<Integer, Sale> entry2) {
               return entry1.getValue().getTotalPrice() >= entry2.getValue().getTotalPrice() ? -1 : 1;
            }
        });
		
		return entryList.iterator().next().getValue();
	}
	
	public static Vendor findWorstVendor(){
				
		Map<String, List<Sale>> map = sales.values().stream().collect( 
				Collectors.groupingBy(x-> x.getVendor().getName()) 
			);				
		
		Double actualValue = 0D;
		Double minValue = 0D;
		Vendor response = null;
		for (Vendor vendor : vendors.values()){			
			actualValue = map.containsKey(vendor.getName()) ? map.get(vendor.getName()).stream().mapToDouble(a-> a.getTotalPrice()).sum() : 0;
			if (response == null) {
				response = vendor;
				minValue = actualValue;
			}else{				
				if (minValue > actualValue){
					response = vendor;
					minValue = actualValue;
				}
			}
		}
		
		return response;
		
	}
	
	public static void addSale(Sale sale) {
		sales.put(sale.getId(), sale);
	}
	
	public static HashMap<String, Customer> getCustomers() {
		return customers;
	}
	
	public static void addCustomer(Customer customer) {
		customers.put(customer.getCnpj(), customer);
	}
	
	public static HashMap<String, Vendor> getVendors() {
		return vendors;
	}
	
	public static void addVendors(Vendor vendor) {
		vendors.put(vendor.getName(), vendor);
	}
	
	public static Vendor getVendorByName(String name) {
		return vendors.get(name);
	}
	
	public static void clear(){
		customers.clear();
		vendors.clear();
	}
	
}
