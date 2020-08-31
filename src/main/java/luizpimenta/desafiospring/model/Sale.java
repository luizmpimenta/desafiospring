package luizpimenta.desafiospring.model;

import java.util.ArrayList;

public class Sale {

	private int id;
	private ArrayList<SaleItem> items;
	private Vendor vendor;
	private double totalPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<SaleItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<SaleItem> items) {
		this.items = items;
	}
	
}
