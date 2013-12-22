package de.medizinplattform.managedbeans.components;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;
import de.medizinplattform.managedbeans.SessionBean;

public class NewStoryComponent {
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	
	//Constructor
	public NewStoryComponent(ChronicleBean cronicleBean){
		this.parent=cronicleBean;
		this.formVisible=false;
	}
	
	//Variable - INNER
	private ChronicleBean parent;
	
	
	//Variable - OUTER
	private String type;
	public String getType(){
		return type;
	}
	public void setType(String newType){
		this.type=newType;
	}
	
	//Variable - OUTER
	private Map<String,Object> types;
	public Map<String, Object> getTypes(){
		
		if(types==null){
			
			types=new LinkedHashMap<String, Object>();
			types.put("symptome", "symptome");
			types.put("diagnosis", "diagnosis");
			types.put("medicine", "medicine");
			types.put("action", "action");
			types.put("recomendation", "recomendation");
		}
		return types;
	
	}
	
	
	//Variable - OUTER
	private String date;
	public String getDate(){
		return date;
	}
	public void setDate(String newDate){
		this.date=newDate;
	}
	
	
	//Variable - OUTER
	private String time;
	public String getTime(){
		return time;
	}
	public void setTime(String newTime){
		this.time=newTime;
	}
	
	//Variable - OUTER
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String newContent){
		this.content=newContent;
	}
	
	
	//Variable - OUTER
	private boolean formVisible;
	public boolean isFormVisible(){
		return formVisible;
	}
	public void setFormVisible(boolean formVisible){
		this.formVisible=true;
	}
	
	//Variable - OUTER
	private boolean newStoryButtonVisible;
	public boolean isNewStoryButtonVisible(){
		return newStoryButtonVisible;
	}
	public void hideNewStoryButton(){
		newStoryButtonVisible=false;
	}
	
	//Buttons logic
	public String showFormButton(){
		this.formVisible=true;
		return null;
	}
	
	//Buttons logic
	public String startButton(){
		if(!type.equals("") && !date.equals("") && !time.equals("")&& !content.equals("")){
			//Obtain em
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
		    
		    Story story = new Story();
		    story.setStoryTeller(parent.getSession().getCanSeeChronicleOf());
		    em.persist(story);
		    
		    Entry entry = new Entry();
		    entry.setBelongsToStory(story.getId());
		    entry.setType(type);
		    entry.setDate(date);
		    entry.setTime(time);
		    entry.setContent(content);
		    em.persist(entry);
		    
		    em.getTransaction().commit();
		    em.close();
				
				
				
			cancelButton();
			
			
		}
		return null;
	}
	
	
	//Buttons logic
	public String cancelButton(){
		type="";
		date="";
		time="";
		content="";
		formVisible=false;
		System.out.println("cancel all changes");
		return null;
	}
}
