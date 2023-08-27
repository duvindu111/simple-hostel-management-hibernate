package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnNewAccount;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private Group grpAdminPin;

    @FXML
    private Group grpLogin;

    @FXML
    private Label lblForgotPass;

    @FXML
    private PasswordField txtAuthPin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnSubmitOnAction(ActionEvent event) {

    }

    @FXML
    void icnCloseOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert btnNewAccount != null : "fx:id=\"btnNewAccount\" was not injected: check your FXML file 'login_form.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'login_form.fxml'.";
        assert grpAdminPin != null : "fx:id=\"grpAdminPin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert grpLogin != null : "fx:id=\"grpLogin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert lblForgotPass != null : "fx:id=\"lblForgotPass\" was not injected: check your FXML file 'login_form.fxml'.";
        assert txtAuthPin != null : "fx:id=\"txtAuthPin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'login_form.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'login_form.fxml'.";

    }

}
