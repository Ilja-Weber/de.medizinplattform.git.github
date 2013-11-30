package de.medizinplattform.managedbeans;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.medizinplattform.managers.UsersManager;


@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {

	
	@ManagedProperty(value="#{usersManager}")
	private UsersManager usersManager;
	
	public UsersManager getUsersManager() {
		return usersManager;
	}

	public void setUsersManager(UsersManager usersManager) {
		this.usersManager = usersManager;
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
		if(usersManager != null){
			
			System.out.println("Trying to create new user");
			if(usersManager.hasUser(name)==false){
				usersManager.createUser(name, password, false);
				return "succesful_registration.xhtml?faces-redirect=true";
			}
			else{
				System.out.println("User " + name + " already exists");
			}
			
		}
		return null;
	}
	
	@PreDestroy
	public void cry(){
		//System.out.println("RegisterBean is about to be destroyed");
	}
}
