package lk.ijse.simple_hostel_management_hibernate.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_type_id")
    private String roomTypeId;
    @Column(name = "type", nullable = false)
    private String roomType;
    @Column(name = "key_money", nullable = false)
    private String keyMoney;
    @Column(name = "qty", nullable = false)
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "room")
    private List<Reservation> reservations = new ArrayList<>();

    public Room() {
    }

    public Room(String roomTypeId, String roomType, String keyMoney, int quantity) {
        this.roomTypeId = roomTypeId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.quantity = quantity;
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

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
