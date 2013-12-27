package de.medizinplattform.managedbeans;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.User;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {

	// Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";

	// Constructor
	public RegisterBean() {
		System.out.println("RegisterBean started");
	}

	// Variable - OUTER
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Variable - OUTER
	private String password = null;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Buttons logic
	public String registerUser() {
		if (name.length() > 0 && password.length() > 0) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();

			Query q = em.createQuery("SELECT x FROM User x WHERE x.name ='"
					+ name + "'");

			List<User> usersList = (List<User>) q.getResultList();

			if (usersList.size() > 1) {
				// Error: Should throw an Exception, because there cannot be
				// many users with same name
				System.out
						.println("Oooops! Too many Users with same name found!");
			} else if (usersList.size() == 1) {
				// One User found, cannot register another with same name
				System.out.println("Oooops! Such user already exists");
			} else {
				// No User found, now check if password is correct and if, then
				// set session data

				User user = new User();
				user.setName(name);
				user.setPassword(password);
				user.setRole("user");

				em.getTransaction().begin();
				em.persist(user);
				em.getTransaction().commit();

				return "registration-success.xhtml?faces-redirect=true";
			}

		}

		return null;
	}

	@PreDestroy
	public void cry() {
		// System.out.println("RegisterBean is about to be destroyed");
	}
}
