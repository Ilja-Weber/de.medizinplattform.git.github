package de.medizinplattform.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Map<String, Integer> intensities = new HashMap<String, Integer>(); 
	public Map<String, Integer> getIntensities() {  
        return intensities;  
    }  
		
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
		
		intensities.put("kaum merkbare", 1);
		intensities.put("merkbare", 2);
		intensities.put("mittelmäßige", 3);
		intensities.put("starke", 4);
		intensities.put("unerträgliche", 5);
		
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
	public void save(){
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
		long day = DateUtility.calculateDay(date);
		toBeSaved.setDay(day);
		long month = DateUtility.calculateMonth(date);
		toBeSaved.setMonth(month);
		long year = DateUtility.calculateYear(date);
		toBeSaved.setYear(year);
		long hour = DateUtility.calculateHour(date);
		toBeSaved.setHour(hour);
		long minute = DateUtility.calculateMinute(date);
		toBeSaved.setMinute(minute);
		long second = DateUtility.calculateSecond(date);
		toBeSaved.setSecond(second);
		
		em.persist(toBeSaved);
		em.getTransaction().commit();
		
		String update = "none"; //from, to
		
		em.getTransaction().begin();
		
		Story toBeUpdated = em.merge(chronicle.getSelectedStory());
		
		if(toBeUpdated.getF_year() > year){
			update = "from";
		}
		else if(toBeUpdated.getT_year() < year){
			update = "to";
		}
		else{
			if(toBeUpdated.getF_month() > month){
				update = "from";
			}
			else if(toBeUpdated.getT_month() < month){
				update = "to";
			}
			else{
				if(toBeUpdated.getF_day() > day){
					update = "from";
				}
				else if(toBeUpdated.getT_day() < day){
					update = "to";
				}
				else{
					if(toBeUpdated.getF_hour() > hour){
						update = "from";
					}
					else if(toBeUpdated.getT_hour() < hour){
						update = "to";
					}
					else{
						if(toBeUpdated.getF_minute() > minute){
							update = "from";
						}
						else if(toBeUpdated.getT_minute() < minute){
							update = "to";
						}
						else{
							
						}
					}
				}
			}
		}
		
		if(update.equals("from")){
			toBeUpdated.setF_year(year);
			toBeUpdated.setF_month(month);
			toBeUpdated.setF_day(day);
			toBeUpdated.setF_hour(hour);
			toBeUpdated.setF_minute(minute);
			
			chronicle.getSelectedStory().setF_year(year);
			chronicle.getSelectedStory().setF_month(month);
			chronicle.getSelectedStory().setF_day(day);
			chronicle.getSelectedStory().setF_hour(hour);
			chronicle.getSelectedStory().setF_minute(minute);
			
			
		}
		
		if(update.equals("to")){
			toBeUpdated.setT_year(year);
			toBeUpdated.setT_month(month);
			toBeUpdated.setT_day(day);
			toBeUpdated.setT_hour(hour);
			toBeUpdated.setT_minute(minute);
			
			chronicle.getSelectedStory().setT_year(year);
			chronicle.getSelectedStory().setT_month(month);
			chronicle.getSelectedStory().setT_day(day);
			chronicle.getSelectedStory().setT_hour(hour);
			chronicle.getSelectedStory().setT_minute(minute);
		}
		
		em.persist(toBeUpdated);
		em.getTransaction().commit();
		
		chronicle.showSelectedStory();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("chronicle.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 
	 public void intensityChange() {  
	     return;  
	 }
}
