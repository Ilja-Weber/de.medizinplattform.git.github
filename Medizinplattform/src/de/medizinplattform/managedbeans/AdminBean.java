package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.containerbeans.UserEditable;
import de.medizinplattform.entities.Users;


@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean {
	
	@ManagedProperty(value="#{users}")
	private Users users;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	boolean formForAddUserVisible = false;
	
	public String newUserName;
	public String newUserPassword;
	public String newUserRole;
	
	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getNewUserPassword() {
		return newUserPassword;
	}

	public void setNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}

	public String getNewUserRole() {
		return newUserRole;
	}

	public void setNewUserRole(String newUserRole) {
		this.newUserRole = newUserRole;
	}

	public AdminBean(){
		System.out.println("AdminBean started");
	}

	public boolean isFormForAddUserVisible() {
		return formForAddUserVisible;
	}

	public void setFormForAddUserVisible(boolean formForAddUserVisible) {
		this.formForAddUserVisible = formForAddUserVisible;
	}
	
	public String addNewUser(){
		if(users != null){
			if(!newUserName.equals("") && !newUserPassword.equals("") &&!newUserRole.equals("")){
				users.createUser(newUserName, newUserPassword, newUserRole);
			}
			else{
				System.out.println("Form input missinng: Cannot add new User");
			}
		}
		cancel();
		return null;
	}
	
	public String makeFormVisible(){
		formForAddUserVisible=true;
		return null;
	}
	
	public String cancel(){
		newUserName="";
		newUserPassword="";
		newUserRole="";
		formForAddUserVisible=false;
		return null;
	}
	
	public String editUser(UserEditable tableUser){
		System.out.println("Edit table user");
		tableUser.setEditable(true);
		return null;
	}
	
	public String saveUser(UserEditable tableUser){
		System.out.println("Save table user");
		tableUser.setEditable(false);
		return null;
	}
	
	public String removeUser(UserEditable tableUser){
		System.out.println("Remove table user");
		if(users != null){
			users.removeUser(tableUser);
		}
		return null;
	}
	
	
}
