package javaProject.service;

import javaProject.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EmployeeService {

	private SessionFactory sessionFactory;
	private StandardServiceRegistry registry;
	private Session session;

	public EmployeeService() {
		System.out.println("Trying to connect to DB...");
		// configures settings from hibernate.cfg.xml
		registry = new StandardServiceRegistryBuilder().configure().build();

		// Establishing connection to DB
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Connection established");
	}

	public void addNewEmployee(Employee employee) {
		employee.setActive(true);
		session.save(employee);
	}

	public void updateEmployee(Employee employee) {
		session.update(employee);
	}

	public void deleteEmployee(Employee employee) {
		session.remove(employee);
	}

	public Employee getEmployeeById(Long idEmployee) {
		return session.get(Employee.class, idEmployee);
	}

	public void getEmployeeByName(String name) {
		session.createQuery("FROM Employee E WHERE E.firstName = " + name);
	}

	public void submitChange() {
		session.getTransaction().commit();
		session.close();
	}
}
