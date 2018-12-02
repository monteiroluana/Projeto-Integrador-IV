package br.com.ecommerce.childplay;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChildPlayApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(ChildPlayApplication.class, args);                
	}
}
