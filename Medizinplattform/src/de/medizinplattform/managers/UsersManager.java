package de.medizinplattform.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.medizinplattform.entities.User;

@ManagedBean(name="usersManager")
@SessionScoped
public class UsersManager {
	
	private final String PERSISTENCE_UNIT_NAME = "common-entities";
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em = null;
	
	private void initEntityManager(){
		em = emf.createEntityManager();
		
		return;
	}
	
	public UsersManager(){
		initEntityManager();
		System.out.println("usersManager started: Em = "+em);
	}
	
	//Find a User in a Database
	public User findUserByName(String name){
		//Check if EntityManager already created, create new if not
		if(em==null){
			em = emf.createEntityManager();
		}
		
		
		//Create a query
		Query q = em.createQuery("SELECT x FROM User x WHERE x.name ='"+name+"'");
		List<User> usersList = (List<User>) q.getResultList();
		
		if(usersList.size()!=1){
			//TODO: Throw exception
			System.out.println("Ooooops! There was an error in findUserByName method!");
			return null;
		}
		else{
			return usersList.get(0);
		}
	}
	
	//Check if a Username exists in the Database	
	public boolean hasUser(String name){
		return (findUserByName(name)!=null)?true:false;
	}
	
	//Get Users password
	public String getUsersPassword(String name){
		return findUserByName(name).getPassword();
	}
	
	//Get Users role
	public boolean isUserAdmin(String name){
		return findUserByName(name).isAdmin();
	}	
	
	//Get all Users from Database
	public List<User> getUsersList(){
		//Check if EntityManager already created, create new if not
		if(em==null){
			em = emf.createEntityManager();
		}
				
				
		//Create a query
		Query q = em.createQuery("SELECT x FROM User x");
		List<User> usersList = (List<User>) q.getResultList();
		
		return usersList;
	}
	
	//Create a User in Database
	public void createUser(String name, String password, boolean isAdmin){
		//Check if EntityManager already created, create new if not
		if(em==null){
			em = emf.createEntityManager();
		}
		
		//Check if user already exists in the database
		if(hasUser(name)){
			//TODO: Throw exception
			System.out.println("Ooooops! Such User already exists, cannot create new");
			return;
		}
		
		//If no such user, then create an entity
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setAdmin(isAdmin);
		
		//Start transaction, persist User and commit
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	//Remove User from Database
	public void removeUser(User user){
		//Check if EntityManager already created, create new if not
		if(em==null){
			em = emf.createEntityManager();
		}
		
		//Check if user exists in the database
		if(!hasUser(user.getName())){
			//TODO: Throw exception
			System.out.println("Ooooops! No such User exists, cannot delete");
			return;
		}
		
		//Start transaction, persist User and commit
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
	
	
	
	public void refreshUser(User user){
		//Check if EntityManager already created, create new if not
		
		if(em==null){
			em = emf.createEntityManager();
		}
		
		
		
		//Check if user exists in the database
		if(!hasUser(user.getName())){
			//TODO: Throw exception
			System.out.println("Ooooops! No such User exists, cannot refresh");
			return;
		}
				
		//Start transaction, refresh User and commit
		em.getTransaction().begin();
		em.refresh(user);
		em.getTransaction().commit();
	}
}
