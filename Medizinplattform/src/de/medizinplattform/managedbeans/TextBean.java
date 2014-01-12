package de.medizinplattform.managedbeans;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Symptom;

@ManagedBean(name="textBean")
@SessionScoped
public class TextBean {
	
	//Variable - INNER
	HashMap<Integer, String> vocabulary;
	//Constructor
	public TextBean(){
		vocabulary = new HashMap<Integer, String>();
		vocabulary.put(1, "hardly noticeable");
		vocabulary.put(2, "noticeable");
		vocabulary.put(3, "mediocre");
		vocabulary.put(4, "strong");
		vocabulary.put(5, "unbearable");
	}
	
	//Logic
	public boolean isDiagnosis(Entry entry){
		return (entry.getClass().equals(Diagnosis.class))?true:false;
	}
	
	//Logic
	public boolean isSymptom(Entry entry){
		return (entry.getClass().equals(Symptom.class))?true:false;
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
	
	//Logic
	public String getSymptomText(Entry entry){
		return vocabulary.get(((Symptom)entry).getIntensity()) + " " +((Symptom)entry).getTerm();
	}
	
	//Logic
	public String getDate(Entry entry){
		return entry.getYear() + "-" + entry.getMonth() + "-"+entry.getDay();
	}
	
	//Logic
	public String getTime(Entry entry){
		return entry.getHour() + ":" +entry.getMinute();
	}
}
