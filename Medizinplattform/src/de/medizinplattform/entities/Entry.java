package de.medizinplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Entry {
	
	//Variable
	@Id @GeneratedValue
    protected long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//Variable
	protected long year;
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}

	//Variable
	protected long month;
	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	//Variable
	protected long day;
	public long getDay() {
		return day;
	}
	public void setDay(long day) {
		this.day = day;
	}
	
	//Variable
	protected long hour;
	public long getHour() {
		return hour;
	}
	public void setHour(long hour) {
		this.hour = hour;
	}
	
	//Variable
	protected long minute;
	public long getMinute() {
		return minute;
	}
	public void setMinute(long minute) {
		this.minute = minute;
	}
	
	//Variable
	protected long second;
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	
	//Variable
	protected long belongs_to_story;
	public long getBelongs_to_story() {
		return belongs_to_story;
	}
	public void setBelongs_to_story(long belongs_to_story) {
		this.belongs_to_story = belongs_to_story;
	}
	
}
