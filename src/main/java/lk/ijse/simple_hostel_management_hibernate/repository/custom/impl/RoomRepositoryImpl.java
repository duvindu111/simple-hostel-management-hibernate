package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.projection.RoomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.RoomRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private Session session;

    public RoomRepositoryImpl(){}

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<RoomProjection> getDetailsForRoomAvailabily() {
        String hql = "select new lk.ijse.simple_hostel_management_hibernate.projection.RoomProjection(" +
                "R.roomTypeId,R.roomType,R.availableRooms,R.keyMoney,R.perRoom) " +
                "FROM Room AS R ";
        Query query = session.createQuery(hql);
        List<RoomProjection> roomAvailabilityList = query.list();
        return roomAvailabilityList;
    }

    @Override
    public List<String> loadRoomTypeIds() {
        String sql = "SELECT room_type_id FROM room";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        //session.close();
        return list;
    }

    @Override
    public void save(Room entity) {
        session.save(entity);
    }

    @Override
    public Room get(String s) {
        Room room =session.get(Room.class,s);
        return room;
    }

    @Override
    public void update(Room entity) {
        session.update(entity);
    }

    @Override
    public void delete(Room entity) {
        session.delete(entity);
    }

    @Override
    public List<Room> getDetailsToTableView() {
        String hql = "SELECT C FROM Room AS C";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();
        return roomList;
    }

}
