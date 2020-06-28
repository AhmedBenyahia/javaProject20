package javaProject.service;

import javaProject.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionService {

	private SessionFactory sessionFactory;
	private StandardServiceRegistry registry;
	private Session session;

	public TransactionService() {
		System.out.println("Trying to connect to DB...");
		// configures settings from hibernate.cfg.xml
		registry = new StandardServiceRegistryBuilder().configure().build();

		// Establishing connection to DB
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Connection established");
	}

	public void addNewTransaction(Transaction transaction) {
		session.save(transaction);
	}

	public void updateTransaction(Transaction transaction) {
		session.update(transaction);
	}

	public void deleteTransaction(Transaction transaction) {
		session.remove(transaction);
	}

	public void getTransactionById(String idTransaction) {
		session.get(Transaction.class, idTransaction);
	}

	public void submitChange() {
		session.getTransaction().commit();
		session.close();
	}

	public List getAllTransaction() {
		return  session.createQuery("from Transaction").list();
	}

	public List getAllEmployeeTransaction(String employeeId) {
		return  session.createQuery("from Transaction T where T.performedBy = " + employeeId).list();
	}

	public List getTransactionByPeriod(String periodStartDate, String periodEndDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		//convert String to LocalDate
		LocalDate periodStartDateLocal = LocalDate.parse(periodStartDate, formatter);
		LocalDate periodEndDateLocal = LocalDate.parse(periodEndDate, formatter);
		Query q = session.createQuery("from Transaction T where T.performedAt BETWEEN '" +
				periodStartDateLocal  + "' AND '" +  periodEndDateLocal + "'");
		System.out.println(q.getQueryString());
		return  q.list();
	}
}
