package javaProject.controller;

import javaProject.model.Account;
import javaProject.model.Client;
import javaProject.model.Transaction;
import javaProject.service.AccountService;
import javaProject.service.ClientService;
import javaProject.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * The type Client controller.
 */
public class ClientController {

	private static Scanner  scanner;
	private static ClientService clientService;
	private static AccountService accountService;
	private static TransactionService transactionService;

	/**
	 * Run.
	 *
	 * @param client the client
	 */
	public static void run(Client client) {

		scanner = new Scanner(System.in);
		clientService = new ClientService();
		accountService = new AccountService();
		transactionService = new TransactionService();

		greeting(client);
		while (true) {
			// Choice action
			String actionMenu =
					"Choice which action you want to perform: \n 1- depose \n 2- withdraw \n 3- show account list\n";
			String response;
			do {
				System.out.println(actionMenu);
				response = scanner.nextLine();
			}  while (response.isEmpty());

			// Depose fund to client account
			if (response.contains("1")) {
				depose();
			}
			else if (response.contains("2")) {
				withdraw();
			}
			else if (response.contains("3")) {
				List accounts = accountService.getAccountsByClientId(client.getId());
				System.out.println("You have " + accounts.size() + " accounts");
				String msg = "Here is you account list: \n";
				System.out.println(msg);
				accounts.forEach(o -> {
					System.out.println(accounts.toString());
				});
			} else {
				break;
			}
		}

		cleaning();

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
		clientService.submitChange();
		accountService.submitChange();
		transactionService.submitChange();
	}

	private static void greeting(Client client) {

		String greetingMsg = "Welcome ";
		if (client.getGender().equals("homme")) {
			greetingMsg += "Mr ";
		} else {
			greetingMsg += "Ms ";
		}
		greetingMsg +=  client.getFirstName() + " " + client.getLastName() + '\n';
		greetingMsg +=  "You clientId is: "+ client.getId() + "\n";
		System.out.println(greetingMsg);
	}

}
