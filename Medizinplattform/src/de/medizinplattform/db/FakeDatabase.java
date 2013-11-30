package de.medizinplattform.db;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.containerbeans.EntryEditable;
import de.medizinplattform.containerbeans.UserEditable;
import de.medizinplattform.entities.User;

@ManagedBean(name="dataBase")
@ApplicationScoped
public class FakeDatabase {
	
	//Fake tables
	public List<EntriesContainerEditable> entriesContainerTable = new ArrayList<EntriesContainerEditable>();
	public List<EntryEditable> entriesTable = new ArrayList<EntryEditable>();
	public List<UserEditable> usersTable = new ArrayList<UserEditable>();
	
	public FakeDatabase(){
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "1", "Kopfschmerzen", "22.12.2012", "25.12.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "2", "Grippe", "11.02.2006", "13.05.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "3", "Husten", "07.01.2000", "08.02.2002"));
		entriesContainerTable.add(new EntriesContainerEditable("Pedro", "4", "Magenhusten", "03.08.2002", "25.12.2012"));
		entriesContainerTable.add(new EntriesContainerEditable("Ilja", "5", "Hohoho", "beginn", "end"));
		
		entriesTable.add(new EntryEditable("1", "Kopfschmerzen", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("1", "Many years of pain", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("2", "Blagablaga", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("3", "asdfafa", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("4", "Oh mah gad i dead", "22", "11", "2013", false));
		entriesTable.add(new EntryEditable("5", "Ilja was and were everywhere", "22", "11", "2013", false));
		
		usersTable.add(new UserEditable(new User("Ilja", "1312", false)));
		usersTable.add(new UserEditable(new User("Admin", "admin", true)));
		usersTable.add(new UserEditable(new User("Pedro", "3234", false)));
	}

	public List<EntriesContainerEditable> getEntriesContainerTable() {
		return entriesContainerTable;
	}

	public List<EntryEditable> getEntriesTable() {
		return entriesTable;
	}
	
	public List<UserEditable> getUsersTable(){
		return usersTable;
	}
	
	
	
}
