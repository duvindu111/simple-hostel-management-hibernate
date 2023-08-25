package lk.ijse.simple_hostel_management_hibernate.view.tm;

public class RoomTM {

    private String roomTypeId;
    private String roomType;
    private int perRoom;
    private String keyMoney;
    private int roomQty;

    public RoomTM() {
    }

    public RoomTM(String roomTypeId, String roomType, int perRoom, String keyMoney, int roomQty) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.perRoom = perRoom;
        this.keyMoney = keyMoney;
        this.roomQty = roomQty;
    }

    public int getPerRoom() {
        return perRoom;
    }

    public void setPerRoom(int perRoom) {
        this.perRoom = perRoom;
    }

    public int getRoomQty() {
        return roomQty;
    }

    public void setRoomQty(int roomQty) {
        this.roomQty = roomQty;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "perRoom=" + perRoom +
                ", roomQty=" + roomQty +
                ", keyMoney='" + keyMoney + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
