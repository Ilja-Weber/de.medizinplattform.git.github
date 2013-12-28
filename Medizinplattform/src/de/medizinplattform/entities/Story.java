package de.medizinplattform.entities;

import java.sql.Date;

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
	
	//VARIABLE - OUTER
	private int createdAt;
	public int getCreatedAt(){
		return createdAt;
	}
	public void setCreatedAt(int createdAt){
		this.createdAt=createdAt;
	}
	
	
}
