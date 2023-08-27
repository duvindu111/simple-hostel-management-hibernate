package lk.ijse.simple_hostel_management_hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface KeyMoneyStudentService extends SuperService {
    ObservableList<CustomProjection> getDetailsToTableView();
}
