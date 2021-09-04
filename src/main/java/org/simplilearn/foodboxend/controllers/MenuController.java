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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.simplilearn.foodboxend.entities.Menu;

@Component
@RestController
public class MenuController {

	
	@CrossOrigin(origins = "*")
	@GetMapping("/get_menu")
	public List<Menu> get_menu() {
		Connection c = null;
		Statement stmt = null;
		List<Menu> menuList = new ArrayList<Menu>();
		
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "SELECT * FROM menu";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu newMenu = new Menu();
				newMenu.setId(rs.getInt("id"));
				newMenu.setDish(rs.getString("dish"));
				newMenu.setCuisine(rs.getString("cuisine"));
				newMenu.setPrice(rs.getInt("price"));
				menuList.add(newMenu);
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("dish"));
				System.out.println(rs.getString("cuisine"));
				System.out.println(rs.getString("price"));
			}
			c.close();
			return menuList;
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/add_menuitem")
	public boolean add_menuitem(@RequestBody Map<String, Object> payload) {

		String id = payload.get("id").toString();
		String dish = payload.get("dish").toString();
		String cuisine = payload.get("cuisine").toString();
		String price = payload.get("price").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "INSERT INTO menu (id, dish, cuisine, price) values ('" + id + "', '" + dish + "', '" + cuisine
					+ "', '" + price + "');";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/change_menuitem")
	public boolean change_menuitem(@RequestBody Map<String, Object> payload) {

		String id = payload.get("id").toString();
		String dish = payload.get("dish").toString();
		String cuisine = payload.get("cuisine").toString();
		String price = payload.get("price").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "UPDATE menu SET dish='" + dish + "', cuisine='" + cuisine + "', price='" + price
					+ "' WHERE id='" + id + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/delete_menuitem")
	public boolean delete_menuitem(@RequestBody Map<String, Object> payload) {

		String dish = payload.get("dish").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "DELETE FROM menu WHERE dish='" + dish + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/change_price")
	public boolean change_price(@RequestBody Map<String, Object> payload) {

		String id = payload.get("id").toString();
		String price = payload.get("price").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "UPDATE menu SET price='" + price + "' WHERE id='" + id + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/change_dish")
	public boolean change_dish(@RequestBody Map<String, Object> payload) {

		String id = payload.get("id").toString();
		String dish = payload.get("dish").toString();
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/db_example?" + "user=root&password=F@!th973@");
			stmt = c.createStatement();
			String sql = "UPDATE menu SET dish='" + dish + "' WHERE id='" + id + "';";
			boolean rs = stmt.execute(sql);
			c.close();
			return !rs;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

}
