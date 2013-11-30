package de.medizinplattform.managers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;
import de.medizinplattform.db.FakeDatabase;
import de.medizinplattform.utilitybeans.SearchBean;

@ManagedBean(name="entriesManager")
@ApplicationScoped
public class EntriesManager {
	
	//Injecting SearchBean
	@ManagedProperty(value="#{searchBean}")
	private SearchBean search;			
	public SearchBean getSearch() {
		return search;
	}			
	public void setSearch(SearchBean search) {
		this.search = search;
	}
	//Injecting Database
	@ManagedProperty(value="#{dataBase}")
	private FakeDatabase dataBase;			
	public FakeDatabase getDataBase() {
		return dataBase;
	}			
	public void setDataBase(FakeDatabase dataBase) {
		this.dataBase = dataBase;
	}
	
	//Constructor
	public EntriesManager(){
		System.out.println("EntriesManager started");
	}
	
	/*
	public List<EntryEditable> getEntriesList(String entriesContainerEditableId) {
		return search.getEntryEditableByEntriesContainersId(dataBase.getEntriesTable(), entriesContainerEditableId);
	}
	*/
	public List<EntryEditable> getEntriesList(EntriesContainerEditable entriesContainerEditable){
		return search.getEntryEditableByEntriesContainersId(dataBase.getEntriesTable(), entriesContainerEditable.getId());
	}
	
	public void addNewEntry(EntryEditable entryEditable){
		dataBase.getEntriesTable().add(entryEditable);
	}
	public void removeEntry(EntryEditable entryEditable){
		dataBase.getEntriesTable().remove(entryEditable);
	}
	
				
}
