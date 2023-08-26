package lk.ijse.simple_hostel_management_hibernate.embedded;

import jakarta.persistence.*;

@Embeddable
public class StudentContact {

    private String contactNo;

    public StudentContact() {
    }

    public StudentContact(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "StudentContact{" +
                "contactNo='" + contactNo + '\'' +
                '}';
    }
}
