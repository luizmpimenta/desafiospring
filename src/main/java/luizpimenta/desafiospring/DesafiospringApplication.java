package luizpimenta.desafiospring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import luizpimenta.desafiospring.util.ApplicationConfiguration;

@SpringBootApplication
public class DesafiospringApplication {

	public static void main(String[] args) {	
		SpringApplication sa = new SpringApplication(DesafiospringApplication.class);
	    ConfigurableApplicationContext context = sa.run(args);
	}
	
}
