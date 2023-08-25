package lk.ijse.simple_hostel_management_hibernate.repository;

import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import org.hibernate.Session;

public interface StudentRepository extends CrudRepository<Student,String> {

    public void setSession(Session session);
}
