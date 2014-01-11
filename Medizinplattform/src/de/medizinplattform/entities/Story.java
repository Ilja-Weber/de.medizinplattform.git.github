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
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	private String story_teller;
	public String getStory_teller() {
		return story_teller;
	}
	public void setStory_teller(String story_teller) {
		this.story_teller = story_teller;
	}
	
	//From year
	private long f_y;
	public long getF_y() {
		return f_y;
	}
	public void setF_y(long f_y) {
		this.f_y = f_y;
	}

	//To year
	private long t_y;
	public long getT_y() {
		return t_y;
	}
	public void setT_y(long t_y) {
		this.t_y = t_y;
	}
	
	//From day
	private long f_d;
	public long getF_d() {
		return f_d;
	}
	public void setF_d(long f_d) {
		this.f_d = f_d;
	}
	
	//To day
	private long t_d;
	public long getT_d() {
		return t_d;
	}
	public void setT_d(long t_d) {
		this.t_d = t_d;
	}
	
	//From month
	private long f_m;
	public long getF_m() {
		return f_m;
	}
	public void setF_m(long f_m) {
		this.f_m = f_m;
	}
	
	//To month
	private long t_m;
	public long getT_m() {
		return t_m;
	}
	public void setT_m(long t_m) {
		this.t_m = t_m;
	}
	
	
	
	
	
	
}
