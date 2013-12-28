package de.medizinplattform.managedbeans.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;

public class StoryComponent {

	//Variable - INNER
	private ChronicleBean parent;
	
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
		
	public StoryComponent(ChronicleBean parent, Story story){
		this.parent=parent;
		this.story=story;
	}
	
	//Variable - OUTER (Getter)
	public boolean isEmpty(){
		return (getEntries().size()>0)? false : true;
	}
	
	//Variable - OUTER
	private List<Entry> entries=new ArrayList<Entry>();
	public List<Entry> getEntries(){
		if(entries == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT x FROM Entry x WHERE x.id = "+story.getId()+"");
			entries = (ArrayList<Entry>)q.getResultList();
		}
		return entries;
	}
	
	//Variable - OUTER
	private Story story;
	public Story getStory(){
		return story;
	}
	
	//Logic
	public String create(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Entry toBeCreated = new Entry();
		toBeCreated.setBelongsToStory(story.getId());
		em.persist(toBeCreated);
		em.getTransaction().commit();
		entries.add(0, toBeCreated);		
		return null;
	}
	
	//Logic
	public String remove(Entry entry){
		if(entry!=null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Entry toBeRemoved = em.merge(entry);
			em.remove(toBeRemoved);
			em.getTransaction().commit();
			entries.remove(entry);		
			
		}
		return null;		
	}
}
