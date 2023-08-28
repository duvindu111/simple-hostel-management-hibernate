package lk.ijse.simple_hostel_management_hibernate.service.custom;

import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface LoginService extends SuperService {

    String getAdminPin();

    String checkUsernameAvailability(String username);

    String checkPassword(String username);
}
