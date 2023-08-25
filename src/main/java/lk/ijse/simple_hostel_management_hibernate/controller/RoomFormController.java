package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RoomFormController{

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
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane studentFormAncPane;

    @FXML
    private TableView<?> tableStudent;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtType;

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'room_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colKeyMoney != null : "fx:id=\"colKeyMoney\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colQuantity != null : "fx:id=\"colQuantity\" was not injected: check your FXML file 'room_form.fxml'.";
        assert colType != null : "fx:id=\"colType\" was not injected: check your FXML file 'room_form.fxml'.";
        assert studentFormAncPane != null : "fx:id=\"studentFormAncPane\" was not injected: check your FXML file 'room_form.fxml'.";
        assert tableStudent != null : "fx:id=\"tableStudent\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtKeyMoney != null : "fx:id=\"txtKeyMoney\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtQuantity != null : "fx:id=\"txtQuantity\" was not injected: check your FXML file 'room_form.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'room_form.fxml'.";

        Image image = new Image("assets/images/back_icon.png");
        ImageView imageView = new ImageView(image);
        btnBack.setGraphic(imageView);
    }

}
