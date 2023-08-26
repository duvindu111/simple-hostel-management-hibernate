package lk.ijse.simple_hostel_management_hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.dto.ReservationDTO;
import lk.ijse.simple_hostel_management_hibernate.service.SuperService;

import java.util.List;

public interface ReservationService extends SuperService {

    List<String> loadStudentIds();

    List<String> loadRoomTypeIds();

    ObservableList<ReservationDTO> getDetailsToTableView();

    List<Integer> saveReservation(ReservationDTO reservationDTO);

    boolean updateReservation(ReservationDTO reservationDTO);

    List<Integer> deleteReservation(ReservationDTO reservationDTO);
}
