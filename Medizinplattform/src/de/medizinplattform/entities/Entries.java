package de.medizinplattform.entities;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.managedbeans.SearchBean;


@ManagedBean(name="entries")
@ApplicationScoped
public class Entries {
	
	//Injecting SearchBean
	@ManagedProperty(value="#{searchBean}")
	private SearchBean search;
	
	public SearchBean getSearch() {
		return search;
	}
	
	public void setSearch(SearchBean search) {
		this.search = search;
	}
	
	
	List<Entry> entriesList = new ArrayList<Entry>();
	
	
	public Entries(){
		System.out.println("Fill in Entries");
		
		//filling chronicle with id=123
		entriesList.add(new Entry("123", "Бился головой об стену"));
		entriesList.add(new Entry("123", "Сломал стену"));
		entriesList.add(new Entry("123", "Пришли щлые соседи с битой"));
		entriesList.add(new Entry("123", "Very-very bad headache"));
		
		//filling chronicle with id=152
		entriesList.add(new Entry("152", "Written some java code"));
		entriesList.add(new Entry("152", "Didnot compile"));
		entriesList.add(new Entry("152", "Errors everywhere"));
		entriesList.add(new Entry("152", "Unhappyness"));
		
		//filling chronicle with id=112
		entriesList.add(new Entry("112", "Eat some chili"));
		entriesList.add(new Entry("112", "Stomachache"));
		entriesList.add(new Entry("112", "Perdros decease"));
		
		//filling chronicle with id=333
		entriesList.add(new Entry("333", "Pedro is happy"));
		entriesList.add(new Entry("333", "Pedro ate a little girl"));
		entriesList.add(new Entry("333", "Pedro is unhappy"));
		entriesList.add(new Entry("333", "Pedro killed himself twice, jet still felt unhappy"));
		entriesList.add(new Entry("333", "Pedro has written some java code"));
		entriesList.add(new Entry("333", "Didnot compile"));
		entriesList.add(new Entry("333", "More error for the god of debug"));
		entriesList.add(new Entry("333", "Its geting worse ans worse"));
		
		//filling chronicle with id=132
		entriesList.add(new Entry("132", "Death"));
		
		System.out.println("Done!");
	}
	
	
	public List<Entry> getUserEntriesByChronicleId(String chronicle_id){
		if(search != null){
			System.out.println("Entries: I take "+chronicle_id+" and take an id " + chronicle_id);
			return search.findEntryByChronicleId(entriesList, chronicle_id);
		}
		System.out.println("Eintries: About to be null");
		return null;
	}
	

	public List<Entry> getEntriesList() {
		return entriesList;
	}
	
	public void removeEntry(Entry entry){
		entriesList.remove(entry);
	}

	
}
