package lk.ijse.simple_hostel_management_hibernate.service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.StudentRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.impl.StudentRepositoryImpl;
import lk.ijse.simple_hostel_management_hibernate.service.StudentService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository = new StudentRepositoryImpl();

    public boolean saveStudent(StudentDTO student){
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.save(student.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("student saving process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ObservableList<StudentDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            studentRepository.setSession(session);
            List<Student> studentList = studentRepository.getDetailsToTableView();
            ObservableList<StudentDTO> studentObList = FXCollections.observableArrayList();
            for (Student s: studentList){
                studentObList.add(
                        new StudentDTO(
                                s.getStudentId(),
                                s.getStudentAddress(),
                                s.getStudentDOB(),
                                s.getStudentGender(),
                                s.getStudentName(),
                                s.getStudentContact()
                        )
                );
            }
            transaction.commit();
            session.close();
            return studentObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean updateStudent(StudentDTO studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.update(studentDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("student updating process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            studentRepository.setSession(session);
            studentRepository.delete(studentDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("student deleting process failed");
            System.out.println(e);
            return false;
        }
    }


}
