package de.medizinplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ENTITY")
public class Entry {
	
	
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
	private String belongsToStory;
	public String getBelongsToStory(){
		return belongsToStory;
	}
	public void setBelongsToStory(String newStoryId){
		this.belongsToStory=newStoryId;
	}
	
	//VARIABLE - OUTER
	private String type;
	public String getType(){
		return type;
	}
	public void setType(String newType){
		this.type=newType;
	}
	
	//VARIABLE - OUTER
	private String date; //Format dd.mm.yyyy
	public String getDate(){
		return date;
	}
	public void setDate(String newDate){
		this.date=newDate;
	}
	public void setDate(String dd, String mm, String yyyy){
		this.date=dd+"."+mm+"."+yyyy;
	}
	
	
	//VARIABLE - OUTER
	private String time; //Format hh:mm
	public String getTime(){
		return time;
	}
	public void setTime(String newTime){
		this.time=newTime;
	}
	public void setTime(String hh, String mm){
		this.time=hh+":"+mm;
	}
	
	
	//VARIABLE - OUTER
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String newContent){
		this.content=newContent;
	}
}
