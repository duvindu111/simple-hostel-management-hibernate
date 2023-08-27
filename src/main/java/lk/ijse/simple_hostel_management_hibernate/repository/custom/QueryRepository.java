package lk.ijse.simple_hostel_management_hibernate.repository.custom;

import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.SuperRepository;
import org.hibernate.Session;

import java.util.List;

public interface QueryRepository extends SuperRepository {

    public void setSession(Session session);

    List<CustomProjection> getDetailsOfStudentsWithoutKeyMoney();
}
