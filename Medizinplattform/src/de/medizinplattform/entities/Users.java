package de.medizinplattform.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

//das ist keine entity
@ManagedBean(name="users")
@ApplicationScoped
public class Users {
	
	List<TableUser> users = new ArrayList<TableUser>();
	
	
	public Users(){
		//User Ilja@1312
		users.add(new TableUser(new User("Ilja", "1312", "user")));
		users.add(new TableUser(new User("Admin", "admin", "admin")));
		users.add(new TableUser(new User("Pedro", "3234", "user")));
					
		System.out.println("UsersBean started and initialized");
	}
	
	//Find a User in a Database
	public TableUser findUserByName(String name){
		for(TableUser user : users){
			if(user.user.getName().equals(name)){
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
		return findUserByName(name).user.getPassword();
	}
	
	//Get Users role
	public String getUsersRole(String name){
		return findUserByName(name).user.getRole();
	}
	
	
	
	
	//Get all Users from Database
	public List<TableUser> getUsers(){
		return users;

	}
	
	//Create a User in Database
	public void createUser(String name, String password, String role){
		users.add(new TableUser(new User(name, password, role)));
		System.out.println("User "+ name + "@" + password+" created");
	}
	
	//Remove User from Database
	public void removeUser(TableUser tableUser){
		System.out.println("Remove table user");
		users.remove(tableUser);
	
	}
	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
