package de.medizinplattform.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name="abcSucheBean")
@SessionScoped
public class AbcSucheBean implements Serializable {
	public static String text;
			// Getter-Setter
			public String getText() {
				return text; //text
			}
			
			public void setText(String text) {
				this.text=text;
			}
			
	public static String[] outputListe;
			// Getter-Setter
			public String[] getOutputListe() {
				return outputListe;
			}
			
			public void setOutputListe(String[] outputListe) {
				this.outputListe=outputListe;
			}
	
	
	// Konstruktor
	public AbcSucheBean() {
		text=null;
		outputListe = null;
	}
	
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	/*
	Krankheiten[] diagnosenList; 
	public Krankheiten[] getDiagnosenList() {
		diagnosenList = new Krankheiten[3];
		diagnosenList[0] = new Krankheiten("EEEEE"+this.getText());
		diagnosenList[1] = new Krankheiten("OOOOO"+this.getText());
		diagnosenList[2] = new Krankheiten("EEE1EE"+this.getText());
		return diagnosenList;
	}
					public static class Krankheiten{
						String diag;
						public Krankheiten(String diag) {
							this.diag = diag;
						}
						public void setDiag(String diag) {
							this.diag=diag;
						}
						public String getDiag() {
							return diag;
						}
					}
	*/
	
	public String editAction() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String action = params.get("action");
		this.text = action;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("SELECT e.diag FROM Entry e WHERE e.diag LIKE '"+this.text+"%' ");
		List<String> hilfsvar = (List<String>) q.getResultList();
		
		if ((hilfsvar == null) || (hilfsvar.size() == 0)) {
			System.out.println("Its null");
			this.outputListe = new String[1];
			this.outputListe[0] = new String("Keine Krankheit gefunden");
		}
		else {
			System.out.println("*****");
			System.out.println("Found "+hilfsvar.size()+":");
			for(int i = 0;i<hilfsvar.size();i++) {
				System.out.println(hilfsvar.get(i));
			}
			this.outputListe = new String[hilfsvar.size()];
			for (int i=0;i<hilfsvar.size();i++) 
				this.outputListe[i] = new String(""+hilfsvar.get(i));
		}
		return null;
	}
}

/*
<!-- 
	<h:dataTable value="#{krankheiten.diagnosenList}" var="d">
		<h:column>
			<f:facet name="header">Diagnose Id</f:facet>
			#{d.diag}
		</h:column>
		<h:column>
			<f:facet name="header">Diagnose</f:facet>
			#{d.diag}
		</h:column>
</h:dataTable>
-->
*/