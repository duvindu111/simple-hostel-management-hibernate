package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.ReservationDTO;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Reservation;
import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.QueryRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.ReservationRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.RoomRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.ReservationService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.RESERVATION);
    RoomRepository roomRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ROOM);
    QueryRepository queryRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.QUERY);

    @Override
    public List<String> loadStudentIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            queryRepository.setSession(session);
            List<String> ids = queryRepository.loadStudentIds();
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
            roomRepository.setSession(session);
            List<String> ids = roomRepository.loadRoomTypeIds();
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
                                reservation.getRoom().getRoomTypeId(),
                                reservation.getStudent().getStudentId(),
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
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.save(reservationDTO.toEntity());
            updateAvailableRooms(reservationDTO);

            transaction.commit();
            session.close();
            return true;
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            transaction.rollback();
            session.close();
            System.out.println("reservation saving process failed");
            String errorMessage =  e.getMessage();
            int startIndex = errorMessage.indexOf("[") +1;
            int endIndex = errorMessage.indexOf("' ") +1;  // Find the index of the first ']'
            String extractedPart = errorMessage.substring(startIndex, endIndex).trim();
            AlertController.errormessage(extractedPart);
            return false;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("reservation saving process failed");
            e.printStackTrace();
            System.out.println(e);
            return false;
        }

    }

    public void updateAvailableRooms(ReservationDTO reservationDTO){
        String roomTypeId = reservationDTO.toEntity().getRoom().getRoomTypeId();

            int count = reservationRepository.getReservationCount(roomTypeId);

            List<Object[]> list = reservationRepository.getMaxPersonsPerRoom(roomTypeId);
            Object[] result = list.get(0);
            int perRoom = (Integer) result[0];
            int roomQuantity = (Integer) result[1];

            int unavailable_rooms = count / perRoom;
            int available_rooms = roomQuantity - unavailable_rooms;

            reservationRepository.updateAvailableRooms(available_rooms, roomTypeId);

    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.update(reservationDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch(jakarta.persistence.OptimisticLockException e){
            AlertController.warningmessage("Cannot update room type id or student id.\n" +
                    "delete the current reservation first and add new reservation with updated details.");
            return false;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("reservation updating process failed");
            System.out.println(e);

            String errorMessage =  e.getMessage();
            int startIndex = errorMessage.indexOf("[") +1;
            int endIndex = errorMessage.indexOf("' ") +1;  // Find the index of the first ']'
            String extractedPart = errorMessage.substring(startIndex, endIndex).trim();
            AlertController.errormessage(extractedPart);
            return false;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            reservationRepository.delete(reservationDTO.toEntity());

            updateAvailableRooms(reservationDTO);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            System.out.println("reservation deletion process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Integer> getAvailableRoomsCount(String roomTypeId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            int count = reservationRepository.getReservationCount(roomTypeId);

            List<Object[]> list = reservationRepository.getMaxPersonsPerRoom(roomTypeId);
            Object[] result = list.get(0);
            int perRoom = (Integer) result[0];
            int roomQuantity = (Integer) result[1];

            int unavailable_rooms = count / perRoom;
            int perInOtherAvailableRoom = count % perRoom;
            int available_rooms = roomQuantity - unavailable_rooms;

            List<Integer> returnList = new ArrayList<>();
            returnList.add(available_rooms);
            returnList.add(perInOtherAvailableRoom);
            return returnList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getAvailableRoomsCount failed");
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentDTO getStudentbyResId(String resId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            Student student = reservationRepository.getStudentbyResId(resId);
            return new StudentDTO(
                student.getStudentId(),
                student.getStudentAddress(),
                student.getStudentDOB(),
                student.getStudentGender(),
                student.getStudentName(),
                student.getStudentContact()
            );
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getAvailableRoomsCount failed");
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation getReseravationAvailabilty(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            reservationRepository.setSession(session);
            Reservation reservation=reservationRepository.get(reservationDTO.getReservationId());
            transaction.commit();
            session.close();
            return reservation;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getReseravationAvailabilty failed");
            System.out.println(e);
            return null;
        }
    }
}
