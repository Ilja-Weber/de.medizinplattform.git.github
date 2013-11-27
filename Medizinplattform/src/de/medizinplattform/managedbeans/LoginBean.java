package de.medizinplattform.managedbeans;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.medizinplattform.entities.Users;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
	public String name;
	public String password;
	
	
	@ManagedProperty(value="#{users}")
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
	@ManagedProperty(value="#{userBean}")
	private UserBean user;
	
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	public LoginBean(){
		
		//debug
		System.out.println("Login Bean started");
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
	
	
	public String login(){
		//debug
		if(users != null){
	    
			System.out.println("checking if user " + name+ " is registered");
			if(users.hasUser(name)==false){
				System.out.println("no such user exists");
			}
			else{
				if(password.equals(users.getUsersPassword(name))){
					System.out.println("succesfull login");
					user.setRole(users.getUsersRole(name));
					System.out.println("Session role set to " + users.getUsersRole(name));
					user.setName(name);
					return "index.xhtml?faces-redirect=true";
				}
				else{
					return "login_fail.xhtml?faces-redirect=true";
				}
			}
				
	    
		
		}       
		return null;    
	}
	public String logout(){
		//debug
		System.out.println("header_with_forms mode set");
		user.setRole("guest");
		System.out.println("Session role set to guest");
		user.setName(null);
		return "index.xhtml?faces-redirect=true";
	}
	
	@PreDestroy
	public void cry(){
		//System.out.println("LoginBean is about to be destroyed");
	}
	
}
