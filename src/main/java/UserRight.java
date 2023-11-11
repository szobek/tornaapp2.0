public class UserRight {
	private int userId;
	private boolean listreserves ;
	private boolean newuser;
	
	
	
	public UserRight() {
		super();
		
	}
	public UserRight(int userId, boolean listreserves, boolean newuser) {
		super();
		this.userId = userId;
		this.listreserves  = listreserves;
		this.newuser = newuser;
	}
	public int getUserId() {
		return userId;
	}
	public boolean isReserveList() {
		return listreserves ;
	}
	public boolean isCreateUser() {
		return newuser;
	}
	@Override
	public String toString() {
		return "UserRight [userId=" + userId + ", reserveList=" + listreserves  + ", createUser=" + newuser + "]";
	}
	
	

}
