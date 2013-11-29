package de.medizinplattform.containerbeans;

import java.util.ArrayList;
import java.util.List;

public class EntriesContainerEditable {
	
	private String currentTitle;
	private String fromDate;
	private String toDate;
	
	//should actually come from the entity
	
	private String ownerName;
	private String id;
	
	//new entry form
	private boolean newEntryFormVisible = false;
	private String newEntryText;
	private String newEntryDay;
	private String newEntryMonth;
	private String newEntryYear;
	
	private List<EntryEditable> containerEntries = new ArrayList<EntryEditable>();
	
	public EntriesContainerEditable(String ownerName, String id, String currentTitle, String fromDate, String toDate){
		this.ownerName=ownerName;
		this.id=id;
		this.currentTitle=currentTitle;
		this.fromDate=fromDate;
		this.toDate=toDate;
	}
		
	public void updateState(){
		currentTitle=containerEntries.get(0).getEntryText();
		fromDate=containerEntries.get(containerEntries.size()-1).getEntryDate();
		toDate=containerEntries.get(0).getEntryDate();
	}

	public String getCurrentTitle() {
		return currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public boolean isNewEntryFormVisible() {
		return newEntryFormVisible;
	}

	
	public void setNewEntryFormVisible(boolean newEntryFormVisible) {
		this.newEntryFormVisible = newEntryFormVisible;
	}

	
	public String getNewEntryText() {
		return newEntryText;
	}

	
	public void setNewEntryText(String newEntryText) {
		this.newEntryText = newEntryText;
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
	
	
	public String addNewEntry(){
		newEntryFormVisible=true;
		return null;
	}
	public String saveNewEntry(){
		newEntryText=null;
		newEntryDay=null;
		newEntryMonth=null;
		newEntryYear=null;
		newEntryFormVisible=false;
		return null;
	}
	public String cancelNewEntry(){
		newEntryText=null;
		newEntryDay=null;
		newEntryMonth=null;
		newEntryYear=null;
		newEntryFormVisible=false;
		return null;
	}
}
