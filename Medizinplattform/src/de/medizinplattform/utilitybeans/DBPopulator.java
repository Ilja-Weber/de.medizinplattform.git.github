package de.medizinplattform.utilitybeans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.User;

public class DBPopulator {
	
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		    EntityManager em = factory.createEntityManager();
		    // read the existing entries and write to console
		    
		    // create new todo
		    em.getTransaction().begin();
		    
		    User user = new User();
		    user.setName("Ilja");
		    user.setPassword("1312");
		    user.setRole("admin");
		    em.persist(user);
		    
		    em.getTransaction().commit();

		    em.close();
		  }
}


