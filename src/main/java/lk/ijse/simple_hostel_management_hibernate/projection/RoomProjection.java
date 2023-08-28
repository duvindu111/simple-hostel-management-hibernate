package lk.ijse.simple_hostel_management_hibernate.projection;

public class RoomProjection {

    private String roomTypeId;
    private String roomType;
    private int availableRooms;
    private String keyMoney;
    private int maxPersons;

    public RoomProjection() {
    }

    public RoomProjection(String roomTypeId, String roomType, int availableRooms, String keyMoney, int maxPersons) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.availableRooms = availableRooms;
        this.keyMoney = keyMoney;
        this.maxPersons = maxPersons;
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

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    @Override
    public String toString() {
        return "RoomProjection{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", availableRooms=" + availableRooms +
                ", keyMoney='" + keyMoney + '\'' +
                ", maxPersons=" + maxPersons +
                '}';
    }
}
