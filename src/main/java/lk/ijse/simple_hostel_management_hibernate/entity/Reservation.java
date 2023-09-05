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
    @Column(name = "status", nullable = false)
    private String reservationStatus;

    @ManyToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id", unique = true, nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id", nullable = false)
    private Room room;

    public Reservation() {
    }

    public Reservation(String reservationId, LocalDate reservationDate, String reservationStatus, Student student, Room room) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
        this.student = student;
        this.room = room;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
                ", reservationStatus='" + reservationStatus + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
}
