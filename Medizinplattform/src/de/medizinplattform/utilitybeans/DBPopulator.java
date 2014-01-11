package de.medizinplattform.utilitybeans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;

public class DBPopulator {
	
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	public static void main(String[] args) {
		List<Entry> allEntries;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT x FROM Entry x WHERE x.belongs_to_story = "+251L+"");
		allEntries = (List<Entry>) q.getResultList();
		if(allEntries == null){
			System.out.println("Its null");
		}
		else{
			System.out.println("Found "+allEntries.get(0));
		}
		 }
}


