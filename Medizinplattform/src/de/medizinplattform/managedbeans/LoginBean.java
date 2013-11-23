package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class LoginBean {

	private String name = "Enter your name here";
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name=name;
		//session.setName(name);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	
	
}
