package javaProject.service;

import javaProject.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AccountService {

	private SessionFactory sessionFactory;
	private StandardServiceRegistry registry;
	private Session session;

	public AccountService() {
		System.out.println("Trying to connect to DB...");
		// configures settings from hibernate.cfg.xml
		registry = new StandardServiceRegistryBuilder().configure().build();

		// Establishing connection to DB
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Connection established");
	}

	public void addNewAccount(Account account) {
		account.setActive(true);
		session.save(account);
	}

	public void updateAccount(Account account) {
		session.saveOrUpdate(account);
	}

	public void deleteAccount(Account account) {
		session.remove(account);
	}

	public Account getAccountById(Long idAccount) {
		return session.get(Account.class, idAccount);
	}

	public List getAccountsByClientId(Long idClient) {
		return session.createQuery("FROM Account A WHERE A.clientId = " + idClient).list();
	}

	public void submitChange() {
		session.getTransaction().commit();
		session.close();
	}
}
