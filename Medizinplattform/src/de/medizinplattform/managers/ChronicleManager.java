package de.medizinplattform.managers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.utilitybeans.SearchBean;

//Dieser Manager liefert eine Liste von ChronicleEntriesManager
//Au


@ManagedBean(name="chronicleManager")
@SessionScoped
public class ChronicleManager {
	//Injecting SearchBean
	@ManagedProperty(value="#{searchBean}")
	private SearchBean search;
			
	public SearchBean getSearch() {
		return search;
	}
			
	public void setSearch(SearchBean search) {
		this.search = search;
	}
			
	
	private EntriesContainersManager chronicleEntriesManager;
	
	public void getChronicleEntriesList(){
		
	}
}
