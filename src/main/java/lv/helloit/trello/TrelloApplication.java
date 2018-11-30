package lv.helloit.trello;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//STEP 1. Import required packages
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TrelloApplication {

//	// JDBC driver name and database URL
//	private static final String JDBC_DRIVER = "org.postgresql.Driver";
//	private static final String DB_URL = "jdbc:postgresql://ec2-54-246-85-234.eu-west-1.compute.amazonaws.com:5432/d38fg66uk8rvv9?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
//
//	//  Database credentials
//	private static final String USER = "ztacdohjspyclv";
//	private static final String PASS = "3d0cc2c4883e31c3a81df63bae6235dd3812609b831da382a3737b20df5e5aad";

	public static void main(String[] args) {

		SpringApplication.run(TrelloApplication.class, args);

	}
}
