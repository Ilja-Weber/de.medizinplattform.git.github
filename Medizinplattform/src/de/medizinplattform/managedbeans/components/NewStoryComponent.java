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
		this.optionsVisible=false;
		this.newStoryButtonVisible=true;
	}
	
	
	
	
	
	//Variable - OUTER
	private boolean newStoryButtonVisible;
	public boolean isNewStoryButtonVisible(){
		return newStoryButtonVisible;
	}
	public void hideNewStoryButton(){
		newStoryButtonVisible=false;
	}
	
	//Variable - OUTER
	private boolean optionsVisible;
	public boolean isOptionsVisible(){
		return optionsVisible;
	}
	public void hideOptions(){
		optionsVisible=false;
	}
	
	//Buttons logic	
	public void gotoOptions(){
		optionsVisible=true;
		newStoryButtonVisible=false;
	}
	
	
}
