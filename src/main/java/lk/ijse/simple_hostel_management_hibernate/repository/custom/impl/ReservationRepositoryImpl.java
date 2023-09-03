package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
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
    public int getReservationCount(String roomTypeId) {
        String sql = "SELECT COUNT(r) FROM Reservation r WHERE r.roomTypeId = :room_type_id";
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
       /* String sql = "UPDATE Room r SET r.availableRooms = :available_room_qty WHERE r.roomTypeId= :room_type_id";
        Query query = session.createQuery(sql);
        query.setParameter("available_room_qty", available_rooms);
        query.setParameter("room_type_id", roomTypeId);
        query.executeUpdate();*/

        Room room = session.get(Room.class,roomTypeId);
        System.out.println(room);
        room.setAvailableRooms(available_rooms);
        System.out.println(room);
        session.merge(room);
    }

    @Override
    public Student getStudentbyResId(String resId) {
        String sql = "SELECT s " +
                "FROM Student s " +
                "INNER JOIN Reservation r " +
                "ON s.studentId=r.studentId " +
                "WHERE r.reservationId= :res_id ";
        Query query = session.createQuery(sql);
        query.setParameter("res_id", resId);
        Student student = (Student) query.getSingleResult();
        return student;
    }

    @Override
    public void save(Reservation entity) {
        session.save(entity);
    }

    @Override
    public Reservation get(String s) {
        Reservation reservation =session.get(Reservation.class,s);
        return reservation;
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

}
