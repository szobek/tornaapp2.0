package model;

public class UserRight {
    private final boolean listreserves;
    private final boolean newuser;

    private final boolean createPartner;
//////////////////////////////////////////////////////////////////////////
    private final boolean changeRoomName;
    private final boolean changeRoomImages;
    private final boolean changeRoomNum;
    private final boolean createRoom;
    ////////////////////////////////////////////////////////////////////////////


    public UserRight(boolean listreserves, boolean newuser, boolean createPartner, boolean changeRoomName, boolean changeRoomImages, boolean changeRoomNum, boolean createRoom) {
        this.listreserves = listreserves;
        this.newuser = newuser;
        this.createPartner = createPartner;
        this.changeRoomName = changeRoomName;
        this.changeRoomImages = changeRoomImages;
        this.changeRoomNum = changeRoomNum;
        this.createRoom = createRoom;
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

    public boolean isChangeRoomName() {
        return changeRoomName;
    }

    public boolean isChangeRoomImages() {
        return changeRoomImages;
    }

    public boolean isChangeRoomNum() {
        return changeRoomNum;
    }

    public boolean isCreateRoom() {
        return createRoom;
    }
}
