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
	
	
}
