package enums;

public enum Success {
    UPDATED(false),
    INSERTED(true),

    UPDATEROOM(false),
    UPDATEUSER(false),
    DELETEDUSER(false),
    INSERTEDROOM(false),
    DELETEDROOM(false),UPDATEINFORMATION(false),UPDATEPARTNER(false);

    private  boolean suc;
    Success(boolean data){
        this.suc=data;
    }

    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }
}
