package lk.ijse.simple_hostel_management_hibernate.service;

import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.SuperRepository;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.impl.StudentRepositoryImpl;
import lk.ijse.simple_hostel_management_hibernate.service.custom.impl.KeyMoneyStudentServiceImpl;
import lk.ijse.simple_hostel_management_hibernate.service.custom.impl.ReservationServiceImpl;
import lk.ijse.simple_hostel_management_hibernate.service.custom.impl.RoomServiceImpl;
import lk.ijse.simple_hostel_management_hibernate.service.custom.impl.StudentServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }

    public static ServiceFactory getServiceFactory(){
        return (null==serviceFactory) ? serviceFactory=new ServiceFactory() : serviceFactory;
    }

    public enum ServiceTypes{
        STUDENT,ROOM,RESERVATION,KEY_MONEY_STUDENT
    }

    public <T extends SuperService>T getservice(ServiceTypes type){
        switch (type){
            case STUDENT:
                return (T)new StudentServiceImpl();
            case ROOM:
                return (T)new RoomServiceImpl();
            case RESERVATION:
                return (T)new ReservationServiceImpl();
            case KEY_MONEY_STUDENT:
                return (T)new KeyMoneyStudentServiceImpl();
            default:
                return null;
        }
    }

}
