package de.medizinplattform.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;

import de.medizinplattform.entities.Action;
import de.medizinplattform.entities.Diagnosis;
import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;

@ManagedBean(name="readStoriesBean")
@SessionScoped
public class ReadStoriesBean {
	
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "common-entities";
	
	private int alter_min = 0; 
    public int getAlter_min() {  
        return alter_min;  
    }  
    public void setAlter_min(int alter_min) {  
        this.alter_min = alter_min;  
    }  
  
    private int alter_max = 100;
    public int getAlter_max() {  
        return alter_max;  
    }  
    public void setAlter_max(int alter_max) {  
        this.alter_max = alter_max;  
    }  
    
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
	
	//final int x=0;
	
	List<String> behListbox;
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
	
	List<Story> readDiagnosen;
	public List<Story> getReadDiagnosen() {
		return readDiagnosen; 
	}
	public void setReadDiagnosen(List<Story> readDiagnosen) {
		this.readDiagnosen=readDiagnosen;
	}
	
	// Konstruktor
	public ReadStoriesBean() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		//String about = params.get("about");
		//this.about = about;
		this.about="";
		
		this.symListbox = new ArrayList<String>();
		this.symListbox.add("kein Symptom");
		
		this.behListbox = new ArrayList<String>();
		this.behListbox.add("keine Behandlung");
		
		this.getAllStories();
		this.listFill();
	}
		
		
		
		
		
		
		
		Krankheiten[] diagnosenList;
		public Krankheiten[] getDiagnosenList() {
			diagnosenList = new Krankheiten[5];
			diagnosenList[0] = new Krankheiten("Erkältung", "Schnupfen", "Tropfen", "m", 25, new Date(2014,0,18));
			diagnosenList[1] = new Krankheiten("Erkältung", "Husten", "Saft", "w", 18, new Date(2013,11,12));
			diagnosenList[2] = new Krankheiten("Erkältung", "Husten", "Tablette", "m", 46, new Date(2013,11,24));
			diagnosenList[3] = new Krankheiten("Erkältung", "Kopfschmerzen", "Tablette", "w", 67, new Date(2010,1,25));
			diagnosenList[4] = new Krankheiten("Erkältung", "Husten", "Tablette", "m", 34, new Date(2010,1,24));
			return diagnosenList;
		}
		public static class Krankheiten {
			String diag, symp, beha, ges;
			Date datum;
			int alter;
			public Krankheiten(String diag,String symp, String beha, String ges, int alter, Date datum) {
				this.diag = diag;
				this.symp = symp;
				this.beha = beha;
				this.ges = ges;
				this.alter = alter;
				this.datum = datum;
			}
			public void setDiag(String diag) {
				this.diag=diag;
			}
			public String getDiag() {
				return diag;
			}
			public void setSymp(String symp) {
				this.symp=symp;
			}
			public String getSymp() {
				return symp;
			}
			public void setBeha(String beha) {
				this.beha=beha;
			}
			public String getBeha() {
				return beha;
			}
			public void setGes(String ges) {
				this.ges=ges;
			}
			public String getGes() {
				return ges;
			}
			public void setAlter(int alter) {
				this.alter=alter;
			}
			public int getAlter() {
				return alter;
			}
			public void setDatum(Date datum) {
				this.datum=datum;
			}
			public Date getDatum() {
				return datum;
			}
		};
	
		
		
							
		public String zeigeRecord(String about) {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			//String about = params.get("about");
			this.about = about;
			return null;
		}
		
		public void getAllStories() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			
			Query q = em.createQuery("SELECT s FROM Story s WHERE s.title LIKE '%"+this.about+"%' ORDER BY s.id ASC");
			this.readDiagnosen = (List<Story>) q.getResultList();
			System.out.println("Size readDiagnosen: "+this.readDiagnosen.size());
			System.out.println("---");
		}
		
		public void sortieren(String sort) {
			System.out.println(sort);
		}
		
		/*public void onTabChange(TabChangeEvent event) {
			String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex();
	        System.out.println("Active: "+activeIndex);
	        
	    }*/
		public void onTabChange(TabChangeEvent event) {
			String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex();
			System.out.println("Active: "+activeIndex);
			int y = Integer.parseInt(activeIndex);
			
			long id = this.readDiagnosen.get(y).getId();
			System.out.println("suche id: "+id);
			Query q;
			
			/*try {
				id = Long.parseLong(str);
				System.out.println("eingabe: "+id);
			} 
			catch (NumberFormatException e) {
			}*/
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			q = em.createQuery("SELECT s FROM Symptom s WHERE s.belongs_to_story='"+id+"' ");
			this.symptom = (List<Symptom>) q.getResultList();
			q = em.createQuery("SELECT a FROM Action a WHERE a.belongs_to_story='"+id+"' ");
			this.action = (List<Action>) q.getResultList();
			
			
	    }
		
		public void listFill() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			Query q;
			List<String> sym = null;
			List<String> act = null;
			
			for (int i=0;i<this.readDiagnosen.size();i++) {
				long id = this.readDiagnosen.get(i).getId();
				System.out.println(id);
				
				//Symptomen List
				q = em.createQuery("SELECT DISTINCT s.term FROM Symptom s WHERE s.belongs_to_story='"+id+"' ");
				sym = (List<String>) q.getResultList();
				System.out.println("sym"+sym);	
				for(String s:sym) 
					if (!this.symListbox.contains(s))
						this.symListbox.add(s);
				
				System.out.println("symListbox"+this.symListbox);	
				
				//Behandlungen List
				q = em.createQuery("SELECT DISTINCT a.action FROM Action a WHERE a.belongs_to_story='"+id+"' ");
				act = (List<String>) q.getResultList();
				System.out.println("act"+act);	
				for(String a:act) 
					if (!this.behListbox.contains(a))
						this.behListbox.add(a);
				
				System.out.println("behListbox"+this.behListbox);	
				
			}	
			
			int m=1;
			
			//Symtomen List befüllen
			this.selectSymptom = new ArrayList<SelectItem>();
			for(String s : this.symListbox) {
				this.selectSymptom.add(new SelectItem(m,s));
				m++;
			}
			for(SelectItem item : this.selectSymptom)
				System.out.println(item.getValue()+"-"+item.getLabel());
			System.out.println("-------------------------------");
			
			//Behandlungen List befüllen
			this.selectBehandlung = new ArrayList<SelectItem>();
			m=1;
			for(String b : this.behListbox) {
				this.selectBehandlung.add(new SelectItem(m,b));
				m++;
			}
			for(SelectItem item : this.selectBehandlung)
				System.out.println(item.getValue()+"-"+item.getLabel());
			System.out.println("-------------------------------");
		}
		
}
