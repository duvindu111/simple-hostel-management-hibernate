package lk.ijse.simple_hostel_management_hibernate.service;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;

public interface StudentService {

    public boolean saveStudent(StudentDTO student);

    ObservableList<StudentDTO> getDetailsToTableView();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(StudentDTO studentDTO);
}
