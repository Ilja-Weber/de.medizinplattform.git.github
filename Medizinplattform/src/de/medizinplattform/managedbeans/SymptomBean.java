package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;
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
		
	//Constructor
	public SymptomBean(){
		resetFields();
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
	
	//Logic INNER
	private void resetFields(){
		term = "";
		intensity=0;
	}
	
	//Logic
	public String deselectSymptom(){
		resetFields();
		chronicle.deselectEntry();
		chronicle.showOptions();
		return null;
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
}
