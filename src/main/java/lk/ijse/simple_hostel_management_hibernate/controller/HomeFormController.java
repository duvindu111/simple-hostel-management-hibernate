package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.HomeService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.StudentKeyMoneyTM;

public class HomeFormController {

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContacts;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colResDate;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private AnchorPane homeFormAncPane;

    @FXML
    private TableView<StudentKeyMoneyTM> tableStudentKeyMoney;

    HomeService homeService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.HOME);
    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        homeFormAncPane.getChildren().clear();
        homeFormAncPane.getChildren().add(load);
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/room_form.fxml"));
        homeFormAncPane.getChildren().clear();
        homeFormAncPane.getChildren().add(load);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        homeFormAncPane.getChildren().clear();
        homeFormAncPane.getChildren().add(load);
    }

    @FXML
    void initialize() {
        assert btnReservation != null : "fx:id=\"btnReservation\" was not injected: check your FXML file 'home_form.fxml'.";
        assert btnRoom != null : "fx:id=\"btnRoom\" was not injected: check your FXML file 'home_form.fxml'.";
        assert btnStudent != null : "fx:id=\"btnStudent\" was not injected: check your FXML file 'home_form.fxml'.";
        assert homeFormAncPane != null : "fx:id=\"homeFormAncPane\" was not injected: check your FXML file 'home_form.fxml'.";

        setDataToTableView();
        setCellValueFactory();
    }

    public void setDataToTableView(){
        ObservableList<CustomProjection> studentKeyMoneyList = homeService.getDetailsToTableView();
        ObservableList<StudentKeyMoneyTM> studentKeyMoneyTmList = FXCollections.observableArrayList();
        for (CustomProjection cp : studentKeyMoneyList){
            studentKeyMoneyTmList.add(
                    new StudentKeyMoneyTM(
                            cp.getId(),
                            cp.getAddress(),
                            cp.getDob(),
                            cp.getGender(),
                            cp.getName(),
                            cp.getStudentContact(),
                            cp.getRes_id(),
                            cp.getDate(),
                            cp.getRoom_type(),
                            cp.getKey_money()
                    )
            );
        }
        tableStudentKeyMoney.setItems(studentKeyMoneyTmList);
    }

    public void setCellValueFactory(){
        colStId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContacts.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
    }

    public void tableStudentOnMouseClicked(MouseEvent mouseEvent) {
    }
}
