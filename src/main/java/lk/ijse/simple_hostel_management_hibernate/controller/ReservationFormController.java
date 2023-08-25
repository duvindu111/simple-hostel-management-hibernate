package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private ComboBox<?> cmbGender;

    @FXML
    private ComboBox<?> cmbGender1;

    @FXML
    private ComboBox<?> cmbGender11;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colQuantity1;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private DatePicker dpDate;

    @FXML
    private AnchorPane studentFormAncPane;

    @FXML
    private TableView<?> tableStudent;

    @FXML
    private TextField txtResId;

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert cmbGender != null : "fx:id=\"cmbGender\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert cmbGender1 != null : "fx:id=\"cmbGender1\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert cmbGender11 != null : "fx:id=\"cmbGender11\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert colKeyMoney != null : "fx:id=\"colKeyMoney\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert colQuantity != null : "fx:id=\"colQuantity\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert colQuantity1 != null : "fx:id=\"colQuantity1\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert colType != null : "fx:id=\"colType\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert dpDate != null : "fx:id=\"dpDate\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert studentFormAncPane != null : "fx:id=\"studentFormAncPane\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert tableStudent != null : "fx:id=\"tableStudent\" was not injected: check your FXML file 'reservation_form.fxml'.";
        assert txtResId != null : "fx:id=\"txtResId\" was not injected: check your FXML file 'reservation_form.fxml'.";

        Image image = new Image("assets/images/back_icon.png");
        ImageView imageView = new ImageView(image);
        btnBack.setGraphic(imageView);
    }

}
