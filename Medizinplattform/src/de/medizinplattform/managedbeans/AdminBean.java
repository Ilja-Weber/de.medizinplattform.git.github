package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.User;
import de.medizinplattform.managedbeans.components.NewUserBeanComponent;
import de.medizinplattform.managedbeans.components.UserBeanComponent;
import de.medizinplattform.managers.UsersManager;


@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean {
	
	//...?
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	//Injecting sessionBean
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean session;	
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}
	
	//Components
	private NewUserBeanComponent newUserC=null;
	private List<UserBeanComponent> usersC=null;
	
	
	//Constructor
	public AdminBean(){
		
	}
	
	//Getters-Setters
	public NewUserBeanComponent getNewUserC(){
		if(newUserC==null){
			newUserC = new NewUserBeanComponent(this);
		}
		return newUserC;
	}
	
	public List<UserBeanComponent> getUsersC(){
		if(usersC==null){
			usersC = new ArrayList<UserBeanComponent>();
			List<User> usersList = new ArrayList<User>();
			
			//Get list of all users
			//get entitymanager
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			//Create a query
			Query q = em.createQuery("SELECT x FROM User x");
			usersList = (List<User>) q.getResultList();
			
			//In for-loop create for each User a userComponent
			for(User user : usersList){
				usersC.add(new UserBeanComponent(this, user));
			}
			
		}
		return usersC;
	}
	
	
	public void removeUserComponent(UserBeanComponent userBeanComponent) {
		if(usersC!=null){
			usersC.remove(userBeanComponent);
		}
		
	}
	
	
	
}
