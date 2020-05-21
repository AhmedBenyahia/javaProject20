package javaProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The type Bank.
 */
@Entity
@Table(name = "bank")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", allocationSize = 1)
	private long id;

	@Column
	private String bankName;

	@Column
	private String address;

	@Column
	private String phoneNum;

	/**
	 * Instantiates a new Bank.
	 */
	public Bank() {
		//empty
	}

	/**
	 * Instantiates a new Bank.
	 *
	 * @param bankName the bank name
	 * @param address the address
	 * @param phoneNum the phone num
	 */
	public Bank(String bankName, String address, String phoneNum) {

		this.bankName = bankName;
		this.address = address;
		this.phoneNum = phoneNum;
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
	 * Gets bank name.
	 *
	 * @return the bank name
	 */
	public String getBankName() {

		return bankName;
	}

	/**
	 * Sets bank name.
	 *
	 * @param bankName the bank name
	 */
	public void setBankName(String bankName) {

		this.bankName = bankName;
	}

	/**
	 * Gets address.
	 *
	 * @return the address
	 */
	public String getAddress() {

		return address;
	}

	/**
	 * Sets address.
	 *
	 * @param address the address
	 */
	public void setAddress(String address) {

		this.address = address;
	}

	/**
	 * Gets phone num.
	 *
	 * @return the phone num
	 */
	public String getPhoneNum() {

		return phoneNum;
	}

	/**
	 * Sets phone num.
	 *
	 * @param phoneNum the phone num
	 */
	public void setPhoneNum(String phoneNum) {

		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {

		return "Bank: "  + bankName + '\'' + ", address: '" + address + '\'' + ", phoneNum: '" + phoneNum + '\'';
	}
}
