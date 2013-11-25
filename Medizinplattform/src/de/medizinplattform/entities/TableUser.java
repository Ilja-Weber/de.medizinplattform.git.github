package de.medizinplattform.entities;

public class TableUser extends User{
	
	boolean canEdit = false;
	boolean canRemove = false;
	
	User user;
	
	public TableUser(User user){
		this.user=user;
		System.out.println("TableUser created");
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}

}
