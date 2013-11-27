package de.medizinplattform.entities;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.managedbeans.SearchBean;

@ManagedBean(name="chronicles")
@ApplicationScoped
public class Chronicles {
	
	//Injecting SearchBean
		@ManagedProperty(value="#{searchBean}")
		private SearchBean search;
		
		public SearchBean getSearch() {
			return search;
		}
		
		public void setSearch(SearchBean search) {
			this.search = search;
		}
		
		
	List<Chronicle> chroniclesList = new ArrayList<Chronicle>();
	
	public Chronicles(){
		chroniclesList.add(new Chronicle("123", "Ilja", "Very-very bad headache", "21.11.2013"));
		chroniclesList.add(new Chronicle("152", "Ilja", "Unhappyness", "22.11.2013"));
		chroniclesList.add(new Chronicle("112", "Pedro", "Pedros decease", "11.11.2012"));
		chroniclesList.add(new Chronicle("333", "Pedro", "Its getting worse ad worse", "10.01.1945"));
		chroniclesList.add(new Chronicle("132", "Pedro", "Death", "06.02.2079"));
	}
	
	public List<Chronicle> getUsersChronicles(String usersName){
		if(search != null){
			System.out.println("Chronicles: Hey take these chronicles from "+usersName);
			return search.findUsersChronicles(chroniclesList, usersName);
		}
		System.out.println("Chronicles: About to be null");
		return null;
	}
	
	public List<Chronicle> getChroniclesList() {
		return chroniclesList;
	}
	
	
	
	
}
