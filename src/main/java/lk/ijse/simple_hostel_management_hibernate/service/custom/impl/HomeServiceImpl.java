package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.projection.RoomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.QueryRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.RoomRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.UserRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.HomeService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HomeServiceImpl implements HomeService {

    QueryRepository queryRepository = RepositoryFactory.getRepositoryFactory()
            .getRepository(RepositoryFactory.RepositoryTypes.QUERY);

    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    RoomRepository roomRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ROOM);
    @Override
    public ObservableList<CustomProjection> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            queryRepository.setSession(session);
            List<CustomProjection> customList = queryRepository.getDetailsOfStudentsWithoutKeyMoney();
            ObservableList<CustomProjection> customObList = FXCollections.observableArrayList(customList);

            transaction.commit();
            session.close();
            return customObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsOfStudentsWithoutKeyMoney failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String checkCurrentPass(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            String dbCurrentPass = userRepository.checkPassword(username);
            transaction.commit();
            session.close();
            return dbCurrentPass;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("checkCurrentPass failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean changePassword(UserDTO userDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.update(userDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("changePassword failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ObservableList<RoomProjection> getDetailsToRoomAvaTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            roomRepository.setSession(session);
            List<RoomProjection> customList = roomRepository.getDetailsForRoomAvailabily();
            ObservableList<RoomProjection> customObList = FXCollections.observableArrayList(customList);

            transaction.commit();
            session.close();
            return customObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToRoomAvaTableView failed");
            System.out.println(e);
            return null;
        }
    }
}
