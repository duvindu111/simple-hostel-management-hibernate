package lk.ijse.simple_hostel_management_hibernate.view.tm;

import java.time.LocalDate;

public class StudentTM {
    private String id;
    private String address;
    private LocalDate dob;
    private String gender;
    private String name;
    private String contact;

    public StudentTM() {
    }

    public StudentTM(String id, String address, LocalDate dob, String gender, String name, String contact) {
        this.id = id;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.name = name;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
