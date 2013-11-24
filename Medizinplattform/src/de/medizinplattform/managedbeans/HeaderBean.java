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
		System.out.println("header_with_name mode set");
		header="header_with_name.xhtml";
	}
	public void logout(){
		//debug
		System.out.println("header_with_forms mode set");
		header="header_with_forms.xhtml";
	}

}
