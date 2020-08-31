package luizpimenta.desafiospring.business;

public class BusinessFactory {

	public static BusinessInterface createModelByString(String line) throws Exception{
		String[] lineArray = line.split("ç");
		switch (lineArray[0]){
			case "001": return new VendorBO();
			case "002": return new CustomerBO();
			case "003": return new SaleBO();
			default: throw new Exception("Exception: File out of the pattern");
		}
	}
	
}
