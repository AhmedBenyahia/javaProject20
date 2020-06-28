package javaProject.controller;

import javaProject.model.Account;
import javaProject.model.Client;
import javaProject.model.Employee;
import javaProject.model.Transaction;
import javaProject.service.AccountService;
import javaProject.service.ClientService;
import javaProject.service.EmployeeService;
import javaProject.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
	private static Scanner scanner;
	private static EmployeeService employeeService;
	private static AccountService accountService;
	private static TransactionService transactionService;
	private static ClientService clientService;

	public static void run(Employee employee) {

		scanner = new Scanner(System.in);
		employeeService = new EmployeeService();
		accountService = new AccountService();
		transactionService = new TransactionService();
		clientService = new ClientService();

		greeting(employee);
		while (true) {
			// Choice action
			String actionMenu =
					"Choice which action you want to perform: \n " +
							"1- depose funds to client account \n " +
							"2- withdraw funds from client account\n " +
							"3- Transfer funds \n " +
							"4- Create new Account for Client \n " +
							"5- Add new Client \n " +
							"6- Show  Clients List \n " +
							"7- Show  Account Transaction \n " + //Period  // employee
							"8- Add new employee \n " +
							"9- Calculate and Add Interest to Client Account \n " +
							"10- logout and save changes \n ";
			String response;
			System.out.println(actionMenu);
			do {
				response = scanner.nextLine();
			}  while (response.isEmpty());

			// Depose fund to client account
			if (response.equals("1")) {
				depose(employee);
			}
			else if (response.contains("2")) {
				withdraw(employee);
			}

			else if (response.contains("3")) {
				TransferFund(employee);
			}

			else if (response.contains("4")) {
				createAccount(employee);
			}

			else if (response.contains("5")) {
				addNewClient(employee);
			}

			else if (response.contains("6")) {
				showClientList(employee);
			}

			else if (response.contains("7")) {
				showAccountTransaction(employee);
			}

			else if (response.contains("8")) {
				addNewEmployee(employee);
			}

			else if (response.contains("9")) {
				calClientInterest();
			}
			else if (response.contains("10")) {
				break;
			}
		}

		cleaning();

	}

	private static void calClientInterest() {
		String msg = " For which account ?? \n";
		System.out.println(msg);

		Long accountId = scanner.nextLong();
		System.out.println("Fetching account details..");
		Account account = accountService.getAccountById(accountId);
		System.out.println("Current account balance: " + account.getBalance() + "DT");
		account.addInterestToAccountBalance();
		accountService.updateAccount(account);
		System.out.println("New account balance: " + account.getBalance() + "DT");


	}

	private static void addNewEmployee(Employee e) {
		if (e.getJobTitle().equals("Manager")) {
			System.out.println("Employee Name ");
			String name = scanner.nextLine();
			System.out.println("Employee Surname ");
			String surname = scanner.nextLine();
			System.out.println("Employee Address");
			String add = scanner.nextLine();
			System.out.println("Employee Gender (h/m)");
			String gender = scanner.nextLine();
			System.out.println("Employee phone");
			String phone = scanner.nextLine();
			System.out.println("Employee email");
			String email = scanner.nextLine();
			System.out.println("Employee post code");
			String postCode = scanner.nextLine();
			System.out.println("Employee age");
			String age = scanner.nextLine();
			System.out.println("Employee cin");
			String cin = scanner.nextLine();
			System.out.println("Employee secretCode");
			String code = scanner.nextLine();
			System.out.println("Employee job title");
			String job = scanner.nextLine();
			System.out.println("Employee salarie");
			double salary = scanner.nextDouble();
			Employee employee =
					new Employee(name, surname, add, age, phone, gender, cin, email, true, postCode,
							job, salary);
			employee.setActive(true);
			employee.setId(2);
			//TODO: ADD Auto generation  for secret code
			employee.setSecretCode(code);
			System.out.println("New " + job +" created");
			employeeService.addNewEmployee(employee);
		} else {
			System.out.println("You don't have the permission to do that !!");
		}
	}

	private static void showAccountTransaction(Employee employee) {
		List transactions = null;
		boolean test =false;
		String employeeId;
		String periodStartDate;
		String periodEndDate;
		do {

			System.out.println(
					"Choice The filter type: \n " +
							"1- filter by performed employee \n " +
							"2- filter by period\n " +
							"3- Show all \n");
			String response = scanner.nextLine();
			if (response.contains("1")) {
				System.out.println("Employee Id:");
				employeeId = scanner.nextLine();
				transactions = transactionService.getAllEmployeeTransaction(employeeId);
				test = true;
			}
			else if (response.contains("2")) {
				System.out.println("Period start date Id d/MM/yyyy:");
				periodStartDate = scanner.nextLine();
				System.out.println("Period end date Id d/MM/yyyy: ");
				periodEndDate = scanner.nextLine();
				transactions = transactionService.getTransactionByPeriod(periodStartDate, periodEndDate);				test = true;

			}
			else if (response.contains("3")) {
				transactions = transactionService.getAllTransaction();
				test = true;
			}
			else {
				System.out.println("Sorry, but you choice is invalid, try again !!");
			}
		} while (!test) ;
		System.out.println("You have " + transactions.size() + " transactions");
		String msg = "Here is the transactions list: \n";
		System.out.println(msg);
		transactions.forEach(o -> {
			System.out.println(o.toString());
		});

	}

	private static void showClientList(Employee employee) {
		List clients = clientService.getAllClient();
		System.out.println("You have " + clients.size() + " clients");
		String msg = "Here is the client list: \n";
		System.out.println(msg);
		clients.forEach(o -> {
			System.out.println(o.toString());
		});
	}

	private static void addNewClient(Employee employee) {
		System.out.println("Client Name ");
		String name = scanner.nextLine();
		System.out.println("Client Surname ");
		String surname = scanner.nextLine();
		System.out.println("Client Address");
		String add = scanner.nextLine();
		System.out.println("Client Gender (h/m)");
		String gender = scanner.nextLine();
		System.out.println("Client phone");
		String phone = scanner.nextLine();
		System.out.println("Client email");
		String email = scanner.nextLine();
		System.out.println("Client post code");
		String postCode = scanner.nextLine();
		System.out.println("Client age");
		String age = scanner.nextLine();
		System.out.println("Client cin");
		String cin = scanner.nextLine();
		System.out.println("Client secretCode");
		String code = scanner.nextLine();
		Client client = new Client(name, surname, add,
				age, phone, gender, cin,
				email, true, postCode);
		client.setActive(true);
		client.setId(2);
		//TODO: ADD Auto generation  for secret code
		client.setSecretCode(code);
		System.out.println("New Client created");
		clientService.addNewClient(client);
	}

	private static void createAccount(Employee employee) {
		System.out.println("Who is the client, Client Id:");
		Long clientId = scanner.nextLong();
		String accountType = Account.CHECKING;
		boolean test = false;
		do {
			System.out.println(
					"Choice The account type: \n " +
							"1- CHECKING \n " +
							"2- SAVINGS\n " +
							"3- MAXI SAVINGS \n");
			String response = scanner.nextLine();
			if (response.contains("1")) {
				accountType = Account.CHECKING;
				test = true;
			} else if (response.contains("2")) {
				accountType = Account.SAVINGS;
				test = true;

			} else if (response.contains("3")) {
				accountType = Account.MAXI_SAVINGS;
				test = true;
			}
			else {
				System.out.println("Sorry, but you choice is invalid, try again !!");
			}
		} while (!test);



		//Create new account
		Account account = new Account(accountType, 0, (long) clientId,
				employee.getEmploymentBankName(), employee.getEmploymentBankId());
		account.setActive(true);
		accountService.addNewAccount(account);
		System.out.println("New Account created");
	}

	private static void TransferFund(Employee employee) {

		String msg = "How much you want to transfer ?? \n";
		System.out.println(msg);
		double amount = scanner.nextDouble();

		msg = "From which account ?? \n";
		System.out.println(msg);
		Long accountIdSource = scanner.nextLong();

		msg = "In which account ?? \n";
		System.out.println(msg);
		Long accountIdReceiver = scanner.nextLong();
		System.out.println("Fetching receiver account details..");
		Account accountReceiver = accountService.getAccountById(accountIdReceiver);

		System.out.println("Fetching source account details..");
		Account accountSource = accountService.getAccountById(accountIdSource);

		// Check if there is enough fund in the account
		if (accountSource.getBalance() >= amount) {
			// Perform action
			System.out.println("withdraw fund from account..");
			accountSource.removeFromBalance(amount);
			accountService.updateAccount(accountSource);

			System.out.println("Depose fund to account..");
			accountReceiver.addToBalance(amount);
			accountService.updateAccount(accountReceiver);


			// Create a WITHDRAW transaction
			System.out.println("Create WITHDRAW transaction..");
			Transaction transaction =
					new Transaction(Transaction.WITHDRAW, amount, String.valueOf(employee.getId()),
							LocalDate.now(), accountSource.getAccountBankId(),
							accountSource.getAccountBankName(), accountIdSource);
			transactionService.addNewTransaction(transaction);
			System.out.println("Source account New Balance: " + accountSource.getBalance()+ "DT");

			// Create a DEPOSIT transaction
			System.out.println("Create transaction..");
			transaction = new Transaction(Transaction.DEPOSIT, amount, "0", LocalDate.now(),
					accountReceiver.getAccountBankId(),
					accountReceiver.getAccountBankName(), accountIdReceiver);
			transactionService.addNewTransaction(transaction);
			System.out.println("Receiver accountReceiver New Balance: " + accountReceiver.getBalance()+ "DT");

		} else {
			System.out.println("No Enough fund in account!!");
		}
	}

	private static void withdraw(Employee employee) {

		String msg = "How much you want to withdraw ?? \n";
		System.out.println(msg);

		double amount = scanner.nextDouble();
		msg = "From which client account ?? \n";
		System.out.println(msg);

		Long accountId = scanner.nextLong();
		System.out.println("Fetching account details..");
		Account account = accountService.getAccountById(accountId);

		// Check if there is enough fund in the account
		if (account != null) {
			if (account.getBalance() >= amount) {
				// Perform action
				System.out.println("withdraw fund from account..");
				account.removeFromBalance(amount);
				accountService.updateAccount(account);
				// Create a transaction
				System.out.println("Create transaction..");
				Transaction transaction =
						new Transaction(Transaction.WITHDRAW, amount, String.valueOf(employee.getId()),
								LocalDate.now(), account.getAccountBankId(),
								account.getAccountBankName(), accountId);
				transactionService.addNewTransaction(transaction);
				System.out.println("New Balance: " + account.getBalance()+ "DT");
			} else {
				System.out.println("No Enough fund in account !!");
			}
		} else {
			System.out.println("Account Id Not found !!");
		}
	}

	private static void depose(Employee employee) {

		String msg = "How much you want to depose in DT?? \n";
		System.out.println(msg);

		double amount = scanner.nextDouble();
		msg = "In which account ?? \n";
		System.out.println(msg);

		Long accountId = scanner.nextLong();
		System.out.println("Fetching account details..");
		Account account = accountService.getAccountById(accountId);
		System.out.println("Current account balance: " + account.getBalance() + "DT");

		// Perform action
		System.out.println("Add fund to account..");
		account.addToBalance(amount);
		accountService.updateAccount(account);
		// Create a transaction
		System.out.println("Create transaction..");
		Transaction transaction =
				new Transaction(Transaction.DEPOSIT, amount, String.valueOf(employee.getId()),
						LocalDate.now(),
				account.getAccountBankId(), account.getAccountBankName(), accountId);
		transactionService.addNewTransaction(transaction);
		System.out.println("New Balance: " + account.getBalance()+ "DT");
	}

	private static void cleaning() {
		employeeService.submitChange();
		accountService.submitChange();
		transactionService.submitChange();
	}

	private static void greeting(Employee employee) {

		String greetingMsg = "Welcome !! \n";
		greetingMsg +=  "You employeeId is: "+ employee.getId() + "\n";
		System.out.println(greetingMsg);
	}
}
