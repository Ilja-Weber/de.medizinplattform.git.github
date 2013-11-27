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
		users.add(new UserEditable(new User("Ilja", "1312", "user")));
		users.add(new UserEditable(new User("Admin", "admin", "admin")));
		users.add(new UserEditable(new User("Pedro", "3234", "user")));
					
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
	public String getUsersRole(String name){
		return findUserByName(name).getUser().getRole();
	}
	
	
	
	
	//Get all Users from Database
	public List<UserEditable> getUsersList(){
		return users;

	}
	
	//Create a User in Database
	public void createUser(String name, String password, String role){
		users.add(new UserEditable(new User(name, password, role)));
		System.out.println("User "+ name + "@" + password+" created");
	}
	
	//Remove User from Database
	public void removeUser(UserEditable tableUser){
		System.out.println("Remove table user");
		users.remove(tableUser);
	
	}
	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
