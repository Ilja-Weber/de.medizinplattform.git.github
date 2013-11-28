package de.medizinplattform.containerbeans;

public class EntryEditable {
	
	private String entriesContainerEditableId;
	private String entryText;
	private String entryDate;
	private String entryDay;
	private String entryMonth;
	private String entryYear;
	private boolean editable=false;
	
	public EntryEditable(String entriesContainerEditableId, String entryText, String entryDay, String entryMonth, String entryYear,boolean editable){
		this.entriesContainerEditableId = entriesContainerEditableId;
		this.entryText=entryText;
		this.entryDay=entryDay;
		this.entryMonth=entryMonth;
		this.entryYear=entryYear;
		this.editable=editable;
	}

	
	
	public String getEntriesContainerEditableId() {
		return entriesContainerEditableId;
	}


	public void setEntriesContainerEditableId(String entriesContainerEditableId) {
		this.entriesContainerEditableId = entriesContainerEditableId;
	}

	public String getEntryText() {
		return entryText;
	}

	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}

	public String getEntryDate() {
		return entryDay+"."+entryMonth+"."+entryYear;
	}

	public String getEntryDay() {
		return entryDay;
	}

	public void setEntryDay(String entryDay) {
		this.entryDay = entryDay;
	}

	public String getEntryMonth() {
		return entryMonth;
	}

	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}

	public String getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
	
	
}
