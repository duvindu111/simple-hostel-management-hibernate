package lk.ijse.simple_hostel_management_hibernate.repository.custom;

import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,String> {

    public void setSession(Session session);

    int getReservationCount(String roomTypeId);

    List getMaxPersonsPerRoom(String roomTypeId);

    void updateAvailableRooms(int available_rooms, String roomTypeId);

    Student getStudentbyResId(String resId);
}
