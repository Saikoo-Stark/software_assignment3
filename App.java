import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
	private Customer customer;
	private UserController UC;
	private CatalogController CC;
	private OrderController OC;

	App() {
		customer = null;
		UC = new UserController(this);
		CC = new CatalogController(this);
		OC = new OrderController(this);

	}

	public Customer getCustomer() {
		return customer;
	}

	boolean validEmail(String email) {
		Pattern EMAIL_PATTERN = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	void showError(String msg) {
		System.out.printf("(Error) %s\n", msg);
	}

	void register() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your name: ");
		String name = scanner.nextLine();

		System.out.print("Enter your Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter your address: ");
		String address = scanner.nextLine();

		System.out.print("Enter your password hint(must be at least 5 chars): ");
		String pass = scanner.nextLine();

		scanner.close();
		if (validEmail(email) && address.length() > 5 && pass.length() > 4) {

			if (UC.addCustomer(email, name, address, pass))
				System.out.println("good info :>");

		} else {
			showError("given info is incomplete");
		}

	}

	void login() {
		Scanner scanner = new Scanner(System.in);

		// System.out.print("Enter your Email: ");
		// String email = scanner.nextLine();

		System.out.print("Enter your password hint(must be at least 5 chars): ");
		String pass = scanner.nextLine();

		scanner.close();

		// customer = UC.loginChecker(email, pass);
		// if (customer != null) {
		// System.out.println("login done successfully :)");
		// viewCustomer();
		// }

	}

	void viewCustomer() {
		System.out.printf("CustomerId: %d\nCustomerName: %s\nCustomerEmail: %s\nCustomerAddress: %s",
				customer.id, customer.name, customer.email, customer.address);
	}

	void viewCatalog() {

	}

	void addToCart() {

	}

	void viewCart() {

	}

}
