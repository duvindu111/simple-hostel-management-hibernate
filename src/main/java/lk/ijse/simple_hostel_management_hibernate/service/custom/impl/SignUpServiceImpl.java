package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.UserRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.SignUpService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SignUpServiceImpl implements SignUpService {

    UserRepository userRepository = RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

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
            System.out.println("getting admin pin failed");
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveNewUser(UserDTO userDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.save(userDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("new user saving process failed");
            System.out.println(e);
            return false;
        }
    }
}
