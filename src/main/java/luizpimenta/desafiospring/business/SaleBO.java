package luizpimenta.desafiospring.business;

import java.util.ArrayList;

import luizpimenta.desafiospring.model.Sale;
import luizpimenta.desafiospring.model.SaleItem;
import luizpimenta.desafiospring.util.DataWarehouse;

public class SaleBO implements BusinessInterface {

	public SaleBO(){}
	
	public void create(String line) {
		Sale sale = new Sale();		
		String lineArray[] = line.split("ç");
		
		sale.setId(Integer.parseInt(lineArray[1]));
		sale.setItems(getItemsFromLine(lineArray[2]));
		sale.setTotalPrice(
				sale.getItems().stream()
			    .mapToDouble(a -> a.getPrice())
			    .sum()
		);
		sale.setVendor(DataWarehouse.getVendorByName(lineArray[3]));
				  
		DataWarehouse.addSale(sale);		
	}
	
	private ArrayList<SaleItem> getItemsFromLine(String line){
		ArrayList<SaleItem> saleItens = new ArrayList<SaleItem>();
		for (final String saleInformation : line.replaceAll("\\[|\\]", "").split(",")){
			SaleItem saleItem = new SaleItem();
			String[] saleInformationDetail = saleInformation.split("-");
			saleItem.setId(Integer.parseInt(saleInformationDetail[0]));
			saleItem.setQuantity(Integer.parseInt(saleInformationDetail[1]));
			saleItem.setPrice(Double.parseDouble(saleInformationDetail[2]));
			saleItens.add(saleItem);
		}
		return saleItens;
	}

}
