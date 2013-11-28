package de.medizinplattform.utilitybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;
import de.medizinplattform.entities.Chronicle;
import de.medizinplattform.entities.Entry;

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
		System.out.println("UserChronicles "+result);
		return result;
		
	}
	
	public List<Entry> findEntryByChronicleId(List<Entry> entries, String chronicle_id){
		List<Entry> result = new ArrayList<Entry>();
			for(Entry entryInList : entries){
				if(chronicle_id.equals(entryInList.getChronicleId())){
					result.add(0, entryInList);
					
				}
			}
		System.out.println("Search: I return "+result);
		return result;
		
	}
	
	public List<EntriesContainerEditable> getEntriesContainerByOwnerName(List<EntriesContainerEditable> originalList, String ownerName){
		List<EntriesContainerEditable> subList = new ArrayList<EntriesContainerEditable>();
		
		for(EntriesContainerEditable entriesContainerEditable : originalList){
			if(ownerName.equals(entriesContainerEditable.getOwnerName())){
				subList.add(0, entriesContainerEditable);
			}
		}
		return subList;
	}
	
	public List<EntryEditable> getEntryEditableByEntriesContainersId(List<EntryEditable> originalList, String entriesContainerId){
		List<EntryEditable> subList = new ArrayList<EntryEditable>();
		
		for(EntryEditable entryEditable : originalList){
			if(entriesContainerId.equals(entryEditable.getEntriesContainerEditableId())){
				subList.add(0, entryEditable);
			}
		}
		return subList;	
	}
	
	
}
