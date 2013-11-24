package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="headerBean")
@SessionScoped
public class HeaderBean {
	
	public String name;
	public String password;
	public String header="header_with_forms.xhtml";

	public HeaderBean(){
		
		//debug
		System.out.println("Header Bean started");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("Name was set to "+name);
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("Password was set to "+password);
		this.password = password;
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public void check(){
		System.out.println("check called");
		header="header_with_name.xhtml";
		System.out.println("New header mode set");
	}

}
