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
	
	HashMap<String, User> users = new HashMap<String, User>();
	
	
	public Users(){
		//User Ilja@1312
		users.put("Ilja", new User());
		users.get("Ilja").setName("Ilja");
		users.get("Ilja").setPassword("1312");
		users.get("Ilja").setRole("user");
		
		//User Ilja@1312
		users.put("Admin", new User());
		users.get("Admin").setName("Admin");
		users.get("Admin").setPassword("admin");
		users.get("Admin").setRole("admin");
		
			
		System.out.println("UsersBean started and initialized");
	}
	
	
	public boolean hasUser(String name){
		return users.containsKey(name);
	}
	
	public String getPassword(String name){
		return users.get(name).getPassword();
	}
	
	public String getRole(String name){
		return users.get(name).getRole();
	}
	
	public void createUser(String name, String password){
		users.put(name, new User());
		users.get(name).setName(name);
		users.get(name).setPassword(password);
		users.get(name).setRole("user");
		
		System.out.println("User "+ name + "@" + password+" created");
	}
	
	public List<User> getUsers(){
		System.out.println("Trying to build Users List");
		List<User> user_list = new ArrayList<User>();
		Set<String> keys = new HashSet<String>();
		System.out.println("Get all keys from hash");
		keys = users.keySet();
		
		System.out.println("Iterate");
		String temp;
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			System.out.println("Get next key");
			temp = it.next();
			
			
			System.out.println("We got "+temp);
			
			System.out.println("Now add "+temp+" to the users_list");
			user_list.add(users.get(temp));
		}
		System.out.println("Return the list");
		return user_list;

	}
	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
