package de.medizinplattform.managedbeans.components;

import de.medizinplattform.entities.Story;
import de.medizinplattform.managedbeans.ChronicleBean;

public class StoryComponent {

	//Variable - INNER
	private Story story;
	private ChronicleBean parent;
	
	
	public StoryComponent(ChronicleBean parent, Story story){
		this.parent=parent;
		this.story=story;
	}
}
