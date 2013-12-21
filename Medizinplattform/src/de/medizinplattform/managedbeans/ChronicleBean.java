package de.medizinplattform.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.medizinplattform.managedbeans.components.NewStoryComponent;

@ManagedBean(name="chronicleBean")
@SessionScoped
public class ChronicleBean {
	//Constants - INNER
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
	
	//Constructor
	public ChronicleBean(){
		System.out.println("ChronicleBean started");
	}
	
		
	
	//Variable - OUTER
	private NewStoryComponent newStoryC;
	public NewStoryComponent getNewStoryComponent(){
		if(newStoryC==null){
			newStoryC=new NewStoryComponent(this);
		}
	return newStoryC;
	}

}
