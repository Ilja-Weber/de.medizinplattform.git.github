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
	
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean session;
	
	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
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
				boolean isAdmin = (newUserRole.equals("admin")) ? true : false;
				users.createUser(newUserName, newUserPassword, isAdmin);
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
	
	public String editUser(UserEditable userEditable){
		userEditable.setEditable(true);
		return null;
	}
	
	public String saveUser(UserEditable userEditable){
		userEditable.setEditable(false);
		return null;
	}
	
	public String removeUser(UserEditable userEditable){
		if(users != null){
			users.removeUser(userEditable);
		}
		return null;
	}
	
	
	public String viewUsersChronicle(UserEditable userEditable){
		if(session != null){
			session.setCanSeeChronicleOf(userEditable.getName());
			return "chronicle.xhtml?faces-redirect=true";
		}
		return null;
	}
	
	
}
