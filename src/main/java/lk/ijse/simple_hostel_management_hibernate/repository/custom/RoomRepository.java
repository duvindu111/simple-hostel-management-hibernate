package lk.ijse.simple_hostel_management_hibernate.repository.custom;

import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.projection.RoomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,String> {

    public void setSession(Session session);

    List<RoomProjection> getDetailsForRoomAvailabily();

    List<String> loadRoomTypeIds();

    void updateAvailableRooms(int available_rooms, String roomTypeId);
}
