package de.medizinplattform.managers;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.containerbeans.UserEditable;
import de.medizinplattform.db.FakeDatabase;
import de.medizinplattform.entities.User;

@ManagedBean(name="usersManager")
@ApplicationScoped
public class UsersManager {
	//Injecting Database
	@ManagedProperty(value="#{dataBase}")
	private FakeDatabase dataBase;			
	public FakeDatabase getDataBase() {
		return dataBase;
	}			
	public void setDataBase(FakeDatabase dataBase) {
		this.dataBase = dataBase;
	}
	
	
	
	//Find a User in a Database
	public UserEditable findUserByName(String name){
		for(UserEditable user : dataBase.getUsersTable()){
			if(user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}
	
	//Check if a Username exists in the Database	
	public boolean hasUser(String name){
		return (findUserByName(name)!=null)?true:false;
	}
	
	//Get Users password
	public String getUsersPassword(String name){
		return findUserByName(name).getPassword();
	}
	
	//Get Users role
	public boolean isUserAdmin(String name){
		return findUserByName(name).isAdmin();
	}	
	
	//Get all Users from Database
	public List<UserEditable> getUsersList(){
		return dataBase.getUsersTable();

	}
	
	//Create a User in Database
	public void createUser(String name, String password, boolean isAdmin){
		dataBase.getUsersTable().add(new UserEditable(new User(name, password, isAdmin)));
	}
	
	//Remove User from Database
	public void removeUser(UserEditable userEditable){
		dataBase.getUsersTable().remove(userEditable);
	
	}
}
