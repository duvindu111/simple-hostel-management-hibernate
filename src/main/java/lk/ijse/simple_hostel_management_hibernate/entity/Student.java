package lk.ijse.simple_hostel_management_hibernate.entity;

import jakarta.persistence.*;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
    @Column(name = "st_contact", nullable = false, unique = true)
    private String studentContact;
    @Column(name = "st_dob", nullable = false)
    private LocalDate studentDOB;
    @Column(name = "st_gender", nullable = false)
    private String studentGender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "student")
    private List<Reservation> reservations = new ArrayList<>();

    public Student() {
    }

    public Student(String studentId, String studentName, String studentAddress, String studentContact, LocalDate studentDOB, String studentGender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentContact = studentContact;
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

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
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
                ", studentContact='" + studentContact + '\'' +
                ", studentDOB=" + studentDOB +
                ", studentGender='" + studentGender + '\'' +
                '}';
    }

    public StudentDTO toDto() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(this.studentId);
        studentDTO.setAddress(this.studentAddress);
        studentDTO.setDob(this.studentDOB);
        studentDTO.setGender(this.studentGender);
        studentDTO.setName(this.studentName);
        studentDTO.setStudentContact(this.studentContact);
        return studentDTO;
    }
}
