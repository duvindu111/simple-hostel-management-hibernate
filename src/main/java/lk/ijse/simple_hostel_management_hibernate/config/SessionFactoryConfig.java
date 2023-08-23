package lk.ijse.simple_hostel_management_hibernate.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;


    private SessionFactoryConfig() {
        Properties properties = new Properties();
        try  {
            properties.load(SessionFactoryConfig.class.getResourceAsStream("/hibernate.properties"));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("error encountered while loading the property file");
        }
        sessionFactory=new Configuration().setProperties(properties).buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (null==factoryConfig) ? factoryConfig=new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
