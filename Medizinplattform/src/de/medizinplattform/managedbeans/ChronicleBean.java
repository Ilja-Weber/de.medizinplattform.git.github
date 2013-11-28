package de.medizinplattform.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;
import de.medizinplattform.managers.EntriesContainersManager;
import de.medizinplattform.managers.EntriesManager;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	
	//Injecting sessionBean for retrieving logged user information
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean loggedUser;
	
	public SessionBean getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(SessionBean loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	//Injecting entriesContainersManager
	@ManagedProperty(value="#{entriesContainersManager}")
	private EntriesContainersManager entriesContainersManager;		
	public EntriesContainersManager getEntriesContainersManager() {
		return entriesContainersManager;
	}
	public void setEntriesContainersManager(EntriesContainersManager entriesContainersManager) {
		this.entriesContainersManager = entriesContainersManager;
	}
	
	//Injecting entriesManager 
	@ManagedProperty(value="#{entriesManager}")
	private EntriesManager entriesManager;		
	public EntriesManager getEntriesManager() {
		return entriesManager;
	}
	public void setEntriesManager(EntriesManager entriesManager) {
		this.entriesManager = entriesManager;
	}
	
	//Constructor with debug info
	public ChronicleBean(){
		System.out.println("ChronicleBean started");
	}
	
	private boolean newEntriesContainerFormVisible = false;
	private String newEntryText;
	private String newEntryDate;
	private String newEntryDay;
	private String newEntryMonth;
	private String newEntryYear;
	
	

	public boolean getNewEntriesContainerFormVisible(){
		return newEntriesContainerFormVisible;
	}
	public String newButton(){
		newEntriesContainerFormVisible=true;
		return null;
	}
	public String saveButton(){
		newEntriesContainerFormVisible=false;
		return null;
	}
	public String cancelButton(){
		newEntriesContainerFormVisible=false;
		newEntryText=null;
		newEntryDate=null;
		newEntryDay=null;
		newEntryMonth=null;
		newEntryYear=null;
		return null;
	}
	public String getNewEntryText() {
		return newEntryText;
	}

	public void setNewEntryText(String newEntryText) {
		this.newEntryText = newEntryText;
	}

	public String getNewEntryDate() {
		return newEntryDate;
	}

	public void setNewEntryDate(String newEntryDate) {
		this.newEntryDate = newEntryDate;
	}
	public String getNewEntryDay() {
		return newEntryDay;
	}

	public void setNewEntryDay(String newEntryDay) {
		this.newEntryDay = newEntryDay;
	}

	public String getNewEntryMonth() {
		return newEntryMonth;
	}

	public void setNewEntryMonth(String newEntryMonth) {
		this.newEntryMonth = newEntryMonth;
	}

	public String getNewEntryYear() {
		return newEntryYear;
	}

	public void setNewEntryYear(String newEntryYear) {
		this.newEntryYear = newEntryYear;
	}
	
	public List<EntriesContainerEditable> getEntriesContainers(){
		return entriesContainersManager.getEntriesContainersList(loggedUser.getCanSeeChronicleOf());
	}
	public List<EntryEditable> getEntries(EntriesContainerEditable entriesContainerEditable){
		if(entriesContainerEditable!=null){
			return entriesManager.getEntriesList(entriesContainerEditable);
		}
		return null;
		
	}
}
