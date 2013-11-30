package de.medizinplattform.utilitybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.medizinplattform.containerbeans.EntriesContainerEditable;
import de.medizinplattform.db.FakeDatabase;

@ManagedBean(name="idGenerator")
@ApplicationScoped
public class IdGeneratorBean {
	//Injecting Database
		@ManagedProperty(value="#{dataBase}")
		private FakeDatabase dataBase;			
		public FakeDatabase getDataBase() {
			return dataBase;
		}			
		public void setDataBase(FakeDatabase dataBase) {
			this.dataBase = dataBase;
		}
		
		public String getUniqueEntrieContainerId(){
			List<String> allIds = new ArrayList<String>();
			for(EntriesContainerEditable container : dataBase.getEntriesContainerTable()){
				allIds.add(container.getId());
			}
			
			boolean not_found = true;
			String SampleId = null;
			
			while(not_found){
				not_found=false;
				SampleId = String.valueOf(((int)(Math.random()*10000+10000)));
				
				for(String idFromList : allIds){
					if(idFromList.equals(SampleId)){
						not_found=true;
					}
				}
			}
			
			return SampleId;
		}
}
