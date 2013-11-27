package de.medizinplattform.entities;

public class User{
	
	public String name;
	public String password;
	public boolean admin;
	
	public User(String name, String password, boolean admin){
		this.name=name;
		this.password=password;
		this.admin=admin;
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}
	
	

}
