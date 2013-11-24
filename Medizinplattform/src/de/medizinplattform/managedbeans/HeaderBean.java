package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Users;

@ManagedBean(name="headerBean")
@SessionScoped
public class HeaderBean {
	
	public String name;
	public String password;
	public String header="header_with_forms.xhtml";

	@ManagedProperty(value="#{users}")
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public HeaderBean(){
		
		//debug
		System.out.println("Header Bean started");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		//debug
		System.out.println("Name was set to "+name);
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		//debug
		System.out.println("Password was set to "+password);
		this.password = password;
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public void login(){
		//debug
		if(users != null){
	    
			System.out.println("checking if user is registered");
			if(users.hasUser(name)==false){
				System.out.println("no such user exists");
			}
			else{
				if(password.equals(users.getPassword(name))){
					System.out.println("succesfull login");
					header="header_with_name.xhtml";
				}
				else{
					System.out.println("wrong password");
				}
			}
				
	    
		
		}       
	          
	}
	public void logout(){
		//debug
		System.out.println("header_with_forms mode set");
		header="header_with_forms.xhtml";
	}
	
}
