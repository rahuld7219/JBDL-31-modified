package com.example.L09jdbcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class L09JdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(L09JdbcDemoApplication.class, args);
		Connection conn=null;
		try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "app1", "Jbdl@1234");
			Statement statement = conn.createStatement();
			String query ="select * from person";
			ResultSet rs = statement.executeQuery(query); // rs is nothing but like a cursor to the set of rows that are returned
			while (rs.next()){
				System.out.println(rs.getString(2)); // index starts with 1, instead of column number we can also use column name
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
