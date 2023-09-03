package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.controller.util.ValidateFields;
import lk.ijse.simple_hostel_management_hibernate.dto.RoomDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.Room;
import lk.ijse.simple_hostel_management_hibernate.entity.Student;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.RoomService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.RoomTM;

import java.io.IOException;

public class RoomFormController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colPersonsQty;

    @FXML
    private TableColumn<?, ?> colRoomQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane roomFormAncPane;

    @FXML
    private TableView<RoomTM> tableRoom;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtPersonQty;

    @FXML
    private TextField txtRoomQty;

    @FXML
    private TextField txtType;

    RoomService roomService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.ROOM);

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colKeyMoney != null : "fx:id=\"colKeyMoney\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colType != null : "fx:id=\"colType\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtKeyMoney != null : "fx:id=\"txtKeyMoney\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'room_form.fxml'.";

        Image image = new Image("assets/images/back_icon.png");
        ImageView imageView = new ImageView(image);
        btnBack.setGraphic(imageView);

        setDataToTableView();
        setCellValueFactory();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        RoomDTO roomDTO = getDetailsInTextFields();
        if (noEmptyFields) {
            Room room = roomService.getRoomAvailabilty(roomDTO);
            if (room != null) {
                boolean result = AlertController.okconfirmmessage("Are you sure you want to delete room\n " + roomDTO.toString());
                if (result) {
                    boolean saved = roomService.deleteRoomType(roomDTO);
                    setDataToTableView();
                    if (saved) {
                        AlertController.confirmmessage("room details deleted successfully");
                        clearTxtFields();
                    } else {
                        AlertController.errormessage("room details deleting process unsuccessful");
                    }
                }
            } else {
                AlertController.errormessage("room type id " + roomDTO.getRoomTypeId() + " doesn't exist");
            }
        } else {
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        if (noEmptyFields) {
            RoomDTO roomDTO = getDetailsInTextFields();
            boolean saved = roomService.updateRoomType(roomDTO);
            setDataToTableView();
            if (saved) {
                AlertController.confirmmessage("room details updated successfully");
                clearTxtFields();
            } else {
                AlertController.errormessage("room details updating process unsuccessful");
            }
        } else {
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean noEmptyFields = noEmptyValuesInTextFields();
        if (noEmptyFields) {
            RoomDTO roomDTO = getDetailsInTextFields();
            boolean saved = roomService.saveRoomType(roomDTO);
            setDataToTableView();
            if (saved) {
                AlertController.confirmmessage("room details saved successfully");
                clearTxtFields();
            } else {
                AlertController.errormessage("room details saving process unsuccessful");
            }
        } else {
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    private void setDataToTableView() {
        ObservableList<RoomDTO> roomDtoList = roomService.getDetailsToTableView();
        ObservableList<RoomTM> roomTmList = FXCollections.observableArrayList();
        for (RoomDTO dto : roomDtoList) {
            roomTmList.add(
                    new RoomTM(
                            dto.getRoomTypeId(),
                            dto.getRoomType(),
                            dto.getPerRoom(),
                            dto.getKeyMoney(),
                            dto.getRoomQty()
                    )
            );
        }
        tableRoom.setItems(roomTmList);
    }

    public void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colPersonsQty.setCellValueFactory(new PropertyValueFactory<>("perRoom"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("roomQty"));
    }

    public RoomDTO getDetailsInTextFields() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setPerRoom(Integer.parseInt(txtPersonQty.getText()));
        roomDTO.setRoomQty(Integer.parseInt(txtRoomQty.getText()));
        roomDTO.setKeyMoney(txtKeyMoney.getText());
        roomDTO.setRoomTypeId(txtId.getText());
        roomDTO.setRoomType(txtType.getText());
        return roomDTO;
    }

    void clearTxtFields() {
        txtPersonQty.setText("");
        txtRoomQty.setText("");
        txtKeyMoney.setText("");
        txtId.setText("");
        txtType.setText("");
    }

    public void tableRoomOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tableRoom.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<RoomTM, ?>> columns = tableRoom.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtType.setText(columns.get(1).getCellData(row).toString());
        txtPersonQty.setText(columns.get(2).getCellData(row).toString());
        txtKeyMoney.setText(columns.get(3).getCellData(row).toString());
        txtRoomQty.setText(columns.get(4).getCellData(row).toString());

        txtId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtRoomQty.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtPersonQty.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtKeyMoney.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtType.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");

        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        roomFormAncPane.getChildren().clear();
        roomFormAncPane.getChildren().add(load);
    }

    public boolean noEmptyValuesInTextFields() {
        String roomTypeId = txtId.getText();
        String roomType = txtType.getText();
        String roomQty = txtRoomQty.getText();
        String keyMoney = txtKeyMoney.getText();
        String maxPer = txtPersonQty.getText();
        if (!roomTypeId.isEmpty() && !roomType.isEmpty() && !roomQty.isEmpty() && !keyMoney.isEmpty() &&
                !maxPer.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void btnEnable() {
        if (ValidateFields.roomIdCheck(txtId.getText()) && ValidateFields.moneyCheck(txtKeyMoney.getText()) &&
                ValidateFields.numberCheck(txtRoomQty.getText()) && ValidateFields.numberCheck(txtPersonQty.getText())
        ) {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void txtIdOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtId.getText();
        if (ValidateFields.roomIdCheck(txt)) {
            txtId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtTypeOnMouseKeyTyped(KeyEvent keyEvent) {
    }

    public void txtKeyMoneyOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtKeyMoney.getText();
        if (ValidateFields.moneyCheck(txt)) {
            txtKeyMoney.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtKeyMoney.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtRoomQtyOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtRoomQty.getText();
        if (ValidateFields.numberCheck(txt)) {
            txtRoomQty.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtRoomQty.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtPersonQtyOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtPersonQty.getText();
        if (ValidateFields.numberCheck(txt)) {
            txtPersonQty.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        } else {
            txtPersonQty.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTxtFields();
    }
}
