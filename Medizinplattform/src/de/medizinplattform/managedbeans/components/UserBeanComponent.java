package de.medizinplattform.managedbeans.components;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.medizinplattform.entities.User;
import de.medizinplattform.managedbeans.AdminBean;

public class UserBeanComponent {
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	//Variables
	private String name;
	private String password;
	private String role; 
	
	private String t_name;
	private String t_password;
	private String t_role; 
	
	
	private boolean beingEdited;
	private User user;
	private AdminBean parent;
	
	
	//Constructor
	public UserBeanComponent(AdminBean adminBean, User user){
		this.user=user;
		this.parent=adminBean;
		
		this.name=user.getName();
		this.password=user.getPassword();
		this.role=user.getRole();
	}

	
	//Getters-Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.t_name=this.name;
		this.name=name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.t_password=this.password;
		this.password=password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.t_role=this.role;
		this.role=role;
	}

	public boolean isBeingEdited() {
		return beingEdited;
	}

	public void setBeingEdited(boolean beingEdited) {
		this.beingEdited = beingEdited;
	}
	
	//Some buttonslogic
	
	public String editButton(){
		beingEdited=true;
		return null;
	}
	
	public String saveButton(){
		beingEdited=false;
		
		
		
		//Obtain em
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
				
		//start transaction, delete user and commit changes
		em.getTransaction().begin();
		User toBeUpdated = em.merge(user);
		toBeUpdated.setName(name);
		toBeUpdated.setPassword(password);
		toBeUpdated.setRole(role);
		em.getTransaction().commit();
		return null;
	}
	
	public String cancelButton(){
		beingEdited=false;
		this.name=this.t_name;;
		this.password=this.t_password;
		this.role=this.t_role;
		return null;
	}
	
	public String deleteButton(){
		//Obtain em
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		
		//start transaction, delete user and commit changes
		em.getTransaction().begin();
		User toBeRemoved = em.merge(user);
		em.remove(toBeRemoved);
		em.getTransaction().commit();
		
		//remove userbeancomponent from userbeancomponents list in adminbean
		parent.getUsersC().remove(this);
		
		return null;
	}
	
	
}
