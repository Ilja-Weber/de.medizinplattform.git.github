package de.medizinplattform.entities;

public class Entry {
	
	public String chronicleId;
	public String text;
	public boolean editable=false;
	
	
	public Entry(String chronicleId, String text){
		this.chronicleId=chronicleId;
		this.text=text;
	}
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getChronicleId() {
		return chronicleId;
	}
	public void setChronicleId(String chronicleId) {
		this.chronicleId = chronicleId;
	}
	
	@Override
	public String toString(){
		return chronicleId;
	}
}
