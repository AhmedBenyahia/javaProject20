package javaProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * The type Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

	/**
	 * The constant DEPOSIT.
	 */
	public static final String DEPOSIT = "deposit";
	/**
	 * The constant WITHDRAW.
	 */
	public static final String WITHDRAW = "withdraw";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", allocationSize = 1)
	private long id;

	private String transactionType;

	private double amount;

	private String performedBy;

	private LocalDate performedAt;

	private String accountBankId;

	private String accountBankName;

	private Long accountId;

	/**
	 * Instantiates a new Transaction.
	 */
	public Transaction() {

	}

	/**
	 * Instantiates a new Transaction.
	 *  @param transactionType the transaction type
	 * @param amount the amount
	 * @param performedBy the performed by
	 * @param performedAt the performed at
	 * @param accountBankId the account bank id
	 * @param accountBankName the account bank name
	 * @param accountId the account id
	 */
	public Transaction(String transactionType, double amount, String performedBy, LocalDate performedAt,
			String accountBankName, String accountBankId, Long accountId) {

		this.transactionType = transactionType;
		this.amount = amount;
		this.performedBy = performedBy;
		this.performedAt = performedAt;
		this.accountBankId = accountBankId;
		this.accountBankName = accountBankName;
		this.accountId = accountId;
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
	 * Gets transaction type.
	 *
	 * @return the transaction type
	 */
	public String getTransactionType() {

		return transactionType;
	}

	/**
	 * Sets transaction type.
	 *
	 * @param transactionType the transaction type
	 */
	public void setTransactionType(String transactionType) {

		this.transactionType = transactionType;
	}

	/**
	 * Gets amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {

		return amount;
	}

	/**
	 * Sets amount.
	 *
	 * @param amount the amount
	 */
	public void setAmount(double amount) {

		this.amount = amount;
	}

	/**
	 * Gets performed by.
	 *
	 * @return the performed by
	 */
	public String getPerformedBy() {

		return performedBy;
	}

	/**
	 * Sets performed by.
	 *
	 * @param performedBy the performed by
	 */
	public void setPerformedBy(String performedBy) {

		this.performedBy = performedBy;
	}

	/**
	 * Gets performed at.
	 *
	 * @return the performed at
	 */
	public LocalDate getPerformedAt() {

		return performedAt;
	}

	/**
	 * Sets performed at.
	 *
	 * @param performedAt the performed at
	 */
	public void setPerformedAt(LocalDate performedAt) {

		this.performedAt = performedAt;
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
	 * Gets account id.
	 *
	 * @return the account id
	 */
	public Long getAccountId() {

		return accountId;
	}

	/**
	 * Sets account id.
	 *
	 * @param accountId the account id
	 */
	public void setAccountId(Long accountId) {

		this.accountId = accountId;
	}

	@Override
	public String toString() {

		return "Transaction " + "id: " + id + ", transactionType: '" + transactionType + '\'' + ", amount: " + amount + ", performedBy: '" + performedBy + '\'' + ", performedAt: " + performedAt + ", accountBankId: '" + accountBankId + '\'' + ", accountBankName: '" + accountBankName + '\'' + ", accountId: '" + accountId + '\'';
	}
}
