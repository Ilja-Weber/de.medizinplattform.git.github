package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.entities.Chronicle;
import de.medizinplattform.entities.Chronicles;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	//Injecting Chronicles
	@ManagedProperty(value="#{chronicles}")
	private Chronicles chronicles;
	
	public Chronicles getChronicles() {
		return chronicles;
	}
	
	public void setChronicles(Chronicles chronicles) {
		this.chronicles = chronicles;
	}
	
	//Injecting UserBean
	@ManagedProperty(value="#{userBean}")
	private UserBean loggedUser;
	
	public UserBean getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserBean loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	List<Chronicle> chroniclesList = new ArrayList<Chronicle>();
	
	
	public ChronicleBean(){
		System.out.println("ChronicleBean started");
	}
	
	
	public List<Chronicle> getChroniclesList(){
		if(loggedUser!=null){
			return chronicles.getUsersChronicles(loggedUser.getName());
		}
		return null;
		
	}
	
	
	public String editChronicle(Chronicle chronicle){
		chronicle.setEditable(true);
		return null;
	}
	
	
	public String saveChronicle(Chronicle chronicle){
		chronicle.setEditable(false);
		return null;
	}
	
	
	public String removeChronicle(Chronicle chronicle){
		return null;
	}
	
}
