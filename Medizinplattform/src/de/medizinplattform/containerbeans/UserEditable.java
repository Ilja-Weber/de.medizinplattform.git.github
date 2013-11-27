package de.medizinplattform.containerbeans;

import de.medizinplattform.entities.User;

public class UserEditable{
	
	private boolean editable = false;
	
	private User user;
	
	public UserEditable(User user){
		this.user=user;
		System.out.println("TableUser created");
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getName(){
		return user.getName();
	}
	
	public void setName(String name){
		user.setName(name);
	}
	
	public String getPassword(){
		return user.getPassword();
	}
	
	public void setPassword(String password){
		user.setPassword(password);
	}
	
	public String getRole(){
		return user.getRole();
	}
	
	public void setRole(String role){
		user.setRole(role);
	}	

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
