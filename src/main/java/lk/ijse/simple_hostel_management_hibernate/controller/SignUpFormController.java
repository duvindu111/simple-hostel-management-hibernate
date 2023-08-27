package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignUpFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnNewAccount;

    @FXML
    private TextField txtConPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewAccountOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert btnNewAccount != null : "fx:id=\"btnNewAccount\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtConPassword != null : "fx:id=\"txtConPassword\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'signup_form.fxml'.";

    }

}
