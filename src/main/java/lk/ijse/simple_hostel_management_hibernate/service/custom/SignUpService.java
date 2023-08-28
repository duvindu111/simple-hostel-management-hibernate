package lk.ijse.simple_hostel_management_hibernate.service.custom;

import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.User;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface SignUpService extends SuperService {
    String checkUsernameAvailability(String username);

    boolean saveNewUser(UserDTO userDto);
}
