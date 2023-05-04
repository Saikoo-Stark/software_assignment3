import java.sql.*;

public class CatalogController {
	private App app;
	private Connection conn;

	CatalogController(App app) {
		this.app = app;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:store.db");
		} catch (SQLException e) {
			System.out.println("error while connecting to data base");
		}

		String sql = "CREATE TABLE items (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
				"name TEXT NOT NULL," +
				"category TEXT NOT NULL," +
				"amount INTEGER NOT NULL," +
				"description TEXT NOT NULL," +
				"price REAL NOT NULL," +
				"kind TEXT NOT NULL" +
				");";

		try {
			Statement smt = conn.createStatement();
			smt.executeUpdate(sql);
			addItems();
		} catch (SQLException e) {
		}

		// viewItems();

	}

	void addItems() {
		String sql = "INSERT INTO items(name, category, description, amount, price, kind) VALUES" +
				"('item1', 'coka', 'hello world', 500, 120, 'kilo')," +
				"('item2', 'jjjoka', 'hello emo', 200, 1202, 'package')";

		try {
			Statement smt = conn.createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e + "in catalog");
		}
	}

	void viewItems() {

		String sql = "SELECT * FROM items";

		try {
			Statement smt = conn.createStatement();
			ResultSet res = smt.executeQuery(sql);

			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String category = res.getString("category");
				int amount = res.getInt("amount");
				double price = res.getDouble("price");
				String kind = res.getString("kind");

				System.out.printf("id: %d\nname: %s\ncategory: %s\namount: %d\nprice: %.2f\nkind: %s\n\n",
						id, name, category, amount, price, kind);

			}

		} catch (SQLException e) {
			System.out.println(e + "in catalog");
		}
	}

}
