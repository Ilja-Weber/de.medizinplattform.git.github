package de.medizinplattform.managedbeans;

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
import de.medizinplattform.entities.User;
import de.medizinplattform.managedbeans.components.NewStoryComponent;
import de.medizinplattform.managedbeans.components.StoryComponent;
import de.medizinplattform.managedbeans.components.UserBeanComponent;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	//Constants - INNER
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	//Injecting sessionBean
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean session;	
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}
	
	//Constructor
	public ChronicleBean(){
		System.out.println("ChronicleBean started");
	}
	
		
	
	//Variable - OUTER
	private NewStoryComponent newStoryC;
	public NewStoryComponent getNewStoryComponent(){
		if(newStoryC==null){
			newStoryC=new NewStoryComponent(this);
		}
	return newStoryC;
	}
	
	//Variable - OUTER
	private List<StoryComponent> storiesC;
	public List<StoryComponent> getStoriesC(){
		if(storiesC==null){
			storiesC = new ArrayList<StoryComponent>();
			List<Story> storiesList = new ArrayList<Story>();
			
			//Get list of all users
			//get entitymanager
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			//Create a query
			Query q = em.createQuery("SELECT x FROM Story x");
			storiesList = (List<Story>) q.getResultList();
			
			//In for-loop create for each User a userComponent
			for(Story story : storiesList){
				storiesC.add(new StoryComponent(this, story));
			}
		}
		return storiesC;
	}
	
	
	//Variable - OUTER
	public boolean isNewStoryButtonVisible(){
		return getNewStoryComponent().isNewStoryButtonVisible();
		
	}
	
	
	//Variable - OUTER
	public boolean isOptionsVisible(){
		return getNewStoryComponent().isOptionsVisible();
	}
	
	//Variable - OUTER
	public boolean isSymptomVisible(){
		return getNewStoryComponent().isSymptomVisible();
	}
	
	//Buttons logic
	public String gotoOptions(){
		getNewStoryComponent().gotoOptions();
		return "new-story.xhtml?faces-redirect=true";
	}
	
	//Logic
	public String gotoSymptomAnket(){
		getNewStoryComponent().gotoSymptomAnket();
		return null;
	}
	
	//Buttons logic
	public String cancel(){
		getNewStoryComponent().cancel();
		return null;
	}

}
