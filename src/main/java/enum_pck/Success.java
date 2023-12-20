package enum_pck;

public enum Success {
    UPDATED(false),
    INSERTED(true),
    DELETED(false),
    UPDATEROOM(false),
    UPDATEUSER(false),
    DELETEDUSER(false),
    INSERTEDROOM(false),
    DELETEDROOM(false),UPDATEINFORMATION(false);

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
