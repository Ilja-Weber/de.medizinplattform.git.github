package de.medizinplattform.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;
import de.medizinplattform.entities.SymptomCollection;
import de.medizinplattform.utilitybeans.DateUtility;

@ManagedBean(name="symptomBean")
@RequestScoped
public class SymptomBean {
	// Injecting chronicleBean
	@ManagedProperty(value = "#{chronicleBean}")
	private ChronicleBean chronicle;
	public ChronicleBean getChronicle() {
		return chronicle;
	}
	public void setChronicle(ChronicleBean chronicle) {
		this.chronicle = chronicle;
	}
	
	// Constants - INNER
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	private ArrayList<String> symptoms;
		
	//Constructor
	public SymptomBean(){
		resetFields();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		
		List<SymptomCollection> allRows = null;		
		Query q = em.createQuery("SELECT x FROM SymptomCollection x");
		allRows = (List<SymptomCollection>) q.getResultList();
		
		symptoms = new ArrayList<String>();
		for(SymptomCollection row : allRows){
			symptoms.add(row.getSymptom());
		}
		
	}
	
	//Variable - OUTER
	private String term;
	public String getTerm(){
		return term;
	}
	public void setTerm(String term){
		this.term=term;
	}
	
	//Variable - OUTER
	private int intensity;
	public int getIntensity(){
		return intensity;
	}
	public void setIntensity(int intensity){
		this.intensity = intensity;
	}
	
	//Variable - OUTER
	private Date date;
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	
	//Logic INNER
	private void resetFields(){
		term = "";
		intensity=0;
	}
	
	//Logic
	public void deselectSymptom(){
		resetFields();
		chronicle.deselectEntry();
		chronicle.showOptions(); 
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("chronicle.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//Logic 
	public String save(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Symptom toBeSaved;
		if(chronicle.getSelectedEntry() != null){
			toBeSaved = em.merge((Symptom) chronicle.getSelectedEntry());
		}
		else{
			toBeSaved = new Symptom();
		}
		
		toBeSaved.setTerm(term);
		toBeSaved.setIntensity(intensity);
		toBeSaved.setBelongs_to_story(chronicle.getSelectedStory().getId());
		long day = DateUtility.calculateDay();
		toBeSaved.setDay(day);
		long month = DateUtility.calculateMonth();
		toBeSaved.setMonth(month);
		long year = DateUtility.calculateYear();
		toBeSaved.setYear(year);
		long hour = DateUtility.calculateHour();
		toBeSaved.setHour(hour);
		long minute = DateUtility.calculateMinute();
		toBeSaved.setMinute(minute);
		long second = DateUtility.calculateSecond();
		toBeSaved.setSecond(second);
		
		em.persist(toBeSaved);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		Story toBeUpdated = em.merge(chronicle.getSelectedStory());
		toBeUpdated.setT_day(day);
		toBeUpdated.setT_month(month);
		toBeUpdated.setT_year(year);
		toBeUpdated.setT_hour(hour);
		toBeUpdated.setT_minute(minute);
		toBeUpdated.setT_second(second);
		em.persist(toBeUpdated);
		em.getTransaction().commit();
		
		chronicle.showSelectedStory();
		return null;
	}
	
	 public List<String> complete(String query) {  
	    List<String> results = new ArrayList<String>();  
	        
	    for (String item : symptoms) {  
	    	if(item.contains(query)){
	    		results.add(item);
	    	}
	    } 	          
	    return results;  
	 } 
}
