package lk.ijse.simple_hostel_management_hibernate.dto;

import lk.ijse.simple_hostel_management_hibernate.entity.Room;

public class RoomDTO {

    private int perRoom;
    private int roomQty;
    private String keyMoney;
    private String roomTypeId;
    private String roomType;

    public RoomDTO() {
    }

    public RoomDTO(int perRoom, int roomQty, String keyMoney, String roomTypeId, String roomType) {
        this.perRoom = perRoom;
        this.roomQty = roomQty;
        this.keyMoney = keyMoney;
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
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
        return "perRoom= "+perRoom+" roomQty= "+roomQty+" keyMoney= "+keyMoney+" roomTypeId= "+roomTypeId+" roomType= "+roomType;
    }

    public Room toEntity(){
        Room room = new Room();
        room.setPerRoom(this.perRoom);
        room.setRoomQuantity(this.roomQty);
        room.setKeyMoney(this.keyMoney);
        room.setRoomTypeId(this.roomTypeId);
        room.setRoomType(this.roomType);
        return room;
    }
}
