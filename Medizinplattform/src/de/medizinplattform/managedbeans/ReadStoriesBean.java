package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;

import de.medizinplattform.entities.Action;
import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;

@ManagedBean(name="readStoriesBean")
@SessionScoped
public class ReadStoriesBean {
	
	public static String parameterAbout;
	public String getparameterAbout() {
		return parameterAbout;
	}
	public void setParameterAbout(String parameterAbout) {
		this.parameterAbout=parameterAbout;
	}
	
	
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
    
	public static String about;
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about=about;
	}
	
	public static String[] outputListe;
	public String[] getOutputListe() {
		return outputListe;
	}
	public void setOutputListe(String[] outputListe) {
		this.outputListe=outputListe;
	}
	
	List<String> symListbox = null;
	public List<String> getSymListbox() {
		return symListbox; 
	}
	public void setSymListbox(List<String> symListbox) {
		this.symListbox=symListbox;
	}
	
	List<SelectItem> selectSymptom;
	public List<SelectItem> getSelectSymptom() {
		return selectSymptom; 
	}
	public void setSelectSymptom(List<SelectItem> selectSymptom) {
		this.selectSymptom=selectSymptom;
	}
	
	List<Diagnosis> diagnosis;
	public List<Diagnosis> getDiagnosis() {
		return diagnosis; 
	}
	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis=diagnosis;
	}
	
	List<Symptom> symptom;
	public List<Symptom> getSymptom() {
		return symptom; 
	}
	public void setSymptom(List<Symptom> symptom) {
		this.symptom=symptom;
	}
	
	List<Action> action;
	public List<Action> getAction() {
		return action; 
	}
	public void setAction(List<Action> action) {
		this.action=action;
	}
	
	List<String> behListbox = null;;
	public List<String> getBehListbox() {
		return behListbox; 
	}
	public void setBehListbox(List<String> behListbox) {
		this.behListbox=behListbox;
	}
	
	List<SelectItem> selectBehandlung;
	public List<SelectItem> getSelectBehandlung() {
		return selectBehandlung; 
	}
	public void setSelectBehandlung(List<SelectItem> selectBehandlung) {
		this.selectBehandlung=selectBehandlung;
	}
	
	List<Story> readStories;
	public List<Story> getReadStories() {
		return readStories; 
	}
	public void setReadStories(List<Story> readStories) {
		this.readStories=readStories;
	}
	
	public String sort = "ASC";
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public List<Long> dId;
	public List<Long> getDId() {
		return dId;
	}
	public void setDId(List<Long> dId) {
		this.dId = dId;
	}
	
	// Konstruktor
	public ReadStoriesBean() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String str = params.get("parameterAbout");
		this.about = null;
		this.about = str;
		System.out.println("this.about:"+this.about);
		this.about="Erkältung";
		
		
		this.symListbox = new ArrayList<String>();
		this.behListbox = new ArrayList<String>();
		this.getAllStories();
		this.listFill();
	}
		
		public void getAllStories() {
			this.readStories = new ArrayList<Story>();
			/*Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String str = params.get("parameterAbout");
			this.about = str;
			System.out.println("this.about:"+this.about);*/
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();

			//alle Geschichten ohne Filter
			Query q = em.createQuery("SELECT DISTINCT d.belongs_to_story FROM Diagnosis d WHERE d.diagnosis LIKE '%"+this.about+"%' ORDER BY d.year "+this.sort+", d.month "+this.sort+", d.day "+this.sort+", d.hour "+this.sort+", d.minute "+this.sort+", d.second "+this.sort);
			this.dId = (List<Long>) q.getResultList();
			System.out.println("Size dId: "+this.dId);
			System.out.println("---");
			
			for(Long d:this.dId) {
				System.out.println(d);
				q = em.createQuery("SELECT s FROM Story s WHERE s.id = " + d);
				Story story = (Story) q.getSingleResult();
				System.out.println(story);
				this.readStories.add(story);
			}
			for(Story s:this.readStories) {
				switch (s.getState()) {
				case "closed":
					s.setState("geschlossen");
					break;
				case "running":
					s.setState("offen");
					break;
				}
			}
			
			//Filter nach Symptomen
			System.out.println("symListbox"+this.symListbox.size());
			System.out.println("symListbox: "+this.symListbox);
			String strS = "";
			int hS;
			if (this.symListbox.size() > 0) {
				int count = 1;
				for(String s: this.symListbox) {
			        hS = Integer.parseInt(s);
			        System.out.println("hS: "+hS);
			        System.out.println(this.selectSymptom.get(hS).getLabel());
			        if (count == 1)
		        		strS += " AND (s.term = '"+this.selectSymptom.get(hS).getLabel()+"'";
			        else
			        	strS += " OR s.term = '"+this.selectSymptom.get(hS).getLabel()+"'";
				    	count++;
				}
				strS += ")";
			
				for (int i=0;i<this.readStories.size();i++) {
					long id = this.readStories.get(i).getId();
					System.out.println("SELECT s FROM Symptom s WHERE s.belongs_to_story='"+id+"' "+strS);
		        	q = em.createQuery("SELECT s FROM Symptom s WHERE s.belongs_to_story='"+id+"' "+strS);
		        	List<Symptom> s = (List<Symptom>) q.getResultList();
		        	System.out.println(s.size());
		        	if (s.size() == 0)
		        		this.readStories.remove(i);
				}
			}
			
			//Filter nach Behandlung
			System.out.println("behListbox"+this.behListbox.size());
			System.out.println("behListbox: "+this.behListbox);
			String strB = "";
			int hB;
			if (this.behListbox.size() > 0) {
				int count = 1;
				for(String b: this.behListbox) {
			        hB = Integer.parseInt(b);
			        System.out.println("hB: "+hB);
			        System.out.println(this.selectBehandlung.get(hB).getLabel());
			        if (count == 1)
		        		strB += " AND (a.action = '"+this.selectBehandlung.get(hB).getLabel()+"'";
			        else
			        	strB += " OR a.action = '"+this.selectBehandlung.get(hB).getLabel()+"'";
				    	count++;
				}
				strB += ")";
			
				for (int i=0;i<this.readStories.size();i++) {
					long id = this.readStories.get(i).getId();
					System.out.println("SELECT a FROM Action a WHERE a.belongs_to_story='"+id+"' "+strB);
		        	q = em.createQuery("SELECT a FROM Action a WHERE a.belongs_to_story='"+id+"' "+strB);
		        	List<Action> a = (List<Action>) q.getResultList();
		        	System.out.println(a.size());
		        	if (a.size() == 0)
		        		this.readStories.remove(i);
				}
			}
			
			System.out.println("strB: "+strB);
			
			System.out.println("sort: "+this.sort);
		}
		
		public void onTabChange(TabChangeEvent event) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			
			String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex();
			try {
				int y = Integer.parseInt(activeIndex);
				System.out.println("Active: "+y);
				System.out.println(this.readStories.get(y).getId());
				long id = this.readStories.get(y).getId() ;
				Query q;
				q = em.createQuery("SELECT d FROM Diagnosis d WHERE d.belongs_to_story='"+id+"' ");
				this.diagnosis = (List<Diagnosis>) q.getResultList();
				
				q = em.createQuery("SELECT s FROM Symptom s WHERE s.belongs_to_story='"+id+"' ");
				this.symptom = (List<Symptom>) q.getResultList();
				
				q = em.createQuery("SELECT a FROM Action a WHERE a.belongs_to_story='"+id+"' ");
				this.action = (List<Action>) q.getResultList();
			}
			catch (NumberFormatException e) {
			}
			
	    }
		
		public void listFill() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			Query q;
			List<String> sym = null;
			List<String> act = null;
			
			for (int i=0;i<this.dId.size();i++) {
				long id = this.dId.get(i);

				//Symptomen List
				q = em.createQuery("SELECT DISTINCT s.term FROM Symptom s WHERE s.belongs_to_story='"+id+"' ");
				sym = (List<String>) q.getResultList();
				for(String s:sym) 
					if (!this.symListbox.contains(s))
						this.symListbox.add(s);
				
				//Behandlungen List
				q = em.createQuery("SELECT DISTINCT a.action FROM Action a WHERE a.belongs_to_story='"+id+"' ");
				act = (List<String>) q.getResultList();
				for(String a:act) 
					if (!this.behListbox.contains(a))
						this.behListbox.add(a);
			}	
			
			int m=0;
			//Symtomen List befüllen
			this.selectSymptom = new ArrayList<SelectItem>();
			for(String s : this.symListbox) {
				this.selectSymptom.add(new SelectItem(m,s));
				m++;
			}
			System.out.println("-------------------------------");
			
			//Behandlungen List befüllen
			this.selectBehandlung = new ArrayList<SelectItem>();
			m=0;
			for(String b : this.behListbox) {
				this.selectBehandlung.add(new SelectItem(m,b));
				m++;
			}
			System.out.println("-------------------------------");
		}
		
}
