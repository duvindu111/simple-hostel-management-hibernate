package lk.ijse.simple_hostel_management_hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "res_id", unique = true, nullable = false)
    private String reservationId;
    @Column(name = "date", nullable = false)
    private LocalDate reservationDate;
    @Column(name = "st_id", unique = true, nullable = false)
    private String studentId;
    @Column(name = "room_type_id", nullable = false)
    private String roomTypeId;
    @Column(name = "status", nullable = false)
    private String reservationStatus;

    @ManyToOne
    @JoinColumn(name = "st_id",
            referencedColumnName = "st_id",
            insertable = false,
            updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id",
            referencedColumnName = "room_type_id",
            insertable = false,
            updatable = false)
    private Room room;

    public Reservation() {
    }

    public Reservation(String reservationId, LocalDate reservationDate, String studentId, String roomTypeId, String reservationStatus) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
        this.reservationStatus = reservationStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
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

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", reservationDate=" + reservationDate +
                ", studentId='" + studentId + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                '}';
    }
}
