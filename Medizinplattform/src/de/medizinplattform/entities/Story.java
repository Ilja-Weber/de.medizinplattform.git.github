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
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	//VARIABLE - OUTER
	private long created_at;
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	
	//VARIABLE - OUTER
	private String story_teller;
	public String getStory_teller() {
		return story_teller;
	}
	public void setStory_teller(String story_teller) {
		this.story_teller = story_teller;
	}

	
}
