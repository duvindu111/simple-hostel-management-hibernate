package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.controller.util.ValidateFields;
import lk.ijse.simple_hostel_management_hibernate.dto.ReservationDTO;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.ReservationService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

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
    private JFXButton btnAddNew;

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
    private Group grpGetStByResId;

    @FXML
    private ImageView icnClose;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblDateOfBirth;

    @FXML
    private Label lblExtraPersons;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblRoomAvailability;

    @FXML
    private Label lblStAddress;

    @FXML
    private Label lblStId;

    @FXML
    private Label lblStName;

    @FXML
    private TextField txtEnterResId;

    @FXML
    private JFXButton btnKeyMoney;

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

        cmbPaymentStatus.getItems().addAll("PAID", "NOT PAID");
        loadRoomTypeIds();

        setDataToTableView();
        setCellValueFactory();
    }

    public void loadStIds() {
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

    public void loadRoomTypeIds() {
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

        txtResId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");

        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        if (noEmptyFields) {
            ReservationDTO reservationDTO = getDetailsInTextFields();
            boolean success = reservationService.deleteReservation(reservationDTO);
            if (success) {
                AlertController.confirmmessage("reservation details deleted successfully");
                setDataToTableView();
                clearTxtFields();
            } else {
                AlertController.errormessage("reservation details deletion process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        if (noEmptyFields) {
            ReservationDTO reservationDTO = getDetailsInTextFields();
            boolean success = reservationService.updateReservation(reservationDTO);
            if (success) {
                AlertController.confirmmessage("reservation details updated successfully");
                setDataToTableView();
                clearTxtFields();
            } else {
                AlertController.errormessage("reservation details updating process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        if (noEmptyFields) {
            ReservationDTO reservationDTO = getDetailsInTextFields();
            boolean success = reservationService.saveReservation(reservationDTO);
            if (success) {
                AlertController.confirmmessage("reservation details saved successfully");
                setDataToTableView();
                clearTxtFields();
            } else {
                AlertController.errormessage("reservation details saving process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public ReservationDTO getDetailsInTextFields() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDate(dpDate.getValue());
        reservationDTO.setReservationId(txtResId.getText());
        reservationDTO.setRoomTypeId(cmbRoomTypeId.getValue());
        reservationDTO.setStudentId(cmbStudentId.getValue());
        reservationDTO.setStatus(cmbPaymentStatus.getValue());
        return reservationDTO;
    }

    void clearTxtFields() {
        dpDate.setValue(null);
        txtResId.setText("");
        cmbRoomTypeId.setValue("");
        cmbStudentId.setValue("");
        cmbPaymentStatus.setValue("");
    }

    private void setDataToTableView() {
        ObservableList<ReservationDTO> reservationDtoList = reservationService.getDetailsToTableView();
        ObservableList<ReservationTM> reservationTmList = FXCollections.observableArrayList();
        for (ReservationDTO dto : reservationDtoList) {
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

    public void setCellValueFactory() {
        colResId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
    }

    public void cmbRoomTypeIdOnAction(ActionEvent actionEvent) {
        String roomTypeId = cmbRoomTypeId.getValue();

        if (!roomTypeId.isEmpty()) {
            List<Integer> list = reservationService.getAvailableRoomsCount(roomTypeId);

            if (!list.isEmpty()) {
                System.out.println(list.get(0));
                System.out.println(list.get(1));
                int availableRooms = (Integer) list.get(0);
                int perInOtherRoom = (Integer) list.get(1);

                lblRoomAvailability.setText(availableRooms + " rooms available");
                lblRoomAvailability.setVisible(true);

                if (availableRooms != 0) {
                    if (availableRooms == 1) {
                        lblRoomAvailability.setText("1 room available");
                        lblRoomAvailability.setVisible(true);
                        lblRoomAvailability.setStyle("-fx-text-fill: white");
                        btnSave.setDisable(false);
                    } else {
                        lblRoomAvailability.setText(availableRooms + " rooms available");
                        lblRoomAvailability.setVisible(true);
                        lblRoomAvailability.setStyle("-fx-text-fill: white");
                        btnSave.setDisable(false);
                    }
                } else {
                    lblRoomAvailability.setText("*no rooms available*");
                    lblRoomAvailability.setVisible(true);
                    lblRoomAvailability.setStyle("-fx-text-fill: red");
                    btnSave.setDisable(true);
                }

                if (perInOtherRoom != 0) {
                    if (perInOtherRoom == 1) {
                        lblExtraPersons.setText("there is a room with 1 person already in it");
                        lblExtraPersons.setVisible(true);
                    } else {
                        lblExtraPersons.setText("there is a room with " + perInOtherRoom + " persons already in it");
                        lblExtraPersons.setVisible(true);
                    }
                } else {
                    lblExtraPersons.setVisible(false);
                }
            }
        } else {
            lblRoomAvailability.setVisible(false);
            lblExtraPersons.setVisible(false);
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/popup_student_form.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }

    public void icnSearchOnMouseClicked(MouseEvent mouseEvent) {
        StudentDTO studentDTO = reservationService.getStudentbyResId(txtEnterResId.getText());

        lblStId.setText(studentDTO.getId());
        lblStName.setText(studentDTO.getName());
        lblStAddress.setText(studentDTO.getAddress());
        lblContactNo.setText(studentDTO.getStudentContact());
        lblDateOfBirth.setText(studentDTO.getDob().toString());
        lblGender.setText(studentDTO.getGender());

        grpGetStByResId.setVisible(true);
    }

    public void icnCloseOnMouseClicked(MouseEvent mouseEvent) {
        lblStId.setText("");
        lblStName.setText("");
        lblStAddress.setText("");
        lblContactNo.setText("");
        lblDateOfBirth.setText("");
        lblGender.setText("");
        grpGetStByResId.setVisible(false);
    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/key_money_st_form.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();
    }

    public boolean noEmptyValuesInTextFields() {
        String resId = txtResId.getText();
        LocalDate resDate = dpDate.getValue();
        String stId = cmbStudentId.getValue();
        String roomTypeId = cmbRoomTypeId.getValue();
        String status = cmbPaymentStatus.getValue();
        if (!resId.isEmpty() && resDate != null && stId != null && roomTypeId != null && status != null && !stId.isEmpty()
                && !roomTypeId.isEmpty() && !status.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void btnEnable() {
        if (ValidateFields.rerservationIdCheck(txtResId.getText())) {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void txtResIdOnMouseKeytyped(KeyEvent keyEvent) {
        String txt = txtResId.getText();
        if (ValidateFields.rerservationIdCheck(txt)) {
            txtResId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtResId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTxtFields();
    }

    public void cmbStudentIdOnMouseClicked(MouseEvent mouseEvent) {
        loadStIds();
    }
}
