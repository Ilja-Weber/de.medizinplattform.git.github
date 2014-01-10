package de.medizinplattform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Diagnosis {

	@Id @GeneratedValue
	private long id;
	public long getId(){
		return id;
	}
	public void setId(long newId){
		this.id=newId;
	}
	
	//Text der Diagnose
	private String diag_text;	
	public String getDiag_text() {
		return diag_text;
	}
	public void setDiag_text(String diag_text) {
		this.diag_text = diag_text;
	}	

	//Optionale Saitenlokalisation
	private String site_loc;
	public String getSite_loc() {
		return site_loc;
	}
	public void setSite_loc(String site_loc) {
		this.site_loc = site_loc;
	}
	
	//Von wem die Diagnose ist
	private String who_did_diag;
	public String getWho_did_diag() {
		return who_did_diag;
	}
	public void setWho_did_diag(String who_did_diag) {
		this.who_did_diag = who_did_diag;
	}
	
	//Datum und Zeit im format yyyyMMddhhmm
	private int when;
	public int getWhen() {
		return when;
	}
	public void setWhen(int when) {
		this.when = when;
	}
}
