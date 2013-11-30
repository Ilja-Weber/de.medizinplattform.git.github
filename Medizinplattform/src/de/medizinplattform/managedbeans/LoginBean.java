package de.medizinplattform.managedbeans;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.medizinplattform.managers.UsersManager;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
	
	public String name;
	public String password;
	
	private boolean loginFormVisible = false;
	
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
	
	
	public LoginBean(){
		loginFormVisible=false;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isLoginFormVisible() {
		return loginFormVisible;
	}

	public void setLoginFormVisible(boolean loginFormVisible) {
		this.loginFormVisible = loginFormVisible;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String makeLoginFormVisible(){
		loginFormVisible=true;
		return null;
	}
	
	
	public String login(){
		//debug
		if(usersManager != null){
			
			if(usersManager.hasUser(name)==false){
				System.out.println("no such user exists");
			}
			else{
				if(password.equals(usersManager.getUsersPassword(name))){
					
					session.setUsersName(name);
					session.setCanSeeChronicleOf(name); //<-Von interesse fuer Admins, die chronicle von anderen anschauen wollen.
					
					session.setUser(true);
					if(usersManager.isUserAdmin(name)){
						session.setAdmin(true);
					}
					
					session.setGuest(false);
					
					return "index.xhtml?faces-redirect=true";
				}
				else{
					return "login_fail.xhtml?faces-redirect=true";
				}
			}
				
	    
		
		}       
		return null;    
	}
	public String logout(){
		//debug
		session.setGuest(true);
		session.setAdmin(false);
		session.setUser(false);
		session.setUsersName(null);
				
		return "index.xhtml?faces-redirect=true";
	}
	
	@PreDestroy
	public void cry(){
		//System.out.println("LoginBean is about to be destroyed");
	}
	
}
