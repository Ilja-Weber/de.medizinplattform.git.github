package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.utilitybeans.DateUtility;

@ManagedBean(name="diagnosisBean")
@RequestScoped
public class DiagnosisBean {
	// Injecting sessionBean
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
	public DiagnosisBean(){
		resetFields();
	}
	
	//Variable - OUTER
	private String diagnosisText;
	public String getDiagnosisText() {
		return diagnosisText;
	}
	public void setDiagnosisText(String diagnosisText) {
		this.diagnosisText = diagnosisText;
		System.out.println("Hi");
	}
	
	//Variable - OUTER
	private String locationOfProblem;
	public String getLocationOfProblem() {
		return locationOfProblem;
	}
	public void setLocationOfProblem(String locationOfProblem) {
		this.locationOfProblem = locationOfProblem;
	}
	
	//Variable - OUTER
	private String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//Variable - OUTER
	private String where;
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}

	//Variable - OUTER
	private Entry selectedDiagnosis;
	public String select(Entry diagnosis){
		selectedDiagnosis=diagnosis;
		return null;
	}
	public String deselectDiagnosis(){
		selectedDiagnosis=null;
		resetFields();
		chronicle.showOptions();
		return null;
	}
	
	//Logic - INNER
	private void resetFields(){
		diagnosisText="";
		locationOfProblem="";
		author="";
		where="";
	}
	
	//Logic 
	public String save(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Diagnosis toBeSaved;
		if(chronicle.getSelectedEntry() != null){
			toBeSaved = em.merge((Diagnosis) chronicle.getSelectedEntry());
		}
		else{
			toBeSaved = new Diagnosis();
		}
		
		toBeSaved.setAuthor(author);
		toBeSaved.setDiag(diagnosisText);
		toBeSaved.setLoc(locationOfProblem);
		toBeSaved.setBelongs_to_story(chronicle.getSelectedStory().getId());
		toBeSaved.setWhere_was_it_done(where);
		long day = DateUtility.calculateDay();
		toBeSaved.setDay(day);
		long month = DateUtility.calculateMonth();
		toBeSaved.setMonth(month);
		long year = DateUtility.calculateYear();
		toBeSaved.setYear(year);
		
		em.persist(toBeSaved);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Story toBeUpdated = em.merge(chronicle.getSelectedStory());
		toBeUpdated.setTitle(diagnosisText);
		chronicle.getSelectedStory().setTitle(diagnosisText);
		toBeUpdated.setT_d(day);
		toBeUpdated.setT_m(month);
		toBeUpdated.setT_y(year);		
		em.persist(toBeUpdated);
		em.getTransaction().commit();
		
		chronicle.showSelectedStory();
		return null;
	}

}
