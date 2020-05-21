package javaProject.controller;

import javaProject.model.Account;
import javaProject.model.Client;
import javaProject.model.Employee;
import javaProject.model.Transaction;
import javaProject.service.AccountService;
import javaProject.service.ClientService;
import javaProject.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
	private static Scanner scanner;
	private static ClientService employeeService;
	private static AccountService accountService;
	private static TransactionService transactionService;

	public static void run(Employee employee) {

		scanner = new Scanner(System.in);
		employeeService = new ClientService();
		accountService = new AccountService();
		transactionService = new TransactionService();

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
							"4- Ban client account \n ";
			String response;
			do {
				System.out.println(actionMenu);
				response = scanner.nextLine();
			}  while (response.isEmpty());


		}

//		cleaning();

	}

	private static void withdraw() {

		String msg = "How much you want to withdraw ?? \n";
		System.out.println(msg);

		double amount = scanner.nextDouble();
		msg = "From which account ?? \n";
		System.out.println(msg);

		Long accountId = scanner.nextLong();
		System.out.println("Fetching account details..");
		Account account = accountService.getAccountById(accountId);

		// Check if there is enough fund in the account
		if (account.getBalance() >= amount) {
			// Perform action
			System.out.println("withdraw fund from account..");
			account.removeFromBalance(amount);
			accountService.updateAccount(account);
			// Create a transaction
			System.out.println("Create transaction..");
			Transaction transaction =
					new Transaction(Transaction.WITHDRAW, amount, "0", LocalDate.now(), account.getAccountBankId(), account.getAccountBankName(), accountId);
			transactionService.addNewTransaction(transaction);
			System.out.println("DONE");
			System.out.println("New Balance: " + account.getBalance()+ "DT");
		} else {
			System.out.println("No Enough fund in account!!");
		}
	}

	private static void depose() {

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
		Transaction transaction = new Transaction(Transaction.DEPOSIT, amount, "0", LocalDate.now(),
				account.getAccountBankId(), account.getAccountBankName(), accountId);
		transactionService.addNewTransaction(transaction);
		System.out.println("DONE");
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
