
package javaProject;

import javaProject.controller.ClientController;
import javaProject.model.Account;
import javaProject.model.Bank;
import javaProject.model.Client;
import javaProject.model.Employee;
import javaProject.model.Transaction;
import javaProject.service.ClientService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * How to connect to a mySQL Database.
 *
 * @author Jon Bonso
 *
 */
public class DatabaseConnection {

	private static ClientService clientService;
	private static Scanner  scanner;


	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		clientService = new ClientService();

		initDB();
		testDB();
		// Who are you action
		String actionMenu =
				"Login as: \n 1- client \n 2- employee \n 3- manager\n";
		String response;
		do {
			System.out.println(actionMenu);
			response = scanner.nextLine();
		} while (response.isEmpty());

		if (response.contains("1")) {
			// username
			String msg = "Enter you clientId: \n";
			System.out.println(msg);
			Long idClient = scanner.nextLong();
			// password
			msg = "Enter you secret code: \n";
			System.out.println(msg);
			String code = scanner.next();

			// Get Client
			Client client = clientService.getClientById(idClient);
			if (client.getSecretCode().equals(code)) {
				System.out.println("Login success !!");
				ClientController.run(client);
			} else {
				System.out.println("Somethings went wrong !!");
			}

		}
	}


	/**
	 * Connect to MySQL Database
	 * @throws SQLException
	 */
	private static void connectToDB() throws SQLException{

		// 1. Get the Connection instance using the DriverManager.getConnection() method 
		//    with your MySQL Database Credentails
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaProject20",
				"javaPrj20", "");

		System.out.println("LOG: Connection Established!");

		// 2. Execute your SQL Query using conn.createStatement.executeQuery() 
		//    and get the result as a ResultSet object.
		//    with your MySQL Database Credentails

		ResultSet rs   = conn.createStatement().executeQuery("select * from user");
		ResultSetMetaData rsmd = rs.getMetaData();

		System.out.println("Query Results: \n\n");

		// Show Column Names
		getColumnNames(rsmd);

		// Getting the Results
		while (rs.next()){
			for ( int i=1; i <= rsmd.getColumnCount(); i++){;
				System.out.print(rs.getString(i) + "\t\t");
			}

			System.out.println();
		}
	}

	/**
	 * Shows the Column Names
	 * @param rsmd
	 * @throws SQLException
	 */
	private static void getColumnNames(ResultSetMetaData rsmd) throws SQLException{
		// Getting the list of COLUMN Names
		for ( int i=1; i <= rsmd.getColumnCount(); i++){
			System.out.print(rsmd.getColumnName(i) + "\t\t|");
		}

		System.out.println("");
	}

	private static void initDB() {
		try {

			System.out.println("Trying to connect to DB...");


			SessionFactory sessionFactory;
			// configures settings from hibernate.cfg.xml
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

			// Establishing connection to DB
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("Connection established");

			//Create new Bank
			Bank bank = new Bank("ATB", "Dejerba Ajim 4135", "+21653415455");
			session.save(bank);
			System.out.println("New Bank created");



			// Create a test query to fetch the created bank
			List<Bank> result = session.createQuery("from Bank", Bank.class).list();
			System.out.println("Registered bank: " + result.size());

			result.forEach(bank1 -> {
				System.out.println(bank1.getId()+ ": " + bank1.toString());
			});

			// Close connection
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}


	private static void testDB() {
		try {

			System.out.println("Trying to connect to DB...");


			SessionFactory sessionFactory;
			// configures settings from hibernate.cfg.xml
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

			// Establishing connection to DB
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("Connection established");

			//Create new client
			Client client = new Client("Ahmed", "Benyahia", "Ajim Derjba",
					"23", "53415455", "homme", "1351214",
					"ahmed@benyahia.tn", true, "4135");
			client.setActive(true);
			client.setId(2);
			client.setSecretCode("0000");
			session.save(client);
			System.out.println("New Client created");


			// Create a test query to fetch the created client
			List<Client> resultC = session.createQuery("from Client", Client.class).list();
			System.out.println("Registered client: " + resultC.size());

			resultC.forEach(client1 -> {
				System.out.println(client1.getId()+ ": " + client1.toString());
			});


			//Create new employee
			Employee employee = new Employee("Ahmed", "Benyahia",
					"Ajim Derjba",
					"23", "53415455", "homme", "1351214",
					"ahmed@benyahia.tn", true, "4135",
					"assistant", 1200);
			employee.setEmploymentBankId("1");
			employee.setEmploymentBankName("ATB");
			employee.setActive(true);
			session.save(employee);
			System.out.println("New Employee created");


			// Create a test query to fetch the created employee
			List<Employee> resultE = session.createQuery("from Employee", Employee.class).list();
			System.out.println("Registered employee: " + resultE.size());

			resultE.forEach(employee1 -> {
				System.out.println(employee1.getId()+ ": " + employee1.toString());
			});


			//Create new account
			Account account = new Account(Account.MAXI_SAVINGS, 999, (long) 2, "1", "ATB");
			account.setActive(true);
			session.save(account);
			System.out.println("New Account created");


			// Create a test query to fetch the created account
			List<Account> resultA = session.createQuery("from Account", Account.class).list();
			System.out.println("Registered account: " + resultA.size());

			resultA.forEach(account1 -> {
				System.out.println(account1.getId()+ ": " + account1.toString());
			});

			//Create new transaction
			Transaction transaction = new Transaction(Transaction.DEPOSIT, 999, "2", LocalDate.now(),
					"1", "ATB", (long) 3);
			session.save(transaction);
			System.out.println("New Transaction created");


			// Create a test query to fetch the created transaction
			List<Transaction> resultT = session.createQuery("from Transaction", Transaction.class).list();
			System.out.println("Registered transaction: " + resultT.size());

			resultT.forEach(transaction1 -> {
				System.out.println(transaction1.getId()+ ": " + transaction1.toString());
			});

			// Close connection
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
}
