package de.medizinplattform.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.medizinplattform.containerbeans.UserEditable;

//das ist keine entity
@ManagedBean(name="users")
@ApplicationScoped
public class Users {
	
	List<UserEditable> users = new ArrayList<UserEditable>();
	
	
	public Users(){
		//User Ilja@1312
		users.add(new UserEditable(new User("Ilja", "1312", false)));
		users.add(new UserEditable(new User("Admin", "admin", true)));
		users.add(new UserEditable(new User("Pedro", "3234", false)));
					
		System.out.println("UsersBean started and initialized");
	}
	
	//Find a User in a Database
	public UserEditable findUserByName(String name){
		for(UserEditable user : users){
			if(user.getUser().getName().equals(name)){
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
		return findUserByName(name).getUser().getPassword();
	}
	
	//Get Users role
	public boolean isUserAdmin(String name){
		return findUserByName(name).getUser().isAdmin();
	}
	
	
	
	
	//Get all Users from Database
	public List<UserEditable> getUsersList(){
		return users;

	}
	
	//Create a User in Database
	public void createUser(String name, String password, boolean isAdmin){
		users.add(new UserEditable(new User(name, password, isAdmin)));
	}
	
	//Remove User from Database
	public void removeUser(UserEditable userEditable){
		users.remove(userEditable);
	
	}
	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
