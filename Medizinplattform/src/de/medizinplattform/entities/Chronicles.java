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
		chroniclesList.add(new Chronicle("Ilja", "Very-very bad headache"));
		chroniclesList.add(new Chronicle("Ilja", "Unhappyness"));
		chroniclesList.add(new Chronicle("Pedro", "Pedros decease"));
		chroniclesList.add(new Chronicle("Pedro", "Death"));
	}
	
	public List<Chronicle> getUsersChronicles(String usersName){
		if(search != null){
			return search.findUsersChronicles(chroniclesList, usersName);
		}
		return null;
	}

	public List<Chronicle> getChroniclesList() {
		return chroniclesList;
	}
}
