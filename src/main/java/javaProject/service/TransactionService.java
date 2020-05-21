package javaProject.service;

import javaProject.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
}
