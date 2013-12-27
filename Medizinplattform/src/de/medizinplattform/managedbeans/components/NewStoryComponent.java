package de.medizinplattform.managedbeans.components;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;
import de.medizinplattform.managedbeans.SessionBean;

public class NewStoryComponent {
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	//Variable - INNER
	private ChronicleBean parent;
	
	//Constructor
	public NewStoryComponent(ChronicleBean cronicleBean){
		this.parent=cronicleBean;
		reset();
		newStoryButtonVisible=true;
	}
	
	//Logic - INNER
	private void reset(){
		newStoryButtonVisible=false;
		optionsVisible=false;
	}
	
	//Variable - OUTER
	private boolean newStoryButtonVisible;
	public boolean isNewStoryButtonVisible(){
		return newStoryButtonVisible;
	}
	
	//Logic - BUTTON
	public void cancelButton(){
		reset();
		newStoryButtonVisible=true;
	}
	
	//Variable - OUTER
	private boolean optionsVisible;
	public boolean isOptionsVisible(){
		return optionsVisible;
	}
	
	//Logic - BUTTON
	public void gotoOptions(){
		reset();
		optionsVisible=true;
	}
	
	//Variable - OUTER
	private boolean symptomVisible;
	public boolean isSymptomVisible(){
		return symptomVisible;
	}
		
	//Logic - COMMANDLINK
	public void gotoSymptom(){
		reset();
		symptomVisible=true;
	}
	
	//TODO Actions, Diagnosis - Ankets

}
