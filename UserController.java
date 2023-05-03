import java.sql.*;

public class UserController {
	private App app;
	private Connection conn;

	UserController(App app) {
		this.app = app;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:store.db");
		} catch (SQLException e) {
			System.out.println("error while connecting to data base");
		}

		String sql = "CREATE TABLE IF NOT EXISTS customers (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
				"name TEXT NOT NULL UNIQUE," +
				"email TEXT NOT NULL UNIQUE," +
				"pass TEXT NOT NULL," +
				"address TEXT NOT NULL" +
				");";

		try {
			Statement smt = conn.createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	boolean addCustomer(String email, String name, String address, String pass) {

		String sql = "SELECT COUNT(*) num FROM customers WHERE email = ?";

		try {
			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setString(1, email);
			ResultSet res = smt.executeQuery();

			res.next();
			if (res.getInt("num") > 0) {
				app.showError("Email is used before");
				return false;
			}

		} catch (SQLException e) {

		}

		sql = "INSERT INTO customers ( email, address, pass, name) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setString(1, email);
			smt.setString(2, address);
			smt.setString(3, pass);
			smt.setString(4, name);

			smt.executeUpdate();
			return true;
		} catch (SQLException e) {

		}
		return false;
	}

	Customer getCustomer(String email) {

		String sql = "SELECT * FROM customers WHERE email = ?";
		ResultSet res;
		try {
			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setString(1, email);
			res = smt.executeQuery();
			res.next();
			Customer cust = new Customer(res.getInt("id"),
					res.getString("name"),
					res.getString("email"),
					res.getString("pass"),
					res.getString("address"));
			return cust;
		} catch (SQLException e) {
		}

		return null;

	}

	Customer loginChecker(String email, String pass) {

		String sql = "SELECT COUNT(*) num FROM customers WHERE email = ? and pass = ?";

		try {

			PreparedStatement smt = conn.prepareStatement(sql);
			smt.setString(1, email);
			smt.setString(2, pass);
			ResultSet res = smt.executeQuery();
			res.next();
			if (res.getInt("num") == 1) {
				return getCustomer(email);
			} else {
				app.showError("email and pasword doesn't matched");
				return null;
			}

		} catch (SQLException e) {
		}
		return null;

	}

}
