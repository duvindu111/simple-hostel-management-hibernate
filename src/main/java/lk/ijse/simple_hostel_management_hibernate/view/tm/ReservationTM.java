package lk.ijse.simple_hostel_management_hibernate.view.tm;

import java.time.LocalDate;

public class ReservationTM {

    private String reservationId;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String paymentStatus;

    public ReservationTM() {
    }

    public ReservationTM(String reservationId, LocalDate date, String studentId, String roomTypeId, String paymentStatus) {
        this.reservationId = reservationId;
        this.date = date;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
        this.paymentStatus = paymentStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "ReservationTM{" +
                "reservationId='" + reservationId + '\'' +
                ", date=" + date +
                ", studentId='" + studentId + '\'' +
                ", roomTypeId='" + roomTypeId + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
