package de.medizinplattform.entities;

import java.util.HashMap;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

//das ist keine entity
@ManagedBean(name="users")
@ApplicationScoped
public class Users {
	
	HashMap<String, String> user_infos = new HashMap<String, String>();
	HashMap<String, HashMap<String, String>> users = new HashMap<String, HashMap<String, String>>();
	
	
	public Users(){
		//User Ilja@1312
		users.put("Ilja", new HashMap<String, String>());
		users.get("Ilja").put("password", "1312");
		users.get("Ilja").put("role", "user");
		
		//User admin@admin
		users.put("admin", new HashMap<String, String>());
		users.get("admin").put("password", "admin");
		users.get("admin").put("role", "admin");
		
		System.out.println("UsersBean started and initialized");
	}
	
	
	public boolean hasUser(String name){
		return users.containsKey(name);
	}
	
	public String getPassword(String name){
		return users.get(name).get("password");
	}
	
	public String getRole(String name){
		return users.get(name).get("role");
	}
	
	public void createUser(String name, String password){
		users.put(name, new HashMap<String, String>());
		users.get(name).put("password", password);
		users.get(name).put("role", "user");
		
		System.out.println("User "+ name + "@" + password+" created");
	}
	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
