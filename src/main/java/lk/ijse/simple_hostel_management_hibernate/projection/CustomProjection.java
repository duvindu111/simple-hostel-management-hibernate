package lk.ijse.simple_hostel_management_hibernate.projection;

import java.time.LocalDate;

public class CustomProjection {

    private String id;
    private String address;
    private LocalDate dob;
    private String gender;
    private String name;
    private String studentContact;
    private String res_id;
    private LocalDate date;
    private String room_type;
    private String key_money;

    public CustomProjection() {
    }

    public CustomProjection(String id, String address, LocalDate dob, String gender, String name,
                            String studentContact, String res_id, LocalDate date, String room_type,
                            String key_money) {
        this.id = id;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.name = name;
        this.studentContact = studentContact;
        this.res_id = res_id;
        this.date = date;
        this.room_type = room_type;
        this.key_money = key_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getKey_money() {
        return key_money;
    }

    public void setKey_money(String key_money) {
        this.key_money = key_money;
    }

    @Override
    public String toString() {
        return "CustomProjection{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", studentContact='" + studentContact + '\'' +
                ", res_id='" + res_id + '\'' +
                ", date='" + date + '\'' +
                ", room_type_id='" + room_type + '\'' +
                ", key_money='" + key_money + '\'' +
                '}';
    }
}
