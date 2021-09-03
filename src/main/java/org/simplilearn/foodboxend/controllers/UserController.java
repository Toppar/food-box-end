package org.simplilearn.foodboxend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
@RestController
public class UserController {

	@CrossOrigin(origins = "*")
	@GetMapping("/get_users")
	public List<String> get_users() throws URISyntaxException {
		Connection conn = null;
		List<String> usersList = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");

			Statement stmt = null;
			ResultSet rs = null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");
			rs = stmt.getResultSet();

			while (rs.next()) {
				usersList.add(rs.getString("id"));
				usersList.add(rs.getString("email"));
				usersList.add(rs.getString("first_name"));
				usersList.add(rs.getString("last_name"));
				usersList.add(rs.getString("password"));
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("first_name"));
				System.out.println(rs.getString("last_name"));
				System.out.println(rs.getString("password"));
			}

		} catch (SQLException ex) {
			System.out.println("Data not found!");
		}

		return usersList;
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/check_email")
	public boolean check_email(@RequestBody Map<String, Object> payload) {
		System.out.println(payload);
		String email = payload.get("email").toString();

		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "SELECT (email) FROM users WHERE email='" + email + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String dbPass = rs.getString("email");
			c.close();
			return dbPass.equals(email);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/check_password")
	public boolean check_password(@RequestBody Map<String, Object> payload) {
		System.out.println(payload);
		String email = payload.get("email").toString();
		String password = payload.get("password").toString();

		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "SELECT (password) FROM users WHERE email='" + email + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String dbPass = rs.getString("password");
			c.close();
			return dbPass.equals(password);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/add_user")
	public boolean add_user(@RequestBody Map<String, Object> payload) {
		String id = payload.get("id").toString();
		String email = payload.get("email").toString();
		String firstName = payload.get("first_name").toString();
		String lastName = payload.get("last_name").toString();
		String password = payload.get("password").toString();

		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "INSERT INTO users (id, email, first_name, last_name, password) VALUES ('" + id + "', '"
					+ email + "', '" + firstName + "', '" + lastName + "', '" + password + "');";
			boolean status = stmt.execute(sql);
			c.close();
			return !status;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/change_password")
	public boolean change_password(@RequestBody Map<String, Object> payload) {

		String email = payload.get("email").toString();
		String password = payload.get("password").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "UPDATE users SET password='" + password + "' WHERE email='" + email + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/delete_user")
	public boolean delete_user(@RequestBody Map<String, Object> payload) {

		String email = payload.get("email").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "DELETE FROM users WHERE email='" + email + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

}