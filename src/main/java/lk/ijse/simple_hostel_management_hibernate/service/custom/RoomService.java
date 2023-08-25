package lk.ijse.simple_hostel_management_hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.RoomDTO;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

public interface RoomService extends SuperService {

    ObservableList<RoomDTO> getDetailsToTableView();

    boolean updateRoomType(RoomDTO roomDTO);

    boolean saveRoomType(RoomDTO roomDTO);

    boolean deleteRoomType(RoomDTO roomDTO);
}
