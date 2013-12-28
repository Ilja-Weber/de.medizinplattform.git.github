package de.medizinplattform.managedbeans.components;

import java.util.List;

import de.medizinplattform.entities.Entry;
import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;

public class StoryComponent {

	//Variable - INNER
	private ChronicleBean parent;
		
	public StoryComponent(ChronicleBean parent, Story story){
		this.parent=parent;
		this.story=story;
	}
	
	//Variable - OUTER (Getter)
	public boolean isEmpty(){
		return false;
	}
	
	//Variable - OUTER
	private List<Entry> entries;
	public List<Entry> getEntries(){
		if(entries == null){
			
		}
		return entries;
	}
	
	//Variable - OUTER
	private Story story;
	public Story getStory(){
		return story;
	}
	
	
}
