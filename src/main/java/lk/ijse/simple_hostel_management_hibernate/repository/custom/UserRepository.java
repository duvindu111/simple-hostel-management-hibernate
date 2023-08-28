package lk.ijse.simple_hostel_management_hibernate.repository.custom;

import lk.ijse.simple_hostel_management_hibernate.entity.User;
import lk.ijse.simple_hostel_management_hibernate.repository.CrudRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.SuperRepository;
import org.hibernate.Session;

public interface UserRepository extends CrudRepository<User,String> {
    String getAdminPin();

    public void setSession(Session session);

    String checkUsernameAvailability(String username);
}
