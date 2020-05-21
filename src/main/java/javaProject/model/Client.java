package javaProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The type Client.
 */
@Entity
@Table(name = "client")
public class Client extends Personne {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", allocationSize = 1)
	private long id;

	/** The type of client: enterprise, association, individual */
	private String clientType;

	private String secretCode;

	/**
	 * Instantiates a new Client.
	 */
	public Client() {
		super();
	}

	/**
	 * Instantiates a new Client.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Client(String firstName, String lastName) {

		super(firstName, lastName);
	}

	/**
	 * Instantiates a new Client.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param address the address
	 * @param age the age
	 * @param phoneNum the phone num
	 * @param gender the gender
	 * @param cin the cin
	 * @param email the email
	 * @param isActive the is active
	 * @param postCode the post code
	 */
	public Client(String firstName, String lastName, String address, String age, String phoneNum,
			String gender, String cin, String email, boolean isActive, String postCode) {

		super(firstName, lastName, address, age, phoneNum, gender, cin, email, isActive, postCode);
	}

	/**
	 * Gets client type.
	 *
	 * @return the client type
	 */
	public String getClientType() {

		return clientType;
	}

	/**
	 * Sets client type.
	 *
	 * @param clientType the client type
	 */
	public void setClientType(String clientType) {

		this.clientType = clientType;
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
	 * Gets secret code.
	 *
	 * @return the secret code
	 */
	public String getSecretCode() {

		return secretCode;
	}

	/**
	 * Sets secret code.
	 *
	 * @param secretCode the secret code
	 */
	public void setSecretCode(String secretCode) {

		this.secretCode = secretCode;
	}

	@Override
	public String toString() {

		return "Client:" + "id: " + id + ", clientType: '" + clientType + "', " + super.toString();
	}
}
