package de.medizinplattform.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
			stories = (List<StoryComponent>) q.getResultList();
		}
		return stories;
	}
	public String updateStory(StoryComponent sc){
		//TODO
		return null;
	}
	public String removeStory(StoryComponent sc){
		//TODO
		return null;
	}
}
