package lk.ijse.simple_hostel_management_hibernate.entity;

import jakarta.persistence.*;
import lk.ijse.simple_hostel_management_hibernate.embedded.StudentContact;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "st_id")
    private String studentId;
    @Column(name = "st_name", nullable = false)
    private String studentName;
    @Column(name = "st_address", nullable = false, columnDefinition = "Text")
    private String studentAddress;
    @ElementCollection
    @CollectionTable(
            name = "st_contacts",
            joinColumns = @JoinColumn(name = "st_id")
    )
    @Column(name = "st_contacts", nullable = false)
    private List<StudentContact> studentContacts = new ArrayList<>();
    @Column(name = "st_dob", nullable = false)
    private LocalDate studentDOB;
    @Column(name = "st_gender", nullable = false)
    private String studentGender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "student")
    private List<Reservation> reservations = new ArrayList<>();

    public Student() {
    }

    public Student(String studentId, String studentName, String studentAddress,
                   List<StudentContact> studentContacts, LocalDate studentDOB, String studentGender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentContacts = studentContacts;
        this.studentDOB = studentDOB;
        this.studentGender = studentGender;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public List<StudentContact> getStudentContacts() {
        return studentContacts;
    }

    public void setStudentContacts(List<StudentContact> studentContacts) {
        this.studentContacts = studentContacts;
    }

    public LocalDate getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(LocalDate studentDOB) {
        this.studentDOB = studentDOB;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentContacts=" + studentContacts +
                ", studentDOB=" + studentDOB +
                ", studentGender='" + studentGender + '\'' +
                '}';
    }
}
