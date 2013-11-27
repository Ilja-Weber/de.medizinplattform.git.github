package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Chronicle;
import de.medizinplattform.entities.Chronicles;
import de.medizinplattform.entities.Entries;
import de.medizinplattform.entities.Entry;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	//Injecting Chronicles
	@ManagedProperty(value="#{chronicles}")
	private Chronicles chronicles;
	
	public Chronicles getChronicles() {
		return chronicles;
	}
	
	public void setChronicles(Chronicles chronicles) {
		this.chronicles = chronicles;
	}
	
	//Injecting Entries
		@ManagedProperty(value="#{entries}")
		private Entries entries;
		
		public Entries getEntries() {
			return entries;
		}
		
		public void setEntries(Entries entries) {
			this.entries = entries;
		}
		
	
	//Injecting UserBean
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean loggedUser;
	
	public SessionBean getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(SessionBean loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	List<Chronicle> chroniclesList = new ArrayList<Chronicle>();
	
	
	public ChronicleBean(){
		System.out.println("ChronicleBean started");
	}
	
	
	public List<Chronicle> createChroniclesList(){
		if(loggedUser!=null){
			return chronicles.getUsersChronicles(loggedUser.getCanSeeChronicleOf());
		}
		return null;
		
	}
	
	public String removeChronicle(Chronicle chronicle){
		return null;
	}
	
	
	
	
	
	
	
	
	public List<Entry> createEntriesList(Chronicle input){
		if(input==null){
			System.out.println("WTFWTFWTFWTFWTFWTF");
		}
			
		if(entries != null && input!=null){
			System.out.println("chronicleBean gets "+input+" and calls passes "+input.getId()+" to entries");
			return entries.getUserEntriesByChronicleId(input.getId());
		}
		return null;
	}
	
	
	public String editEntry(Entry entry){
		entry.setEditable(true);
		return null;
	}
	
	
	public String saveEntry(Entry entry){
		entry.setEditable(false);
		return null;
	}
	
	
	public String removeEntry(Entry entry){
		if(entries != null){
			entries.removeEntry(entry);
		}
		return null;
	}
	
}
