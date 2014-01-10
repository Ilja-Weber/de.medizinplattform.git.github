package de.medizinplattform.managedbeans.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;

public class StoryComponent {

	//Variable - INNER
	private ChronicleBean parent;
	
	//Variable - INNER
	private Story story;
	
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	//Constructor
	public StoryComponent(ChronicleBean parent, Story story){
		this.parent=parent;
		this.story=story;
	}
	
	//Variable - OUTER
	private List<EntryComponent> entries;
	public List<EntryComponent> getEntries(){
		if(entriesListDoesntExist()){ createEntriesList();}
		return entries;
	}
	
	
	//Variabe - OUTER
	private String title;
	public String getTitle(){
		return title;
	}
	
	//Variable - OUTER
	private String state;
	public String getState(){
		return state;
	}
	
	//Logic
	public void addToList(EntryComponent entry){
		getEntries().add(0, entry);
	}
	
	//Inner Logic
	private boolean entriesListDoesntExist(){
		return (entries==null)?true:false;
	}
	
	//Inner Logic
	private void createEntriesList(){
		//TODO:Creation of list
	}
	
}
