package de.medizinplattform.managedbeans;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Action;
import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Symptom;

@ManagedBean(name="textBean")
@SessionScoped
public class TextBean {
	
	//Variable - INNER
	HashMap<Integer, String> intensities;
	HashMap<Integer, String> periods;
	//Constructor
	public TextBean(){
		intensities = new HashMap<Integer, String>();
		intensities.put(1, "hardly noticeable");
		intensities.put(2, "noticeable");
		intensities.put(3, "mediocre");
		intensities.put(4, "strong");
		intensities.put(5, "unbearable");
		
		periods = new HashMap<Integer, String>();
		periods.put(1, "once");
		periods.put(2, "daily");
		periods.put(3, "weekly");
		periods.put(4, "monthly");
	}
	
	//Logic 
	public String getAction(Entry entry){
		return ((Action)entry).getAction();
	}
	
	//Logic 
	public String getAmount(Entry entry){
		return ((Action)entry).getAmount();
	}
	
	//Logic 
	public String getPeriod(Entry entry){
		return periods.get(((Action)entry).getPeriod());
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
	public boolean isAction(Entry entry){
		return (entry.getClass().equals(Action.class))?true:false;
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
		return intensities.get(((Symptom)entry).getIntensity()) + " " +((Symptom)entry).getTerm();
	}
	
	//Logic
	public String getDate(Entry entry){
		return entry.getDay() + "-" + entry.getMonth() + "-"+entry.getYear();
	}
	
	//Logic
	public String getTime(Entry entry){
		return entry.getHour() + ":" +entry.getMinute();
	}
}
