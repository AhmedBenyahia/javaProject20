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
					"Choice which action you want to perform: \n 1- depose \n 2- withdraw \n" +
							" 3- show account list\n 4- Transfer fund to an other account\n" +
							" 5- logout and save changes";
			String response;
			System.out.println(actionMenu);
			do {
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
					System.out.println(o.toString());
				});
			}
			else if (response.contains("4")) {
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
							new Transaction(Transaction.WITHDRAW, amount, "0",
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
			else if (response.contains("5")) {
				break;
			}
		}
	/* Submit all transaction. The transaction will not be submitted until the client logout */
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

			// Withdraw fund
			System.out.println("withdraw fund from account..");
			account.removeFromBalance(amount);
			accountService.updateAccount(account);

			// Depose fund
			System.out.println("Add fund to account..");
			account.addToBalance(amount);
			accountService.updateAccount(account);

			// Create a transaction
			System.out.println("Create transaction..");
			Transaction transaction =
					new Transaction(Transaction.WITHDRAW, amount, "0", LocalDate.now(),
							account.getAccountBankId(), account.getAccountBankName(), accountId);
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
		System.out.println(" Saving changes...");
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
