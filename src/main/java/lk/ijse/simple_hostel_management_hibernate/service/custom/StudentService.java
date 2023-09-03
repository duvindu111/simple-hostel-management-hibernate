package lk.ijse.simple_hostel_management_hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface StudentService extends SuperService {

    public boolean saveStudent(StudentDTO student);

    ObservableList<StudentDTO> getDetailsToTableView();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(StudentDTO studentDTO);

    Student getStudentAvailabilty(StudentDTO studentDTO);
}
