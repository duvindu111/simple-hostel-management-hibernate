package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.UserRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.LoginService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginServiceImpl implements LoginService {

    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    @Override
    public String getAdminPin() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            String pin = userRepository.getAdminPin();
            transaction.commit();
            session.close();
            return pin;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getting admin pin failed");
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String checkUsernameAvailability(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            String sameUsername = userRepository.checkUsernameAvailability(username);
            transaction.commit();
            session.close();
            return sameUsername;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("username availability checking process failed");
            return null;
        }
    }

    @Override
    public String checkPassword(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            String dbPass = userRepository.checkPassword(username);
            transaction.commit();
            session.close();
            return dbPass;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("checking password using username process failed");
            return null;
        }
    }
}
