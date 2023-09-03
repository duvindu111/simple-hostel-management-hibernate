package lk.ijse.simple_hostel_management_hibernate.repository.custom.impl;

import lk.ijse.simple_hostel_management_hibernate.entity.User;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    public UserRepositoryImpl(){}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String checkUsernameAvailability(String username) {
        String sql = "SELECT username FROM user WHERE username= :un ";
        Query query = session.createNativeQuery(sql);
        query.setParameter("un", username);
        String name = (String) query.getSingleResult();
        return name;
    }

    @Override
    public String checkPassword(String username) {
        String sql = "SELECT password FROM user WHERE username= :un ";
        Query query = session.createNativeQuery(sql);
        query.setParameter("un", username);
        String pass = (String) query.getSingleResult();
        return pass;
    }

    @Override
    public String getAdminPin() {
        String sql = "SELECT password FROM user WHERE username='admin'";
        Query query = session.createNativeQuery(sql);
        String pin = (String) query.getSingleResult();
        return pin;
    }

    @Override
    public void save(User entity) {
        session.persist(entity);
    }

    @Override
    public User get(String s) {
        //not yet implemented
        return null;
    }

    @Override
    public void update(User entity) {
        session.merge(entity);
    }

    @Override
    public void delete(User entity) {
        session.remove(entity);
    }

    @Override
    public List<User> getDetailsToTableView() {
        //not yet implemented
        return null;
    }
}
