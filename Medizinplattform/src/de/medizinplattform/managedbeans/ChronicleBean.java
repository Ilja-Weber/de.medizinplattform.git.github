package de.medizinplattform.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.event.CloseEvent;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.utilitybeans.DateUtility;

@ManagedBean(name = "chronicleBean")
@SessionScoped
public class ChronicleBean {
	// Injecting sessionBean
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean session;
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}
	
	// Constants - INNER
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	
	//Variable - INNER
	private HashMap<String, Integer> month_days;

	// Constructor
	public ChronicleBean() {
		month_days = new HashMap<String, Integer>();
		month_days.put("alle", 31);
		month_days.put("Januar", 31);
		month_days.put("Februar", 28);
		month_days.put("März", 31);
		month_days.put("April", 30);
		month_days.put("Mai", 31);
		month_days.put("Juni", 30);
		month_days.put("Juli", 31);
		month_days.put("August", 31);
		month_days.put("September", 30);
		month_days.put("October", 31);
		month_days.put("November", 30);
		month_days.put("Dezember", 31);
		
		resetVisibility();
		listOfStoriesVisible=true;
	}
	
	//Variable - OUTER
	private List<String> years;
	public List<String> getYears(){
		if(years==null){
			years=new ArrayList<String>();
			int starting_year = (int) DateUtility.calculateYear();
			for(int i=0; i<13; i++){
				years.add(0,String.valueOf(starting_year-i));
			}
			years.add(0, "alle");
		}
		return years;
	}
	
	//Variable - OUTER
	private List<String> months;
	public List<String> getMonths(){
		if(months==null){
			months=new ArrayList<String>();
			months.add("alle");
			months.add("Januar");
			months.add("Februar");
			months.add("März");
			months.add("April");
			months.add("Mai");
			months.add("Juni");
			months.add("Juli");
			months.add("August");
			months.add("September");
			months.add("October");
			months.add("November");
			months.add("Dezember");
		}
		return months;
	}

	//Variable - OUTER
	private List<String> days;
	public List<String> getDays(){
		if(days==null){
			days=new ArrayList<String>();
			int limit=month_days.get(getMonths().get(selectedMonth));
			for(int i=1; i<=limit; i++){
				days.add(String.valueOf(i));
			}
			days.add(0, "alle");
		}
		return days;
	}
	
	//Variable - OUTER
	private int selectedYear=0;
	public String selectYear(int index){
		System.out.println("Year selected: "+index);
		selectedYear=index;
		return null;
	}
	
	//Variable - OUTER
	private int selectedMonth=0;
	public String selectMonth(int index){
		System.out.println("Month selected: "+index);
		selectedMonth=index;
		days=null;
		return null;
	}
	
	//Variable - OUTER
	private int selectedDay=0;
	public String selectDay(int index){
		System.out.println("Day selected: "+index);
		selectedDay=index;
		return null;
	}
	
	//Logic
	public void createStory(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Story toBeCreated = new Story();
			
			String title = "Diagnose unbekannt...";
			toBeCreated.setTitle(title);
			
			String state = "running";
			toBeCreated.setState(state);		
		
			String story_teller = session.getCanSeeChronicleOf();
			toBeCreated.setStory_teller(story_teller);
		
			long year = DateUtility.calculateYear();		
			toBeCreated.setF_year(year);
			toBeCreated.setT_year(year);
			selectYear(years.size()-1);
			
			long month = DateUtility.calculateMonth();		
			toBeCreated.setF_month(month);
			toBeCreated.setT_month(month);
			selectMonth((int) month);
			
			long day = DateUtility.calculateDay();		
			toBeCreated.setF_day(day);
			toBeCreated.setT_day(day);
			selectDay((int) day);
			
			long hour = DateUtility.calculateHour();		
			toBeCreated.setF_hour(hour);
			toBeCreated.setT_hour(hour);
			
			long minute = DateUtility.calculateMinute();		
			toBeCreated.setF_minute(minute);
			toBeCreated.setT_minute(minute);
			
			long second = DateUtility.calculateSecond();		
			toBeCreated.setF_second(second);
			toBeCreated.setT_second(second);
		
		em.persist(toBeCreated);
		em.getTransaction().commit();
		getAllStories().add(0, toBeCreated);
		select(toBeCreated);
		addOnStoryCreateMessage("Geschichte erstellt");
	}
	

	
	//Variable - OUTER
	private List<Story> allStories;
	public List<Story> getAllStories(){
		if(allStories==null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT x FROM Story x");
			allStories = (List<Story>) q.getResultList();
			Collections.sort(allStories);
		}
		return allStories;
	}
	
	//Logic
	public String renderDateFrom(Story story){
		long f_y=story.getF_year();
		long f_m=story.getF_month();
		long f_d=story.getF_day();
		long t_y=story.getT_year();
		long t_m=story.getT_month();
		long t_d=story.getT_day();		
		
		String result_last = numberToString(t_d)+ "." + numberToString(t_m) + "." + numberToString(t_y);
		
		String result_first = null;
		
		if(f_y == t_y){
			if(f_m == t_m){
				if(f_d == t_d){
					
				}
				else{
					result_first=numberToString(f_d)+".";
				}
			}
			else{
				result_first=numberToString(f_d)+"."+numberToString(f_m)+".";
			}
		}
		else{
			result_first=numberToString(f_d)+"."+numberToString(f_m)+"."+numberToString(f_y);
		}
		
		if(result_first == null){
			return result_last;
		}
		else{
			return result_first + " - " + result_last;
		}
	}

	//Logic
	public String renderDateFromSelectedStory(){
		return (selectedStory == null)? "No Date":renderDateFrom(selectedStory);
	}
	//Logic
	public String renderTitleFrom(Story story){
		String title = story.getTitle();
		return title;
	}
	//Logic
	public String renderTitleFromSelectedStory(){
		return (selectedStory == null)? "No Title":renderTitleFrom(selectedStory);
	}
	
	//Logic - INNER
	private String numberToString(long number){
		String string="";
		if(number<10){
			string="0";
		}
		string= string + String.valueOf(number);
		return string;
	}
	
	//Variable - OUTER
	private Story selectedStory;
	public Story getSelectedStory(){
		return (selectedStory!=null)?selectedStory:new Story();
	}
	public String select(Story story){
		selectedStory=story;
		showSelectedStory();
		System.out.println("Story "+story+" selected");
		return null;
	}
	public String deselectStory(){
		selectedStory=null;
		showListOfStories();
		System.out.println("Story deselected");
		return null;
	}
	
	//Variable - OUTER
	public boolean isStorySelected(){
		return (selectedStory!=null)?true:false;
	}
	
	//Logic - INNER
	private void resetVisibility(){
		listOfStoriesVisible=false;
		selectedStoryVisible=false;
		optionsVisible=false;
		diagnosisFormVisible=false;
		symptomFormVisible=false;
		actionFormVisible=false;
	}
	
	//Variable - OUTER
	private boolean actionFormVisible;
	public boolean isActionFormVisible(){
		return actionFormVisible;
	}
	public String showActionForm(){
		resetVisibility();
		actionFormVisible=true;
		return null;
	}
	
	//Variable - OUTER
	private boolean symptomFormVisible;
	public boolean isSymptomFormVisible(){
		return symptomFormVisible;
	}
	public String showSymptomForm(){
		resetVisibility();
		symptomFormVisible=true;
		return null;
	}
	
	//Variable - OUTER
	private boolean diagnosisFormVisible;
	public boolean isDiagnosisFormVisible(){
		return diagnosisFormVisible;
	}
	public String showDiagnosisForm(){
		resetVisibility();
		diagnosisFormVisible=true;
		return null;
	}
	
	//Variable - OUTER
	private boolean optionsVisible;
	public boolean isOptionsVisible(){
		return optionsVisible;
	}
	public void showOptions(){
		resetVisibility();
		optionsVisible=true;
	}
	
	//Variable - OUTER
	private boolean listOfStoriesVisible;
	public boolean isListOfStoriesVisible(){
		return listOfStoriesVisible;
	}
	public String showListOfStories(){
		resetVisibility();
		listOfStoriesVisible=true;
		return null;
	}
	
	//Variable - OUTER
	private boolean selectedStoryVisible;
	public boolean isSelectedStoryVisible(){
		return selectedStoryVisible;
	}
	public String showSelectedStory(){
		resetVisibility();
		selectedStoryVisible=true;
		return null;
	}
	
	//Variable - OUTER
	public List<Entry> getAllEntries(){
		List<Entry> allEntries = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT x FROM Entry x WHERE x.belongs_to_story = "+getSelectedStory().getId()+"");
		allEntries = (List<Entry>) q.getResultList();
		Collections.sort(allEntries);
		return allEntries;
	}
	
	//Variable - OUTER
	private Entry selectedEntry;
	public String select(Entry selection){
		selectedEntry=selection;
		return null;
	}
	public Entry getSelectedEntry(){
		return selectedEntry;
	}
	
	//Logic 
	public String deselectEntry(){
		selectedEntry=null;
		return null;
	}
	
	public void handleClose(CloseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");  
        deselectStory();
        addMessage(message);
        try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("chronicle.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    } 
	private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public void addOnStoryCreateMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
		
}
