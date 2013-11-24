package de.medizinplattform.entities;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

//das ist keine entity
@ManagedBean(name="users")
@ApplicationScoped
public class Users {
	
	HashMap<String, String> users = new HashMap<String, String>();
	
	public Users(){
		users.put("Ilja", "1312");
		users.put("admin", "admin");
		users.put("Gregory", "house");
		users.put("example@gg.com", "example");
		users.put("s", "s");
		System.out.println("Users Entity created and filld");
	}
	
	
	public boolean hasUser(String name){
		return users.containsKey(name);
	}
	
	public String getPassword(String name){
		return users.get(name);
	}
	
	
}
