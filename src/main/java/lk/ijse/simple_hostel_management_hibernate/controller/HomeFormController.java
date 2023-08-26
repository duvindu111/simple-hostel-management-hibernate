package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class HomeFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private AnchorPane homeFormAncPane;

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

    }

}
