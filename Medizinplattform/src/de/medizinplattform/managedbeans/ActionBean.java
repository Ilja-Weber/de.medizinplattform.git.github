package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Action;
import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;
import de.medizinplattform.utilitybeans.DateUtility;

@ManagedBean(name="actionBean")
@RequestScoped
public class ActionBean {
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
	public ActionBean(){
		resetFields();
	}
	
	//Variable - OUTER
	private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	//Variable - OUTER
	private int period;
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	//Variable - OUTER
	private String amount;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	//Logic inner
	private void resetFields(){
		action="";
		period=1;
		amount="";
	}
	
	//Logic
	public String deselectAction(){
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
		Action toBeSaved;
		if(chronicle.getSelectedEntry() != null){
			toBeSaved = em.merge((Action) chronicle.getSelectedEntry());
		}
		else{
			toBeSaved = new Action();
		}
		
		toBeSaved.setAction(action);
		toBeSaved.setPeriod(period);
		toBeSaved.setAmount(amount);
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
