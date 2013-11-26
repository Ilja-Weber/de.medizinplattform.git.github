package de.medizinplattform.entities;

public class Chronicle {
	
	
	public String usersName;
	public String headersText;
	public boolean editable=false;
	
	public Chronicle(String usersName, String headersText){
		this.usersName=usersName;
		this.headersText=headersText;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getHeadersText() {
		return headersText;
	}

	public void setHeadersText(String headersText) {
		this.headersText = headersText;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	

}
