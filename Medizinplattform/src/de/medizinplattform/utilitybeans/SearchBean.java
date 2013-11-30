package de.medizinplattform.utilitybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;

@ManagedBean(name="searchBean")
@ApplicationScoped
public class SearchBean {
	
	public SearchBean(){
		System.out.println("SearchBean started");
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
	
	public EntriesContainerEditable getEntriesContainerById(List<EntriesContainerEditable> originalList, String id){
		EntriesContainerEditable result = null;
		
		for(EntriesContainerEditable entriesContainerEditable : originalList){
			if(id.equals(entriesContainerEditable.getId())){
				result=entriesContainerEditable;
			}
		}
		return result;
	}
	
}
