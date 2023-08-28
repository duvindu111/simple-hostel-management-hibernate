package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.entity.User;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.SignUpService;

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
    private AnchorPane signUpAncPane;

    SignUpService signUpService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.SIGN_UP);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        signUpAncPane.getChildren().clear();
        signUpAncPane.getChildren().add(load);
    }

    @FXML
    void btnNewAccountOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String pass= txtPassword.getText();

        String sameUsername = signUpService.checkUsernameAvailability(username);

        if(sameUsername != null){
            AlertController.errormessage("this username is already taken");
        }else{
            UserDTO userDto = new UserDTO(username,pass);
            boolean saved = signUpService.saveNewUser(userDto);
            if(saved){
                AlertController.confirmmessage("new account created successfully");
            }else{
                AlertController.errormessage("account creation process interrupted");
            }
        }
    }

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert btnNewAccount != null : "fx:id=\"btnNewAccount\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtConPassword != null : "fx:id=\"txtConPassword\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'signup_form.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'signup_form.fxml'.";

        btnNewAccount.setDisable(true);
    }

    @FXML
    void txtConPasswordOnKeyTyped(KeyEvent event) {
        createBtnAvailability();
    }

    @FXML
    void txtPasswordOnKeyTyped(KeyEvent event) {
        createBtnAvailability();
    }

    public void txtUsernameOnKeyTyped(KeyEvent keyEvent) {
        createBtnAvailability();
    }

    public void createBtnAvailability(){
        String pass= txtPassword.getText();
        String conPass = txtConPassword.getText();
        String un = txtUsername.getText();
        if(!conPass.isEmpty() && !un.isEmpty()) {
            if (conPass.equals(pass) && un.length()>=6 && pass.length()>=8){
                btnNewAccount.setDisable(false);
            }else{
                btnNewAccount.setDisable(true);
            }
        }else{
            btnNewAccount.setDisable(true);
        }
    }
}
