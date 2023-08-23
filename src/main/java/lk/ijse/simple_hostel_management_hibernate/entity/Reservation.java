package lk.ijse.simple_hostel_management_hibernate.entity;

import jakarta.persistence.*;
import lk.ijse.simple_hostel_management_hibernate.embedded.ReservationPK;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Column(name = "res_id", nullable = false, unique = true)
    private String reservationId;
    @Column(name = "date", nullable = false)
    private LocalDate reservationDate;
    @EmbeddedId
    private ReservationPK reservationPK;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private KeyMoneyStatus reservationStatus;

    enum KeyMoneyStatus {
        PAID,
        NOT_PAID
    }

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

    public Reservation(String reservationId, LocalDate reservationDate, ReservationPK reservationPK,
                       KeyMoneyStatus reservationStatus) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.reservationPK = reservationPK;
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

    public ReservationPK getReservationPK() {
        return reservationPK;
    }

    public void setReservationPK(ReservationPK reservationPK) {
        this.reservationPK = reservationPK;
    }

    public KeyMoneyStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(KeyMoneyStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", reservationDate=" + reservationDate +
                ", reservationPK=" + reservationPK +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
