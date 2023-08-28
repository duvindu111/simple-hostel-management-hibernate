package lk.ijse.simple_hostel_management_hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface HomeService extends SuperService {
    ObservableList<CustomProjection> getDetailsToTableView();

    String checkCurrentPass(String username);

    boolean changePassword(UserDTO userDto);
}
