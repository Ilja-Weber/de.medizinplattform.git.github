package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.containerbeans.UserEditable;
import de.medizinplattform.managers.UsersManager;


@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean {
	
	@ManagedProperty(value="#{usersManager}")
	private UsersManager usersManager;
	
	public UsersManager getUsersManager() {
		return usersManager;
	}

	public void setUsersManager(UsersManager usersManager) {
		this.usersManager = usersManager;
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
	
	public String addNewUserButton(){
		if(usersManager != null){
			if(!newUserName.equals("") && !newUserPassword.equals("") &&!newUserRole.equals("")){
				boolean isAdmin = (newUserRole.equals("admin")) ? true : false;
				usersManager.createUser(newUserName, newUserPassword, isAdmin);
			}
			else{
				System.out.println("Form input missinng: Cannot add new User");
			}
		}
		cancelButton();
		return null;
	}
	
	public String makeFormVisible(){
		formForAddUserVisible=true;
		return null;
	}
	
	public String cancelButton(){
		newUserName=null;
		newUserPassword=null;
		newUserRole=null;
		formForAddUserVisible=false;
		return null;
	}
	
	public String editUserButton(UserEditable userEditable){
		userEditable.setEditable(true);
		return null;
	}
	
	public String saveUserButton(UserEditable userEditable){
		userEditable.setEditable(false);
		return null;
	}
	
	public String removeUserButton(UserEditable userEditable){
		if(usersManager != null){
			usersManager.removeUser(userEditable);
		}
		return null;
	}
	
	
	public String viewUsersChronicleLink(UserEditable userEditable){
		if(session != null){
			session.setCanSeeChronicleOf(userEditable.getName());
			return "chronicle.xhtml?faces-redirect=true";
		}
		return null;
	}
	
	
}
