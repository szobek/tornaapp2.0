package model;

public class UserRight {
    private final boolean listreserves;
    private final boolean newuser;

    private final boolean createPartner;


    public UserRight(boolean listreserves, boolean newuser, boolean createPartner) {
        super();
        this.listreserves = listreserves;
        this.newuser = newuser;
        this.createPartner = createPartner;
    }

    public boolean isReserveList() {
        return listreserves;
    }

    public boolean isCreateUser() {
        return newuser;
    }

    public boolean isCreatePartner() {
        return createPartner;
    }

    @Override
    public String toString() {
        return "model.UserRight  reserveList=" + listreserves + ", createUser=" + newuser + "]";
    }


}
