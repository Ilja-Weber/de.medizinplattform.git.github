package de.medizinplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STORY")
public class Story {
	
	//VARIABLE - OUTER
	@Id @GeneratedValue
	private long id;
	public long getId(){
		return id;
	}
	public void setId(long newId){
		this.id=newId;
	}
	
	
	//VARIABLE - OUTER
	private String storyTeller;
	public String getStoryTeller(){
		return storyTeller;
	}
	public void setStoryTeller(String newStoryTeller){
		this.storyTeller=newStoryTeller;
	}
	
	
}
