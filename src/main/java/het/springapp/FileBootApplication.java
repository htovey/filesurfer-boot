package het.springapp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import het.springapp.controller.CoreController;

@SpringBootApplication
public class FileBootApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FileBootApplication.class, args);
	}

	
}
