package lv.helloit.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//STEP 1. Import required packages
import java.sql.*;

@SpringBootApplication
public class TrelloApplication {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://ec2-54-246-85-234.eu-west-1.compute.amazonaws.com:5432/d38fg66uk8rvv9?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	//  Database credentials
	private static final String USER = "ztacdohjspyclv";
	private static final String PASS = "3d0cc2c4883e31c3a81df63bae6235dd3812609b831da382a3737b20df5e5aad";

	public static void main(String[] args) {

		SpringApplication.run(TrelloApplication.class, args);

	}
}
