package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Entry;

@ManagedBean(name="textBean")
@SessionScoped
public class TextBean {
	//Logic
	public boolean isDiagnosis(Entry entry){
		return (entry.getClass().equals(Diagnosis.class))?true:false;
	}
	
	//Logic
	public String getAuthor(Entry entry){
		return ((Diagnosis)entry).getAuthor();
	}
	
	//Logic
	public String getDiagnosis(Entry entry){
		return ((Diagnosis)entry).getDiag();
	}
	
	//Logic
	public String test(){
		return "test";
	}
}
