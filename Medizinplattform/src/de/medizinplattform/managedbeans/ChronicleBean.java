package de.medizinplattform.managedbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.components.EntryComponent;
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
	}
	
	//Variable - OUTER
	private List<StoryComponent> stories;
	public List<StoryComponent> getStories(){
		if(storiesListDoesNotExist()){
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

	//Logic
	public String createStory(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Story toBeCreated = new Story();
		
		String story_teller = session.getCanSeeChronicleOf();
		toBeCreated.setStory_teller(story_teller);
		
		long date = calculateDate();		
		toBeCreated.setCreated_at(date);
		
		String state = "running";
		toBeCreated.setState(state);
		
		em.persist(toBeCreated);
		em.getTransaction().commit();
		stories.add(0, new StoryComponent(this, toBeCreated));		
		return null;
	}
	
	//Logic
	public String createEntryIn(StoryComponent story){
		EntryComponent entry = new EntryComponent(story);
		story.addToList(entry);
		return null;
	}
	
	
	//Inner Logic
	private boolean storiesListDoesNotExist(){
		return (stories == null)?true:false;
	}
	
	//Inner Logic
	private long calculateDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = new Date();
		String date_as_string = dateFormat.format(date);
		long date_as_long = Long.parseLong(date_as_string);
		return date_as_long;
	}
		
}
