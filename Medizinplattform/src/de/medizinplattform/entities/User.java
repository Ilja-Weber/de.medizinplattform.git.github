package de.medizinplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTERED_USER")
public class User{
	
	@Id @GeneratedValue
	private long id;
	
	private String name;	
	private String password;
	private String role;
	
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public boolean isAdmin() {
		return role.equals("admin");
	}
	public void setAdmin(boolean isAdmin) {
		this.role = (isAdmin)?"admin":"user";
	}
	
	

}
