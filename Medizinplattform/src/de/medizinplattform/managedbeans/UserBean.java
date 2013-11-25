package de.medizinplattform.managedbeans;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
	public String name;
	public String password;
	public String header="header_guest.xhtml";
	
	public UserBean(){
		System.out.println("UserBean started");
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
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public void setRole(String role){
		if(role.equals("user")){
			header="header_user.xhtml";
		}
		else if(role.equals("admin")){
			header="header_admin.xhtml";
		}
		else{
			header="header_guest.xhtml";
		}
	}
	
	@PreDestroy
	public void cry(){
		//System.out.println("UserBean is about to be destroyed");
	}
}
