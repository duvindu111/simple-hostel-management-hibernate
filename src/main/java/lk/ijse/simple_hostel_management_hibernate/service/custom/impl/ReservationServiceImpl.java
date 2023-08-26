package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.dto.ReservationDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.ReservationRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.ReservationService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.RESERVATION);

    @Override
    public List<String> loadStudentIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            List<String> ids = reservationRepository.loadStudentIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("student id loading process failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<String> loadRoomTypeIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            List<String> ids = reservationRepository.loadRoomTypeIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("room type id loading process failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ObservableList<ReservationDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            List<Reservation> reservationList = reservationRepository.getDetailsToTableView();
            ObservableList<ReservationDTO> reservationObList = FXCollections.observableArrayList();
            for (Reservation reservation : reservationList) {
                reservationObList.add(
                        new ReservationDTO(
                                reservation.getReservationDate(),
                                reservation.getReservationId(),
                                reservation.getReservationPK().getRoomTypeId(),
                                reservation.getReservationPK().getStudentId(),
                                reservation.getReservationStatus()
                        )
                );
            }
            transaction.commit();
            session.close();
            return reservationObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.save(reservationDTO.toEntity());
            int count = reservationRepository.getReservationCount(reservationDTO.toEntity());
            System.out.println("count "+count);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            session.close();
            System.out.println("reservation saving process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.update(reservationDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("reservation updating process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.delete(reservationDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            System.out.println("reservation deletion process failed");
            System.out.println(e);
            return false;
        }
    }
}
