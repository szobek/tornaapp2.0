public enum Success {
    UPDATED(false),INSERTED(true),DELETED(false);

    private  boolean suc;
    private Success(boolean data){
        this.suc=data;
    }

    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }
}
