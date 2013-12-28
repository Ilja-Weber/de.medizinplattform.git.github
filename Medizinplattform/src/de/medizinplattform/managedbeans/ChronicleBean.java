package de.medizinplattform.managedbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.components.StoryComponent;

@ManagedBean(name = "chronicleBean")
@SessionScoped
public class ChronicleBean {
	// Constants - INNER
	private final String PERSISTENCE_UNIT_NAME = "common-entities";

	// Injecting sessionBean
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}

	// Constructor
	public ChronicleBean() {
		System.out.println("ChronicleBean started");
	}

	//Variable - OUTER
	public boolean isEmpty(){
		return (getStories().size()>0)? false : true;
	}
	
	//Variable - OUTER
	private List<StoryComponent> stories;
	public List<StoryComponent> getStories(){
		if(stories == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT x FROM Story x");
			List<Story> storyEntities = (List<Story>) q.getResultList();
			
			stories=new ArrayList<StoryComponent>();
			for(Story story : storyEntities){
				stories.add(new StoryComponent(this, story));
			}
		}
		
		return stories;
	}
	public String updateStory(StoryComponent sc){
		//TODO
		return null;
	}
	public String delete(StoryComponent sc){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Story toBeRemoved = em.merge(sc.getStory());
		em.remove(toBeRemoved);
		em.getTransaction().commit();
		stories.remove(sc);		
		return null;
	}
	public String create(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Story toBeCreated = new Story();
		toBeCreated.setStoryTeller(session.getCanSeeChronicleOf());
		toBeCreated.setCreatedAt(20131228);
		em.persist(toBeCreated);
		em.getTransaction().commit();
		stories.add(0, new StoryComponent(this, toBeCreated));		
		return null;
	}
		
}
