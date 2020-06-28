package javaProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * The type Account.
 */
@Entity
@Table(name = "account")
public class Account {

	public static final String CHECKING = "checking";
	public static final String SAVINGS = "savings";
	public static final String MAXI_SAVINGS = "maxi savings";


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", allocationSize = 1)
	private long id;

	private String accountType;

	private double balance;

	private Long clientId;

	private String accountBankId;

	private String accountBankName;

	private Boolean isActive;

	private LocalDate lastInterestCalDate;

	/**
	 * Instantiates a new Account.
	 */
	public Account() {

	}

	/**
	 * Instantiates a new Account.
	 *
	 * @param accountType the account type
	 * @param balance the balance
	 */
	public Account(String accountType, double balance, Long clientId,
			String accountBankName, String accountBankId) {

		this.accountType = accountType;
		this.balance = balance;
		this.clientId = clientId;
		this.accountBankId = accountBankId;
		this.accountBankName = accountBankName;
		this.lastInterestCalDate = LocalDate.now();
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public long getId() {

		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(long id) {

		this.id = id;
	}

	/**
	 * Gets account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {

		return accountType;
	}

	/**
	 * Sets account type.
	 *
	 * @param accountType the account type
	 */
	public void setAccountType(String accountType) {

		this.accountType = accountType;
	}

	/**
	 * Gets balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {

		return balance;
	}

	/**
	 * Sets balance.
	 *
	 * @param balance the balance
	 */
	public void setBalance(double balance) {

		this.balance = balance;
	}

	/**
	 * Gets client id.
	 *
	 * @return the client id
	 */
	public Long getClientId() {

		return clientId;
	}

	/**
	 * Sets client id.
	 *
	 * @param clientId the client id
	 */
	public void setClientId(Long clientId) {

		this.clientId = clientId;
	}

	/**
	 * Gets active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {

		return isActive;
	}

	/**
	 * Sets active.
	 *
	 * @param active the active
	 */
	public void setActive(Boolean active) {

		isActive = active;
	}

	/**
	 * Gets account bank id.
	 *
	 * @return the account bank id
	 */
	public String getAccountBankId() {

		return accountBankId;
	}

	/**
	 * Sets account bank id.
	 *
	 * @param accountBankId the account bank id
	 */
	public void setAccountBankId(String accountBankId) {

		this.accountBankId = accountBankId;
	}

	/**
	 * Gets account bank name.
	 *
	 * @return the account bank name
	 */
	public String getAccountBankName() {

		return accountBankName;
	}

	/**
	 * Sets account bank name.
	 *
	 * @param accountBankName the account bank name
	 */
	public void setAccountBankName(String accountBankName) {

		this.accountBankName = accountBankName;
	}

	/**
	 * Gets interest percentage (%) By year.
	 *
	 * @return the interest
	 */
	public double getInterest() {
		if (accountType.equals(CHECKING)) {
			return 0.1;
		} else if (accountType.equals(SAVINGS)) {
			if (balance > 1000) return 0.2;
			return 0.2;
		} else {
			if (balance < 1000) return 2;
			else if (balance < 2000) return 5;
			return 10;
		}
	}

	@Override
	public String toString() {

		return "Account " + "id: " + id + ", accountType: '" + accountType + '\'' + ", balance: "
				+ balance + ", clientId: '" + clientId + '\'' + ", isActive: " + isActive
				+ ", Interest rate: " + getInterest() + "%" ;
	}

	/**
	 * remove from balance.
	 *
	 * @param amount the balance
	 */
	public void removeFromBalance(double amount) {
		this.balance -= amount;
	}

	/**
	 * Add to balance.
	 *
	 * @param amount the balance
	 */
	public void addToBalance(double amount) {

		this.balance += amount;
	}

	/**
	 * Add interest to account balance.
	 */
	public void addInterestToAccountBalance() {
		Period period = Period.between(LocalDate.now(), lastInterestCalDate);
		float diff = period.getDays();
		 balance += (balance/100) * getInterest() * (diff / 365);
		 lastInterestCalDate = LocalDate.now();
	}
}
