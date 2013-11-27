package de.medizinplattform.managedbeans;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.medizinplattform.entities.Users;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
	public String name;
	public String password;
	
	
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
	
	
	public LoginBean(){
		
		//debug
		System.out.println("Login Bean started");
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
	
	
	public String login(){
		//debug
		if(users != null){
			
			if(users.hasUser(name)==false){
				System.out.println("no such user exists");
			}
			else{
				if(password.equals(users.getUsersPassword(name))){
					
					session.setUsersName(name);
					session.setCanSeeChronicleOf(name); //<-Von interesse fuer Admins, die chronicle von anderen anschauen wollen.
					
					if(users.getUsersRole(name).equals("user")){
						session.setAdmin(false);
						session.setUser(true);
					}
					
					else if(users.getUsersRole(name).equals("admin")){
						session.setAdmin(true);
						session.setUser(true);
					}
					else{
						System.out.println("Error: No such role exists");
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
