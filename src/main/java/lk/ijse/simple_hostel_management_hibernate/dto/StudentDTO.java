package lk.ijse.simple_hostel_management_hibernate.dto;

import lk.ijse.simple_hostel_management_hibernate.embedded.StudentContact;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {

    private String id;
    private String address;
    private LocalDate dob;
    private String gender;
    private String name;
    private String studentContact;

    public StudentDTO() {
    }

    public StudentDTO(String id, String address, LocalDate dob, String gender, String name, String studentContact) {
        this.id = id;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.name = name;
        this.studentContact = studentContact;
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", studentContact='" + studentContact + '\'' +
                '}';
    }

    public Student toEntity(){
        Student student = new Student();
        student.setStudentId(this.id);
        student.setStudentName(this.name);
        student.setStudentDOB(this.dob);
        student.setStudentAddress(this.address);
        student.setStudentGender(this.gender);
        student.setStudentContact(this.studentContact);
        return student;
    }
}
