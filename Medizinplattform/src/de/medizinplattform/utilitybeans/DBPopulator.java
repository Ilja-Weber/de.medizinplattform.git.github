package de.medizinplattform.utilitybeans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Story;

public class DBPopulator {
	
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		    EntityManager em = factory.createEntityManager();
		    // read the existing entries and write to console
		    
		    // create new todo
		    em.getTransaction().begin();
		    
		    Story story = new Story();
		    story.setStoryTeller("Sarah");
		    em.persist(story);
		    
		    System.out.println("Id: "+story.getId());
		    em.getTransaction().commit();
		    System.out.println("Id: "+story.getId());
		    em.close();
		  }
}


