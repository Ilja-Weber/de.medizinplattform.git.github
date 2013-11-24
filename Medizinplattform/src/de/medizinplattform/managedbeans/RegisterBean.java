package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Users;


@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean {

	@ManagedProperty(value="#{users}")
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String name;
	public String password;
	
	public RegisterBean(){
		System.out.println("RegisterBean started");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String registerUser(){
		if(users != null){
			
			System.out.println("Try to create new user");
			if(users.hasUser(name)==false){
				users.createUser(name, password);
				return "succesful_registration.xhtml";
			}
			else{
				System.out.println("User " + name + " already exists");
			}
			
		}
		return null;
	}
}
