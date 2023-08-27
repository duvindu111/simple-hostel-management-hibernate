package lk.ijse.simple_hostel_management_hibernate.repository.custom;

import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,String> {

    public void setSession(Session session);

}
