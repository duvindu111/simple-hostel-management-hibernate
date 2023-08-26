package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.ReservationRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Session session;

    public ReservationRepositoryImpl() {
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
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
    public int getReservationCount(String roomTypeId) {
        String sql = "SELECT COUNT(r) FROM Reservation r WHERE r.reservationPK.roomTypeId = :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_type_id", roomTypeId);
        long count = (long) query.getSingleResult();
        // session.close();
        return (int) count;
    }

    @Override
    public List getMaxPersonsPerRoom(String roomTypeId) {
        String sql = "SELECT r.perRoom,r.roomQuantity FROM Room r WHERE r.roomTypeId = :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_type_id", roomTypeId);
        List list = (List) query.list();
        return list;
    }

    @Override
    public void updateAvailableRooms(int available_rooms, String roomTypeId) {
        String sql = "UPDATE Room r SET r.availableRooms = :available_room_qty WHERE r.roomTypeId= :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("available_room_qty", available_rooms);
        query.setParameter("room_type_id", roomTypeId);
        query.executeUpdate();
    }

    @Override
    public void save(Reservation entity) {
        session.save(entity);
    }

    @Override
    public Reservation get(String s) {
        return null;
    }

    @Override
    public void update(Reservation entity) {
        session.update(entity);
    }

    @Override
    public void delete(Reservation entity) {
        session.delete(entity);
    }

    @Override
    public List<Reservation> getDetailsToTableView() {
        String hql = "SELECT C FROM Reservation AS C";
        Query query = session.createQuery(hql);
        List<Reservation> reservationList = query.list();
        return reservationList;
    }

    @Override
    public List<String> loadStudentIds() {
        String sql = "SELECT st_id FROM student";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        //session.close();
        return list;
    }
}
