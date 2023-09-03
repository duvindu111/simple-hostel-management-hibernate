package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.RoomDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.RoomRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.RoomService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository = RepositoryFactory.getRepositoryFactory().getRepository
            (RepositoryFactory.RepositoryTypes.ROOM);

    @Override
    public boolean saveRoomType(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room entity = roomDTO.toEntity();
            entity.setAvailableRooms(roomDTO.getRoomQty());
            roomRepository.save(entity);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("room type saving process failed");
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
    public boolean deleteRoomType(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            roomRepository.delete(roomDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("room type deleting process failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Room getRoomAvailabilty(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room room=roomRepository.get(roomDTO.getRoomTypeId());
            transaction.commit();
            session.close();
            return room;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getRoomAvailabilty failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ObservableList<RoomDTO> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            List<Room> roomList = roomRepository.getDetailsToTableView();
            ObservableList<RoomDTO> roomObList = FXCollections.observableArrayList();
            for (Room room: roomList){
                roomObList.add(
                        new RoomDTO(
                                room.getPerRoom(),
                                room.getRoomQuantity(),
                                room.getKeyMoney(),
                                room.getRoomTypeId(),
                                room.getRoomType()
                        )
                );
            }
            transaction.commit();
            session.close();
            return roomObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean updateRoomType(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            roomRepository.update(roomDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch(jakarta.persistence.OptimisticLockException e){
            AlertController.errormessage("Room Type ID:"+roomDTO.getRoomTypeId()+" not " +
                    "found.\nAdd the details of the room to the system first");
            return false;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("room type updating process failed");
            System.out.println(e);

            String errorMessage =  e.getMessage();
            int startIndex = errorMessage.indexOf("[") +1;
            int endIndex = errorMessage.indexOf("' ") +1;  // Find the index of the first ']'
            String extractedPart = errorMessage.substring(startIndex, endIndex).trim();
            AlertController.errormessage(extractedPart);
            return false;
        }
    }
}
