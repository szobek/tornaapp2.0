package model;

public class UserRight {
	private final boolean listreserves ;
	private final boolean newuser;
	
	

	public UserRight( boolean listreserves, boolean newuser) {
		super();
		this.listreserves  = listreserves;
		this.newuser = newuser;
	}

	public boolean isReserveList() {
		return listreserves ;
	}
	public boolean isCreateUser() {
		return newuser;
	}
	@Override
	public String toString() {
		return "model.UserRight  reserveList=" + listreserves  + ", createUser=" + newuser + "]";
	}
	
	

}
