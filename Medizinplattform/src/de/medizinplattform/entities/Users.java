package de.medizinplattform.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

//das ist keine entity
@ManagedBean(name="users")
@ApplicationScoped
public class Users {
	
	List<TableUser> users = new ArrayList<TableUser>();
	
	User temp_user = new User();	
	
	public Users(){
		//User Ilja@1312
		users.add(new TableUser(new User("Ilja", "1312", "user")));
		users.add(new TableUser(new User("Admin", "admin", "admin")));
					
		System.out.println("UsersBean started and initialized");
	}
	
	public TableUser findUserByName(String name){
		for(TableUser user : users){
			if(user.user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}
	
	public User getTemp_user(){
		return temp_user;
	}
	
	public void setTemp_user(User temp_user){
		this.temp_user=temp_user;
	}
	
	public boolean hasUser(String name){
		return (findUserByName(name)!=null)?true:false;
	}
	
	public String getPassword(String name){
		return findUserByName(name).user.getPassword();
	}
	
	public String getRole(String name){
		return findUserByName(name).user.getRole();
	}
	
	public void createUser(String name, String password){
		users.add(new TableUser(new User(name, password, "user")));
		
		System.out.println("User "+ name + "@" + password+" created");
	}
	
	public String createUser(){
		
		users.add(new TableUser(new User(temp_user.getName(), temp_user.getPassword(),  temp_user.getRole())));
		
		System.out.println("User "+ temp_user.getName() + "@" + temp_user.getPassword()+" created");
		
		temp_user.name="";
		temp_user.password="";
		temp_user.role="";
		
		return null;
	}
	
	public List<TableUser> getUsers(){
		return users;

	}
	
	public String editUser(TableUser tu){
		System.out.println("Edit table user");
		tu.setCanEdit(true);
		return null;
	}
	
	public String removeUser(TableUser tu){
		System.out.println("Remove table user");
		users.remove(tu);
		return null;
	}
	
	public String saveUsers(){
		
		for(TableUser tu : getUsers()){
			tu.setCanEdit(false);
		}
		return null;
	}
	
	public String saveUser(TableUser tu){
		System.out.println("Save table user");
		tu.setCanEdit(false);
		return null;
	}
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
