package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.ReservationDTO;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.ReservationService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.ReservationTM;
import lk.ijse.simple_hostel_management_hibernate.view.tm.RoomTM;

public class ReservationFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbPaymentStatus;

    @FXML
    private ComboBox<String> cmbRoomTypeId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private AnchorPane reservationFormAncPane;

    @FXML
    private TableView<ReservationTM> tableReservation;

    @FXML
    private TextField txtResId;

    @FXML
    private Label lblExtraPersons;

    @FXML
    private Label lblRoomAvailability;

    ReservationService reservationService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.RESERVATION);

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert dpDate != null : "fx:id=\"dpDate\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert txtResId != null : "fx:id=\"txtResId\" was not injected: check your FXML file 'reservation_form.fxml'.";

        Image image = new Image("assets/images/back_icon.png");
        ImageView imageView = new ImageView(image);
        btnBack.setGraphic(imageView);

        cmbPaymentStatus.getItems().addAll("PAID","NOT PAID");

        loadStIds();
        loadRoomTypeIds();

        setDataToTableView();
        setCellValueFactory();
    }

    public void loadStIds(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();

            List<String> ids = reservationService.loadStudentIds();
            for (String id : ids) {
                obList.add(id);
            }
            cmbStudentId.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("loading ids to cmbStudentId failed");
        }
    }

    public void loadRoomTypeIds(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();

            List<String> ids = reservationService.loadRoomTypeIds();
            for (String id : ids) {
                obList.add(id);
            }
            cmbRoomTypeId.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("loading ids to cmbStudentId failed");
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        reservationFormAncPane.getChildren().clear();
        reservationFormAncPane.getChildren().add(load);
    }

    public void tableReservationOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tableReservation.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<ReservationTM, ?>> columns = tableReservation.getColumns();

        txtResId.setText(columns.get(0).getCellData(row).toString());
        dpDate.setValue((LocalDate) columns.get(1).getCellData(row));
        cmbStudentId.setValue(columns.get(2).getCellData(row).toString());
        cmbRoomTypeId.setValue(columns.get(3).getCellData(row).toString());
        cmbPaymentStatus.setValue(columns.get(4).getCellData(row).toString());
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ReservationDTO reservationDTO = getDetailsInTextFields();
        boolean success = reservationService.deleteReservation(reservationDTO);
        if(success){
            AlertController.confirmmessage("reservation details deleted successfully");
            setDataToTableView();
            clearTxtFields();
        }else{
            AlertController.errormessage("reservation details deletion process unsuccessful");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ReservationDTO reservationDTO = getDetailsInTextFields();
        boolean success = reservationService.updateReservation(reservationDTO);
        if(success){
            AlertController.confirmmessage("reservation details updated successfully");
            setDataToTableView();
            clearTxtFields();
        }else{
            AlertController.errormessage("reservation details updating process unsuccessful");
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        ReservationDTO reservationDTO = getDetailsInTextFields();
        boolean success = reservationService.saveReservation(reservationDTO);
        if(success){
            AlertController.confirmmessage("reservation details saved successfully");
            setDataToTableView();
            clearTxtFields();
        }else{
            AlertController.errormessage("reservation details saving process unsuccessful");
        }
    }

    public ReservationDTO getDetailsInTextFields(){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDate(dpDate.getValue());
        reservationDTO.setReservationId(txtResId.getText());
        reservationDTO.setRoomTypeId(cmbRoomTypeId.getValue());
        reservationDTO.setStudentId(cmbStudentId.getValue());
        reservationDTO.setStatus(cmbPaymentStatus.getValue());
        return reservationDTO;
    }

    void clearTxtFields(){
        dpDate.setValue(null);
        txtResId.setText("");
        cmbRoomTypeId.setValue("");
        cmbStudentId.setValue("");
        cmbPaymentStatus.setValue("");
    }

    private void setDataToTableView() {
        ObservableList<ReservationDTO> reservationDtoList = reservationService.getDetailsToTableView();
        ObservableList<ReservationTM> reservationTmList = FXCollections.observableArrayList();
        for (ReservationDTO dto : reservationDtoList){
            reservationTmList.add(
                    new ReservationTM(
                            dto.getReservationId(),
                            dto.getDate(),
                            dto.getStudentId(),
                            dto.getRoomTypeId(),
                            dto.getStatus()
                    )
            );
        }
        tableReservation.setItems(reservationTmList);
    }

    public void setCellValueFactory(){
        colResId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
    }

    public void cmbRoomTypeIdOnAction(ActionEvent actionEvent) {
        String roomTypeId = cmbRoomTypeId.getValue();

        if(!roomTypeId.isEmpty()) {
            List<Integer> list = reservationService.getAvailableRoomsCount(roomTypeId);

            if (!list.isEmpty()) {
                System.out.println(list.get(0));
                System.out.println(list.get(1));
                int availableRooms = (Integer) list.get(0);
                int perInOtherRoom = (Integer) list.get(1);

                lblRoomAvailability.setText(availableRooms + " rooms available");
                lblRoomAvailability.setVisible(true);

                lblExtraPersons.setText("there is a room with " + perInOtherRoom + " persons already in it");
                lblExtraPersons.setVisible(true);
            }
        } else {
            lblRoomAvailability.setVisible(false);
            lblExtraPersons.setVisible(false);
        }
    }
}
