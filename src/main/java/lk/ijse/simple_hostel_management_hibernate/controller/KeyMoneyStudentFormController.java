package lk.ijse.simple_hostel_management_hibernate.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.KeyMoneyStudentService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.StudentKeyMoneyTM;

public class KeyMoneyStudentFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private AnchorPane studentFormAncPane;

    @FXML
    private TableView<StudentKeyMoneyTM> tableStudentKeyMoney;

    KeyMoneyStudentService keyMoneyStudentService = ServiceFactory.getServiceFactory()
            .getservice(ServiceFactory.ServiceTypes.KEY_MONEY_STUDENT);
    @FXML
    void tableStudentOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert colAddress != null : "fx:id=\"colAddress\" was not injected: check your FXML file 'key_money_st_form.fxml'.";
        assert colContacts != null : "fx:id=\"colContacts\" was not injected: check your FXML file 'key_money_st_form.fxml'.";
        assert colDob != null : "fx:id=\"colDob\" was not injected: check your FXML file 'key_money_st_form.fxml'.";
        assert colGender != null : "fx:id=\"colGender\" was not injected: check your FXML file 'key_money_st_form.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'key_money_st_form.fxml'.";
        assert studentFormAncPane != null : "fx:id=\"studentFormAncPane\" was not injected: check your FXML file 'key_money_st_form.fxml'.";

        setDataToTableView();
        setCellValueFactory();
    }

    public void setDataToTableView(){
        ObservableList<CustomProjection> studentKeyMoneyList = keyMoneyStudentService.getDetailsToTableView();
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
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
    }

}
