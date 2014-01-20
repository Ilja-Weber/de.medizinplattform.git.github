package de.medizinplattform.managedbeans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="abcSucheBean")
@SessionScoped
public class ReadStoriesBean {
	public static String about;
			// Getter-Setter
			public String getAbout() {
				return about;
			}
			
			public void setAbout(String about) {
				this.about=about;
			}
	
	public ReadStoriesBean() {
		this.about=null;
	}
	
	public String Param() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String about = params.get("about");
		
		this.about=about;
		return about;
	}
	
}
