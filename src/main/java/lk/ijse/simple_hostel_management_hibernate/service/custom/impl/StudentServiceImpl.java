package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.StudentRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.impl.StudentRepositoryImpl;
import lk.ijse.simple_hostel_management_hibernate.service.custom.StudentService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository = RepositoryFactory.getRepositoryFactory()
            .getRepository(RepositoryFactory.RepositoryTypes.STUDENT);

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

            String errorMessage =  e.getMessage();
            int startIndex = errorMessage.indexOf("[") +1;
            int endIndex = errorMessage.indexOf("' ") +1;  // Find the index of the first ']'
            String extractedPart = errorMessage.substring(startIndex, endIndex).trim();
            AlertController.errormessage(extractedPart);
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
        }catch(jakarta.persistence.OptimisticLockException e){
            AlertController.errormessage("Student details of the student with ID:"+studentDto.getId()+" not " +
                    "found.\nAdd the details of the student to the system first");
            return false;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("student updating process failed");
            System.out.println(e);
            e.printStackTrace();

            String errorMessage =  e.getMessage();
            int startIndex = errorMessage.indexOf("[") +1;
            int endIndex = errorMessage.indexOf("' ") +1;  // Find the index of the first ']'
            String extractedPart = errorMessage.substring(startIndex, endIndex).trim();
            AlertController.errormessage(extractedPart);
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
        }catch(org.hibernate.exception.ConstraintViolationException e){
            transaction.rollback();
            session.close();
            AlertController.errormessage("There is a room reservation done using this student id");
            System.out.println("student deleting process failed");
            System.out.println(e);
            return false;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("student deleting process failed");
            System.out.println(e);
            return false;
        }
    }


}
