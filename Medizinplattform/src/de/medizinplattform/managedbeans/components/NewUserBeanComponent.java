package de.medizinplattform.managedbeans.components;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.User;
import de.medizinplattform.managedbeans.AdminBean;

public class NewUserBeanComponent {
	//Constants
	private final String PERSISTENCE_UNIT_NAME = "common-entities";	
	
	//Variables
	private String name="";
	private String password="";
	private String role="";
	private boolean newUserFormVisible=false;
	private AdminBean parent;
	
	//Constructor
	public NewUserBeanComponent(AdminBean adminBean){
		this.parent=adminBean;
	}
	
	
	//Getters-setters
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
	public String showNewUserForm(){
		newUserFormVisible=true;
		return null;
	}
	public void hideNewUserForm(){
		newUserFormVisible=false;
	}
	public boolean isNewUserFormVisible(){
		return newUserFormVisible;
	}
	
	public String createButton(){
		if(!name.equals("") && !password.equals("") && !role.equals("")){
			//Obtain em
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			//Check if such User already exists in the database
			Query q = em.createQuery("SELECT x FROM User x WHERE x.name = '"+name+"'");
			List<User> usersList = (List<User>) q.getResultList();
			//Now if there many, then throw exception
			if(usersList.size()>1){
				//TODO Throw exception
				System.out.println("Oooops! Too many users with that name!");
			}
			else if(usersList.size()==1){
				//User already exists
				System.out.println("Oooops! Sch User already exists in the database");
			}
			else{
				//No user found? Create one!
				User user = new User();
				user.setName(name);
				user.setPassword(password);
				user.setRole(role);
				
				//Start transaction, persist User to database and commit changes
				em.getTransaction().begin();
				em.persist(user);
				em.getTransaction().commit();
				
				parent.getUsersC().add(new UserBeanComponent(parent, user));
				
				cancelButton();
			}
			
		}
		return null;
	}
	
	public String cancelButton(){
		name="";
		password="";
		role="";
		hideNewUserForm();
		return null;
	}
	
}
