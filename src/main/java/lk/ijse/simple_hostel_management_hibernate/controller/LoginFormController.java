package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.LoginService;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXButton btnGFPGetPass;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnNewAccount;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private Group grpAdminPin;

    @FXML
    private Group grpGFP;

    @FXML
    private Group grpLogin;

    @FXML
    private ImageView icnEye;

    @FXML
    private Label lblForgotPass;

    @FXML
    private Label lblGFPPassword;

    @FXML
    private AnchorPane loginAncPane;

    @FXML
    private PasswordField txtAuthPin;

    @FXML
    private TextField txtDisplayPass;

    @FXML
    private PasswordField txtGFPAuthPin;

    @FXML
    private TextField txtGFPUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblGFPTimer;

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
        if (txtDisplayPass.isVisible()) {
            password = txtDisplayPass.getText();
        } else {
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
        } else {
            AlertController.errormessage("please make sure to fill out\nall the required fields");
        }
    }

    public void btnNewAccountOnAction(ActionEvent actionEvent) {
        grpLogin.setVisible(false);
        grpAdminPin.setVisible(true);
    }


    public void icnEyeOnMouseClicked(MouseEvent mouseEvent) {
        if (!txtDisplayPass.isVisible()) {
            txtDisplayPass.setText(txtPassword.getText());
            txtDisplayPass.setVisible(true);
        } else {
            txtPassword.setText(txtDisplayPass.getText());
            txtDisplayPass.setVisible(false);
            btnLogin.requestFocus();
        }
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLoginOnAction(actionEvent);
    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        if (!txtUsername.getText().isEmpty()) {
            txtPassword.requestFocus();
        }
    }

    public void btnGFPGetPassOnAction(ActionEvent actionEvent) {
        String username = txtGFPUsername.getText();
        String pin = txtGFPAuthPin.getText();

        String usernameAvailability = loginService.checkUsernameAvailability(username);
        String dbPin = loginService.getAdminPin();

        if (!username.isEmpty() && !pin.isEmpty()) {
            if (dbPin.equals(pin)) {
                if (usernameAvailability != null) {
                    String pass = loginService.checkPassword(username);
                    lblGFPPassword.setText(pass);
                    txtGFPUsername.setText("");
                    txtGFPAuthPin.setText("");
                    lblGFPPassword.setVisible(true);

//                    lblGFPPassword.setVisible(false);
//                    lblGFPPassword.setText("");

                    timermethod(lblGFPTimer,10);


                } else {
                    AlertController.errormessage("Username not available");
                }
            } else {
                AlertController.warningmessage("Authorization Pin Incorrect");
            }
        }
    }

    private Timeline timeline;
    private SimpleIntegerProperty timeSeconds = new SimpleIntegerProperty();

    public void timermethod(Label label, int START_TIME) {
        label.setVisible(true);
        timeSeconds.set(START_TIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME + 1),
                new KeyValue(timeSeconds, 0)));
        timeline.setOnFinished(event -> {
            label.setVisible(false);
            lblGFPPassword.setVisible(false);
            lblGFPPassword.setText("");
        });
        timeline.playFromStart();
        label.textProperty().bind(timeSeconds.asString());
    }


    public void lblForgotPassOnMouseClicked(MouseEvent mouseEvent) {
        grpLogin.setVisible(false);
        grpGFP.setVisible(true);
    }

    public void icnGFPCloseOnMouseClicked(MouseEvent mouseEvent) {
        txtGFPUsername.setText("");
        txtGFPAuthPin.setText("");
        grpLogin.setVisible(true);
        grpGFP.setVisible(false);
    }
}
