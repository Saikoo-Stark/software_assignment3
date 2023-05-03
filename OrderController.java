import java.sql.*;

public class OrderController {
	private App app;
	private Connection conn;

	OrderController(App app){
		this.app = app;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:store.db");
		} catch (SQLException e) {
			System.out.println("error while connecting to data base");
		}

	}
}
