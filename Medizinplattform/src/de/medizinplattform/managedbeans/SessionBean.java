package de.medizinplattform.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name= "Stranger";
	
	public SessionBean(){
		System.out.println("Session Bean started");
	}
	public String getName() {
		return this.toString();
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void doSomething(){
		System.out.println("Something done!");
	}
	
}
