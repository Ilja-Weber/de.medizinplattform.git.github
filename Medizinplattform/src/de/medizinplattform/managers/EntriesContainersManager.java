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

@ManagedBean(name="entriesContainersManager")
@ApplicationScoped
public class EntriesContainersManager {
	
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
			
			
	
	
	public EntriesContainersManager(){
		System.out.println("EntriesContainerManager started");
		
	}
	
	public List<EntriesContainerEditable> getEntriesContainersList(String ownerName){
		return search.getEntriesContainerByOwnerName(dataBase.getEntriesContainerTable(), ownerName);
	}
	
	public void addNewEntriesContainer(EntriesContainerEditable entriesContainerEditable){
		dataBase.getEntriesContainerTable().add(entriesContainerEditable);
	}
	
	public void deleteEntriesContainer(EntriesContainerEditable entriesContainerEditable){
		dataBase.getEntriesContainerTable().remove(entriesContainerEditable);
	}
	
	public void updateEntriesContainersState(EntriesContainerEditable entriesContainerEditable){
		List<EntryEditable> containerEntries = search.getEntryEditableByEntriesContainersId(dataBase.getEntriesTable(), entriesContainerEditable.getId());
		entriesContainerEditable.setCurrentTitle(containerEntries.get(0).getEntryText());
		entriesContainerEditable.setFromDate(containerEntries.get(containerEntries.size()-1).getEntryDate());
		entriesContainerEditable.setToDate(containerEntries.get(0).getEntryDate());
	}
	
	public EntriesContainerEditable findEntriesContainerEditableById(String id){
		return search.getEntriesContainerById(dataBase.getEntriesContainerTable(), id);
	}
}
