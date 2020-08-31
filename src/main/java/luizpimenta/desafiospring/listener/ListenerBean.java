package luizpimenta.desafiospring.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import luizpimenta.desafiospring.business.BusinessFactory;
import luizpimenta.desafiospring.business.BusinessInterface;
import luizpimenta.desafiospring.util.ApplicationConfiguration;
import luizpimenta.desafiospring.util.DataWarehouse;

@Component
public class ListenerBean {

	ApplicationConfiguration configuration = new ApplicationConfiguration();

	@EventListener
	public void handleEvent(Object event) {

		try {
			while (true) {
				String fileLine;
				File folder = new File(configuration.readProperty("FILE_IMPORT_FOLDER"));
				for (final File file : folder.listFiles()) {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
					while ((fileLine = bufferedReader.readLine()) != null) {
						BusinessInterface myObj = BusinessFactory.createModelByString(fileLine);
						myObj.create(fileLine);
					}
					exportFile(file.getName());
					bufferedReader.close();
					moveFileToDone(file);
					DataWarehouse.clear();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	private void exportFile(String filename) throws Exception {
		File file = new File(configuration.readProperty("FILE_EXPORT_FOLDER") + filename);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write("====================================================");
		bufferedWriter.newLine();
		bufferedWriter.write("Quantidade de Clientes: " + DataWarehouse.getCustomers().size());
		bufferedWriter.newLine();
		bufferedWriter.write("Quantidade de Vendedores: " + DataWarehouse.getVendors().size());
		bufferedWriter.newLine();
		bufferedWriter.write("Venda mais cara: " + DataWarehouse.findByExpensiveSale().getTotalPrice());
		bufferedWriter.newLine();
		bufferedWriter.write("Pior vendedor: " + DataWarehouse.findWorstVendor().getName());
		bufferedWriter.newLine();
		bufferedWriter.write("====================================================");
		bufferedWriter.close();
	}

	private void moveFileToDone(File file) throws Exception {
		Files.move(Paths.get(configuration.readProperty("FILE_IMPORT_FOLDER") + file.getName()),
				Paths.get(configuration.readProperty("FILE_DONE_FOLDER") + file.getName()),
				StandardCopyOption.REPLACE_EXISTING);
	}

}
