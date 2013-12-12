package de.medizinplattform.managedbeans;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.User;
import de.medizinplattform.managers.UsersManager;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
	
	//Injecting sessionBean
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean session;	
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	//Variables
	private String name;
	private String password;	
	private boolean loginFormVisible = false;
	
	
	
	
	public LoginBean(){
		loginFormVisible=false;
	}
	
	//Getters-Setters
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
	public boolean isLoginFormVisible() {
		return loginFormVisible;
	}
	public void setLoginFormVisible(boolean loginFormVisible) {
		this.loginFormVisible = loginFormVisible;
	}
	
	
	
	public String makeLoginFormVisible(){
		loginFormVisible=true;
		return null;
	}
	
	
	public String login(){
		if(name.length()>0 && password.length()>0){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT x FROM User x WHERE x.name ='"+name+"'");
		
		List<User> usersList = (List<User>) q.getResultList();
		
		if(usersList.size()>1){
			//Error: Should throw an Exception, because there cannot be many users with same name
			System.out.println("Oooops! Too many Users with same name found!");
		}
		else if(usersList.size()==0){
			//None User with that name found, maybe there was a mistake in the spelling, or the user is not registered
			System.out.println("Oooops! No such User found!");
		}
		else{
			//One User found, now check if password is correct and if, then set session data
			if(usersList.get(0).getPassword().equals(password)){
				session.setUsersName(usersList.get(0).getName());
				session.setCanSeeChronicleOf(usersList.get(0).getName());
				session.setGuest(false);
				session.setUser(true);
				session.setAdmin(usersList.get(0).isAdmin());
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
}
