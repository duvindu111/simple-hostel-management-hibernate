package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.QueryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {

    private Session session;

    public QueryRepositoryImpl() {
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<String> loadStudentIds() {
        String sql = "SELECT student.st_id " +
                "FROM student " +
                "LEFT JOIN reservation " +
                "ON student.st_id = reservation.st_id " +
                "WHERE reservation.st_id IS NULL";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        //session.close();
        return list;
    }

    @Override
    public List<CustomProjection> getDetailsOfStudentsWithoutKeyMoney() {
        String hql = "select new lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection(" +
                "S.studentId,S.studentAddress,S.studentDOB,S.studentGender,S.studentName,S.studentContact," +
                "RE.reservationId,RE.reservationDate,RO.roomType,RO.keyMoney ) " +
                "FROM Student AS S " +
                "INNER JOIN Reservation AS RE " +
                "ON S.studentId=RE.reservationPK.studentId " +
                "INNER JOIN Room AS RO " +
                "ON RE.reservationPK.roomTypeId=RO.roomTypeId " +
                "WHERE RE.reservationStatus= :status ";
        Query query = session.createQuery(hql);
        query.setParameter("status", "NOT PAID");
        List<CustomProjection> studentWithoutKeyMoneyList = query.list();
        return studentWithoutKeyMoneyList;
    }

}
