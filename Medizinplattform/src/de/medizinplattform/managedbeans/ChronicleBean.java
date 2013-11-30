package de.medizinplattform.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;
import de.medizinplattform.managers.EntriesContainersManager;
import de.medizinplattform.managers.EntriesManager;
import de.medizinplattform.utilitybeans.IdGeneratorBean;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	
	//Injecting IdGenerator
	@ManagedProperty(value="#{idGenerator}")
	private IdGeneratorBean idGenerator;			
	public IdGeneratorBean getIdGenerator() {
		return idGenerator;
	}			
	public void setIdGenerator(IdGeneratorBean idGenerator) {
		this.idGenerator = idGenerator;
	}
	
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
	
	//Actions
	//create new entrycontainer
	public String addNewEntriesContainerButton(){
		newEntriesContainerFormVisible=true;
		return null;
	}
	public String saveNewEntriesContainerButton(){
		newEntriesContainerFormVisible=false;
		
		
		if((newEntryText!=null) && (newEntryDay!=null) && (newEntryMonth!=null) && (newEntryYear!=null)){
			
			String newEntryContainerId=idGenerator.getUniqueEntrieContainerId();
			
			entriesContainersManager.addNewEntriesContainer(
					new EntriesContainerEditable(
							loggedUser.getCanSeeChronicleOf(),
							newEntryContainerId,
							newEntryText,
							(newEntryDay+"."+newEntryMonth+"."+newEntryYear),
							(newEntryDay+"."+newEntryMonth+"."+newEntryYear)));
			
			entriesManager.addNewEntry(
					new EntryEditable(
							newEntryContainerId, 
							newEntryText, 
							newEntryDay,
							newEntryMonth, 
							newEntryYear,
                            false));
			
			newEntriesContainerFormVisible=false;
			newEntryText=null;
			newEntryDate=null;
			newEntryDay=null;
			newEntryMonth=null;
			newEntryYear=null;
		}
		return null;
	}
	public String cancelNewEntriesContainerButton(){
		newEntriesContainerFormVisible=false;
		newEntryText=null;
		newEntryDate=null;
		newEntryDay=null;
		newEntryMonth=null;
		newEntryYear=null;
		return null;
	}
	public String deleteEntriesContainerButton(EntriesContainerEditable entriesContainerEditable){
		entriesContainersManager.deleteEntriesContainer(entriesContainerEditable);
		return null;
	}
	
	//create new entry
	public String addNewEntryButton(EntriesContainerEditable entriesContainerEditable){
		entriesContainerEditable.setNewEntryFormVisible(true);
		return null;
	}
	public String saveNewEntryButton(EntriesContainerEditable entriesContainerEditable){
		
		if((entriesContainerEditable.getNewEntryText()!=null) &&
				(entriesContainerEditable.getNewEntryDay()!=null) &&
				(entriesContainerEditable.getNewEntryMonth()!=null) &&
				(entriesContainerEditable.getNewEntryYear()!=null)){
			entriesManager.addNewEntry(
					new EntryEditable(
							entriesContainerEditable.getId(), 
							entriesContainerEditable.getNewEntryText(), 
							entriesContainerEditable.getNewEntryDay(), 
							entriesContainerEditable.getNewEntryMonth(), 
							entriesContainerEditable.getNewEntryYear(),
							false));
			entriesContainerEditable.setNewEntryText(null);
			entriesContainerEditable.setNewEntryDay(null);
			entriesContainerEditable.setNewEntryMonth(null);
			entriesContainerEditable.setNewEntryYear(null);
			entriesContainerEditable.setNewEntryFormVisible(false);
		}
		entriesContainersManager.updateEntriesContainersState(entriesContainerEditable);
		return null;
	}
	public String cancelNewEntryButton(EntriesContainerEditable entriesContainerEditable){
		entriesContainerEditable.setNewEntryText(null);
		entriesContainerEditable.setNewEntryDay(null);
		entriesContainerEditable.setNewEntryMonth(null);
		entriesContainerEditable.setNewEntryYear(null);
		entriesContainerEditable.setNewEntryFormVisible(false);
		return null;
	}
	
	//edit existing entry
	public String editEntryButton(EntryEditable entryEditable){
		entryEditable.setEditable(true);
		return null;
	}
	public String saveEntryButton(EntryEditable entryEditable){
		entryEditable.setEditable(false);
		entriesContainersManager.updateEntriesContainersState(
				entriesContainersManager.findEntriesContainerEditableById(entryEditable.getEntriesContainerEditableId())
				);
		return null;
	}
	public String removeEntryButton(EntryEditable entryEditable){
		EntriesContainerEditable ece = entriesContainersManager.findEntriesContainerEditableById(entryEditable.getEntriesContainerEditableId());
		entriesManager.removeEntry(entryEditable);
		entriesContainersManager.updateEntriesContainersState(ece);
		return null;
	}
	
	//Getters and Setters
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
