package de.medizinplattform.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	@ManagedProperty(value = "#{sessionBean}") 
	private SessionBean session;
	
	
	public SessionBean getSessionBean(){
		return session;
	}

	public void setSessionBean(SessionBean session)	{
		this.session = session;
	}
	

	private String name = "Enter your name here";
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		System.out.println("Name was set to "+name);
		this.name=name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		System.out.println("Password was set");
		this.password = password;
	}
	
	public String check(){
		System.out.println("Session bean is: "+session);
		if(session!=null){
			session.setName(name);
		}
		return "login";
	}
	
}
