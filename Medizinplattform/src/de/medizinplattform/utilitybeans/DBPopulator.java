package de.medizinplattform.utilitybeans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.SymptomCollection;

public class DBPopulator {
	
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	public static void main(String[] args) {
		List<Entry> allEntries;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
	
		em.getTransaction().begin();
		SymptomCollection sc = new SymptomCollection();
		sc.setSymptom("Schwäche");
		em.persist(sc);
		em.getTransaction().commit();
	}
}


