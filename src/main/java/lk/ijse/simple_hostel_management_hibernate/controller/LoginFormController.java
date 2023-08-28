package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.LoginService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private PasswordField txtPassword;

    @FXML
    private TextField txtDisplayPass;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane loginAncPane;

    LoginService loginService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.LOGIN);

    @FXML
    void btnSubmitOnAction(ActionEvent event) throws IOException {
        String pin = loginService.getAdminPin();
        String typedPin = txtAuthPin.getText();

        if (!typedPin.isEmpty()) {
            if (typedPin.equals(pin)) {
                System.out.println(pin);
                System.out.println(typedPin);
                Parent load = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
                loginAncPane.getChildren().clear();
                loginAncPane.getChildren().add(load);
            }
        }
    }

    @FXML
    void icnCloseOnMouseClicked(MouseEvent event) {
        grpLogin.setVisible(true);
        grpAdminPin.setVisible(false);
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

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();

        String password;
        if(txtDisplayPass.isVisible()){
            password=txtDisplayPass.getText();
        }else{
            password = txtPassword.getText();
        }

        if (!username.isEmpty() && !password.isEmpty()) {
            String sameUsername = loginService.checkUsernameAvailability(username);

            if (sameUsername != null) {
                String dbPassword = loginService.checkPassword(username);
                if (dbPassword.equals(password)) {
                    HomeFormController.getUsernameFromLogin(username);
                    btnLogin.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/home_form.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("D24 Hostel Management System");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();

                } else {
                    AlertController.errormessage("incorrect password \nplease try again");
                }
            } else {
                AlertController.errormessage("no account available for the username: \"" + username + "\"");
            }
        }else{
            AlertController.errormessage("please make sure to fill out\nall the required fields");
        }
    }

    public void btnNewAccountOnAction(ActionEvent actionEvent) {
        grpLogin.setVisible(false);
        grpAdminPin.setVisible(true);
    }


    public void icnEyeOnMouseClicked(MouseEvent mouseEvent) {
        if(!txtDisplayPass.isVisible()){
            txtDisplayPass.setText(txtPassword.getText());
            txtDisplayPass.setVisible(true);
        }else{
            txtPassword.setText(txtDisplayPass.getText());
            txtDisplayPass.setVisible(false);
            btnLogin.requestFocus();
        }
    }
}
