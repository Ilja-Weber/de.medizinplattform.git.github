package de.medizinplattform.managedbeans.components;

import java.util.LinkedHashMap;
import java.util.Map;

import de.medizinplattform.managedbeans.ChronicleBean;

public class NewStoryComponent {
	
	
	
	//Constructor
	public NewStoryComponent(ChronicleBean cronicleBean){
		this.parent=cronicleBean;
		this.formVisible=false;
	}
	
	//Variable - INNER
	private ChronicleBean parent;
	
	
	//Variable - OUTER
	private String text = "Hello";
	public String getText(){
		return text;
	}
	public void setText(String newText){
		this.text=newText;
	}
	
	
	//Variable - OUTER
	private String fromDate = "12.11.2013";
	public String getFromDate(){
		return fromDate;
	}
	public void setFromDate(String newFromDate){
		this.fromDate=newFromDate;
	}
	
	
	//Variable - OUTER
	private String toDate = "16.11.2013";
	public String getToDate(){
		return toDate;
	}
	public void setToDate(String newToDate){
			this.toDate=newToDate;
	}
	
	
	//Variable - OUTER
	private boolean formVisible;
	public boolean isFormVisible(){
		return formVisible;
	}
	public void setFormVisible(boolean formVisible){
		this.formVisible=true;
	}
	
	
	//Variable - OUTER
	private String day;
	public String getDay(){
		return day;
	}
	public void setDay(String newDay){
		this.day=newDay;
		System.out.println("Day set to "+newDay);
	}
	
	
	//Variable - OUTER
	private Map<String,Object> possibleDays;
	public Map<String, Object> getPossibleDays(){
		
		if(possibleDays==null){
			
			possibleDays=new LinkedHashMap<String, Object>();
			
			String temp=null;
			for(int i=1 ; i<32 ; i++){
				
				if(i<10){
					temp="0";
				}
				else{
					temp="";
				}
				temp=temp+String.valueOf(i);
				
				possibleDays.put(temp, temp);
			}
		}
		return possibleDays;
	
	}
	
	
	//Variable - OUTER
	private String month;
	public String getMonth(){
		return month;
	}
	public void setMonth(String newMonth){
		this.month=newMonth;
	}
	
	
	//Variable - OUTER
	private Map<String,Object> possibleMonths;
	public Map<String, Object> getPossibleMonths(){
			
		if(possibleMonths==null){
			
			possibleMonths=new LinkedHashMap<String, Object>();
				
			possibleMonths.put("January", "January");
			possibleMonths.put("February", "February");
			possibleMonths.put("March", "March");
			possibleMonths.put("April", "April");
			possibleMonths.put("May", "May");
			possibleMonths.put("June", "June");
			possibleMonths.put("July", "July");
			possibleMonths.put("August", "August");
			possibleMonths.put("September", "September");
			possibleMonths.put("October", "October");
			possibleMonths.put("November", "Novermber");
			possibleMonths.put("December", "December");
			
		}
		return possibleMonths;
	
	}
	
	
	//Variable - OUTER
	private String year;
	public String getYear(){
		return year;
	}
	public void setYear(String newYear){
		this.year=newYear;
	}
	
	
	//Variable - OUTER
	private Map<String,Object> possibleYears;
	public Map<String, Object> getPossibleYears(){
			
		if(possibleYears==null){
				
			possibleYears=new LinkedHashMap<String, Object>();
					
			possibleYears.put("2014", "2014");
			possibleYears.put("2013", "2013");
			possibleYears.put("2012", "2012");
			possibleYears.put("2011", "2011");
			possibleYears.put("<2011", "<2011");
				
		}
		return possibleYears;
		
	}
	
	
	//Buttons logic
	public String showFormButton(){
		this.formVisible=true;
		return null;
	}
	
	//Buttons logic
	public String startButton(){

		formVisible=false;
		return null;
	}
	
	
	//Buttons logic
	public String cancelButton(){
		text="";
		day="";
		month="";
		year="";
		formVisible=false;
		System.out.println("cancel all changes");
		return null;
	}
}
