package javaProject.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The type Personne.
 */
@MappedSuperclass
public abstract class Personne {

	private String firstName;

	private String lastName;

	private String address;

	private String age;

	private String phoneNum;

	private String gender;

	private String cin;

	private String email;

	private Boolean isActive;

	private String postCode;

	/**
	 * Instantiates a new Personne.
	 */
	public Personne() {

	}

	/**
	 * Instantiates a new Personne.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Personne(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Instantiates a new Personne.
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
	public Personne(String firstName, String lastName, String address, String age, String phoneNum,
			String gender, String cin, String email, boolean isActive, String postCode) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.cin = cin;
		this.email = email;
		this.isActive = isActive;
		this.postCode = postCode;
	}


	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {

		return firstName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * Sets last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {

		this.lastName = lastName;
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
	 * Gets age.
	 *
	 * @return the age
	 */
	public String getAge() {

		return age;
	}

	/**
	 * Sets age.
	 *
	 * @param age the age
	 */
	public void setAge(String age) {

		this.age = age;
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

	/**
	 * Gets gender.
	 *
	 * @return the gender
	 */
	public String getGender() {

		return gender;
	}

	/**
	 * Sets gender.
	 *
	 * @param gender the gender
	 */
	public void setGender(String gender) {

		this.gender = gender;
	}

	/**
	 * Gets cin.
	 *
	 * @return the cin
	 */
	public String getCin() {

		return cin;
	}

	/**
	 * Sets cin.
	 *
	 * @param cin the cin
	 */
	public void setCin(String cin) {

		this.cin = cin;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * Is active boolean.
	 *
	 * @return the boolean
	 */
	public Boolean isActive() {

		return isActive;
	}

	/**
	 * Sets active.
	 *
	 * @param active the active
	 */
	public void setActive(boolean active) {

		isActive = active;
	}

	/**
	 * Gets post code.
	 *
	 * @return the post code
	 */
	public String getPostCode() {

		return postCode;
	}

	/**
	 * Sets post code.
	 *
	 * @param postCode the post code
	 */
	public void setPostCode(String postCode) {

		this.postCode = postCode;
	}

	@Override
	public String toString() {

		return "firstName:'" + firstName + '\'' + ", lastName:'" + lastName + '\'' + ", address:'" + address + '\'' + ", age:'" + age + '\'' + ", phoneNum:'" + phoneNum + '\'' + ", gender:'" + gender + '\'' + ", cin:'" + cin + '\'' + ", email:'" + email + '\'' + ", isActive:" + isActive + ", postCode:'" + postCode;
	}
}
