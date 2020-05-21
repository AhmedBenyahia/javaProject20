package javaProject.service;

import javaProject.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ClientService {

	private SessionFactory sessionFactory;
	private StandardServiceRegistry registry;
	private Session session;

	public ClientService() {
		System.out.println("Trying to connect to DB...");
		// configures settings from hibernate.cfg.xml
		registry = new StandardServiceRegistryBuilder().configure().build();

		// Establishing connection to DB
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("Connection established");
	}

	public void addNewClient(Client client) {
		client.setActive(true);
		session.save(client);
	}

	public void updateClient(Client client) {
		session.update(client);
	}

	public void deleteClient(Client client) {
		session.remove(client);
	}

	public Client getClientById(Long idClient) {
		return session.get(Client.class, idClient);
	}

	public void getClientByName(String name) {
		session.createQuery("FROM Client E WHERE E.firstName = " + name);
	}

	public void submitChange() {
		session.getTransaction().commit();
		session.close();
	}

}
