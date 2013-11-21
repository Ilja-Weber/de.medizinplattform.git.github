package de.medizinplattform;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.testdb;

public class test {
	
	private static final String PERSISTENCE_UNIT_NAME = "testdb";
	private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		    EntityManager em = factory.createEntityManager();
		    // read the existing entries and write to console
		    Query q = em.createQuery("select t from Todo t");
		    List<testdb> todoList = q.getResultList();
		    for (testdb todo : todoList) {
		      System.out.println(todo);
		    }
		    System.out.println("Size: " + todoList.size());

		    // create new todo
		    em.getTransaction().begin();
		    testdb todo = new testdb();
		    todo.setSummary("This is a test");
		    todo.setDescription("This is a test");
		    em.persist(todo);
		    em.getTransaction().commit();

		    em.close();
		  }
}

