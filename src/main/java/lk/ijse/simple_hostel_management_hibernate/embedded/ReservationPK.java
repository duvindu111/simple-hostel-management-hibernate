package lk.ijse.simple_hostel_management_hibernate.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReservationPK implements Serializable {

    @Column(name = "st_id", nullable = false)
    private String studentId;
    @Column(name = "room_type_id", nullable = false)
    private String roomTypeId;

    public ReservationPK() {
    }

    public ReservationPK(String studentId, String roomTypeId) {
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "ReservationPK{" +
                "studentId='" + studentId + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                '}';
    }
}
