package de.medizinplattform.db;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;

@ManagedBean(name="dataBase")
@ApplicationScoped
public class FakeDatabase {
	
	//Fake tables
	public List<EntriesContainerEditable> entriesContainerTable = new ArrayList<EntriesContainerEditable>();
	public List<EntryEditable> entriesTable = new ArrayList<EntryEditable>();
	
	public FakeDatabase(){
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "1", "Kopfschmerzen", "22.12.2012", "25.12.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "2", "Grippe", "11.02.2006", "13.05.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "3", "Husten", "07.01.2000", "08.02.2002"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "4", "Magenhusten", "03.08.2002", "25.12.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Ilja", "5", "Geiler Kerl", "beginn", "end"));
		
		entriesTable.add(new EntryEditable("1", "Kopfschmerzen", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("1", "Oh mah gad i dead", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("2", "Oh mah gad i dead", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("3", "Oh mah gad i dead", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("4", "Oh mah gad i dead", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("5", "Oh mah gad i dead", "22", "11", "2013", false));
	}

	public List<EntriesContainerEditable> getEntriesContainerTable() {
		return entriesContainerTable;
	}

	public List<EntryEditable> getEntriesTable() {
		return entriesTable;
	}
	
	
}
