package lk.ijse.simple_hostel_management_hibernate.dto;

import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;

import java.time.LocalDate;

public class ReservationDTO {

    private LocalDate date;
    private String reservationId;
    private String roomTypeId;
    private String studentId;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(LocalDate date, String reservationId, String roomTypeId, String studentId, String status) {
        this.date = date;
        this.reservationId = reservationId;
        this.roomTypeId = roomTypeId;
        this.studentId = studentId;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "date= "+date+" | reservationId= "+reservationId+" | roomTypeId= "+roomTypeId+" | studentId= "
                +studentId+" | status= "+status;
    }

    public Reservation toEntity(){
        Reservation reservation = new Reservation();
        reservation.setReservationDate(this.date);
        reservation.setReservationId(this.reservationId);

        Student student = new Student();
        student.setStudentId(this.studentId);
        reservation.setStudent(student);

        Room room = new Room();
        room.setRoomTypeId(this.roomTypeId);
        reservation.setRoom(room);

        reservation.setReservationStatus(this.status);
        return reservation;
    }
}

