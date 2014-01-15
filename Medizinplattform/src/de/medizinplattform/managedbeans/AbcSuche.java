package de.medizinplattform.managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="abcSuche")
@SessionScoped
public class AbcSuche implements Serializable {
	public String text;
	
	public AbcSuche() {
		text=null;
	}
	
	private static final long serialVersionUID = 1L;
	private static final Krankheiten[] diagnosenList = new Krankheiten[] {
		new Krankheiten(1, "Erkältung"),
		new Krankheiten(2, "Grippe"),
		new Krankheiten(3, "Multiple Sklerose (MS)"),
		new Krankheiten(4, "Osteoporose"),
		new Krankheiten(5, "Schilddrüsenunterfunktion")
	};
	public Krankheiten[] getDiagnosenList() {
		return diagnosenList;
	}
	public static class Krankheiten{
		int diagnoseId;
		String diagnose;

		public Krankheiten(int diagnoseId, String diagnose) {
			this.diagnoseId = diagnoseId;
			this.diagnose = diagnose;
		}
		public void setDiagnoseId(int diagnoseId) {
			this.diagnoseId=diagnoseId;
		}
		public int getDiagnoseId() {
			return diagnoseId;
		}
		
		public void setDiagnose(String diagnose) {
			this.diagnose=diagnose;
		}
		public String getDiagnose() {
			return diagnose;
		}
		//getter and setter methods
	}
	
	//------------------------------------------------------------
	
	public String editAction() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String action = params.get("action");
		this.text=action;
		return null;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text=text;
	}
}
