package lk.ijse.simple_hostel_management_hibernate.repository;

import lk.ijse.simple_hostel_management_hibernate.repository.custom.impl.RoomRepositoryImpl;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.impl.StudentRepositoryImpl;

public class RepositoryFactory {

    private static  RepositoryFactory repositoryFactory;

    private RepositoryFactory(){

    }

    public static RepositoryFactory getRepositoryFactory(){
        return (null==repositoryFactory) ? repositoryFactory=new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        STUDENT,ROOM,RESERVATION
    }

    public <T extends SuperRepository>T getRepository(RepositoryTypes type){
        switch (type){
            case STUDENT:
                return (T)new StudentRepositoryImpl();
            case ROOM:
                return (T)new RoomRepositoryImpl();
            default:
                return null;
        }
    }
}
