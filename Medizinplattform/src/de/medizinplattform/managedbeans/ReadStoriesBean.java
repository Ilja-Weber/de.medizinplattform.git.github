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
import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.entities.Symptom;

@ManagedBean(name="readStoriesBean")
@SessionScoped
public class ReadStoriesBean {
	
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
	
	List<Story> story;
	public List<Story> getStory() {
		return story; 
	}
	public void setStory(List<Story> story) {
		this.story=story;
	}
	
	List<Long> storyId;
	public List<Long> getStoryId() {
		return storyId; 
	}
	public void setStoryId(List<Long> storyId) {
		this.storyId=storyId;
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
	
	List<Diagnosis> readDiagnosen;
	public List<Diagnosis> getReadDiagnosen() {
		return readDiagnosen; 
	}
	public void setReadDiagnosen(List<Diagnosis> readDiagnosen) {
		this.readDiagnosen=readDiagnosen;
	}
	
	public String sort = "ASC";
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	// Konstruktor
	public ReadStoriesBean() {
		/*Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String about = params.get("about");
		this.about = about;*/
		this.about="Erkältung";
		//this.story = new ArrayList<Story>();
		//this.story.add(null);
		
		this.storyId = new ArrayList<Long>();
		this.story = new ArrayList<Story>();
		//this.story.add(null);
		
		this.symListbox = new ArrayList<String>();
		this.symListbox.add("kein Symptom");
		
		this.behListbox = new ArrayList<String>();
		this.behListbox.add("keine Behandlung");
		
		this.getAllStories();
		this.listFill();
	}
		
		public String zeigeRecord(String about) {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			//String about = params.get("about");
			this.about = about;
			return null;
		}
		
		public void getAllStories() {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			
			Query q = em.createQuery("SELECT DISTINCT d FROM Diagnosis d WHERE d.diagnosis LIKE '%"+this.about+"%' ORDER BY d.id ASC");
			this.readDiagnosen = (List<Diagnosis>) q.getResultList();
			System.out.println("Size readDiagnosen: "+this.readDiagnosen.size());
			for(Diagnosis d:this.readDiagnosen ) {
				this.storyId.add(d.getBelongs_to_story());

				q = em.createQuery("SELECT s FROM Story s WHERE s.id = " + d.getBelongs_to_story());
				Story story = (Story) q.getSingleResult();
				this.story.add(story);
			}
			System.out.println(this.storyId);
		}
		
		public void sortieren(String sort) {
			System.out.println(sort);
		}
		
		public void onTabChange(TabChangeEvent event) {
			String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex();
			System.out.println("Active: "+activeIndex);
			int y = Integer.parseInt(activeIndex);
			
			long id = this.storyId.get(y);
			System.out.println("suche id: "+id);
			Query q;
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager em = emf.createEntityManager();
			q = em.createQuery("SELECT s FROM Symptom s WHERE s.belongs_to_story='"+id+"' ");
			System.out.println("q: "+q);
			this.symptom = (List<Symptom>) q.getResultList();
			System.out.println("this.symptom: "+this.symptom);
			q = em.createQuery("SELECT a FROM Action a WHERE a.belongs_to_story='"+id+"' ");
			this.action = (List<Action>) q.getResultList();
			System.out.println("this.action: "+this.action);
			
	    }
		
		public void listFill() {
			/*EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
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
			System.out.println("-------------------------------");*/
		}
		
}
