package de.medizinplattform.utilitybeans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Entry;
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
		    
		    Entry entry = new Entry();
		    entry.setBelongsToStory(story.getId());
		    entry.setDate("21.11.2014");
		    entry.setTime("11:24");
		    entry.setContent("Hallo haloo");
		    em.persist(entry);
		    
		    em.getTransaction().commit();
		    
		    System.out.println("Id: "+story.getId());
		    em.close();
		  }
}


