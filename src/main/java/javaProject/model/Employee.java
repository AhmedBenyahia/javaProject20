package javaProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", allocationSize = 1)
	private long id;

	private String jobTitle;

	private double salary;

	private String employmentBankId;

	private String employmentBankName;

	/**
	 * Instantiates a new Employee.
	 *
	 * @param jobTitle the job title
	 * @param salary the salary
	 */
	public Employee(String jobTitle, double salary) {

		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	/**
	 * Instantiates a new Employee.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param jobTitle the job title
	 * @param salary the salary
	 */
	public Employee(String firstName, String lastName, String jobTitle, double salary) {

		super(firstName, lastName);
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	/**
	 * Instantiates a new Employee.
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
	 * @param jobTitle the job title
	 * @param salary the salary
	 */
	public Employee(String firstName, String lastName, String address, String age, String phoneNum,
			String gender, String cin, String email, boolean isActive, String postCode,
			String jobTitle, double salary) {

		super(firstName, lastName, address, age, phoneNum, gender, cin, email, isActive, postCode);
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	/**
	 * Instantiates a new Employee.
	 */
	public Employee() {
		// empty
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
	 * Gets job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {

		return jobTitle;
	}

	/**
	 * Sets job title.
	 *
	 * @param jobTitle the job title
	 */
	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;
	}

	/**
	 * Gets salary.
	 *
	 * @return the salary
	 */
	public double getSalary() {

		return salary;
	}

	/**
	 * Sets salary.
	 *
	 * @param salary the salary
	 */
	public void setSalary(double salary) {

		this.salary = salary;
	}

	/**
	 * Gets employment bank.
	 *
	 * @return the employment bank
	 */
	public String getEmploymentBankId() {

		return employmentBankId;
	}

	/**
	 * Sets employment bank.
	 *
	 * @param employmentBankId the employment bank
	 */
	public void setEmploymentBankId(String employmentBankId) {

		this.employmentBankId = employmentBankId;
	}

	/**
	 * Gets employment bank name.
	 *
	 * @return the employment bank name
	 */
	public String getEmploymentBankName() {

		return employmentBankName;
	}

	/**
	 * Sets employment bank name.
	 *
	 * @param employmentBankName the employment bank name
	 */
	public void setEmploymentBankName(String employmentBankName) {

		this.employmentBankName = employmentBankName;
	}

	@Override
	public String toString() {

		return "Employee:" + "id: " + id + ", jobTitle: '" + jobTitle + "', " + ", salary: " + salary
				+ ", employment bank Id: " + employmentBankId
				+ ", employment bank name: " + employmentBankName +  "', "  + super.toString();
	}
}
