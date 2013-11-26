package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.entities.Chronicle;
import de.medizinplattform.entities.Chronicles;

@ManagedBean(name="searchBean")
@ApplicationScoped
public class SearchBean {
	
	public SearchBean(){
		System.out.println("SearchBean started");
	}
	
	
	public List<Chronicle> findUsersChronicles(List<Chronicle> chronicles, String usersName){
		List<Chronicle> result = new ArrayList<Chronicle>();
			for(Chronicle chronicleInList : chronicles){
				if(usersName.equals(chronicleInList.getUsersName())){
					result.add(0, chronicleInList);
				}
			}
		return result;
		
	}
}
