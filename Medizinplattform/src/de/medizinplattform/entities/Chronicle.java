package de.medizinplattform.entities;

public class Chronicle {
	
	
	public String id;
	public String usersName;
	public String headersText;
	public String date;
	
	public Chronicle(String id, String usersName, String headersText, String date){
		this.id=id;
		this.usersName=usersName;
		this.headersText=headersText;
		this.date=date;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString(){
		return id+"@"+usersName;
	}
	

}
