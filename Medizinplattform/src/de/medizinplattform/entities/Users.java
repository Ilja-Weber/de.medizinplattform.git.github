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
	

	
	
	@PreDestroy
	public void cry(){
		//System.out.println("UsersBean is about to be destroyed");
	}
}
