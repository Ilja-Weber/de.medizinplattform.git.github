package de.medizinplattform.entities;

public class User{
	
	public String name;
	public String password;
	public String role;
	
	public User(String name, String password, String role){
		this.name=name;
		this.password=password;
		this.role=role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
