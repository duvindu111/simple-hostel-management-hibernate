package lk.ijse.simple_hostel_management_hibernate.controller.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertController {
    public static void errormessage(String msg){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150);
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/images/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static void confirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/images/tick.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static boolean okconfirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/images/tick.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
        return false;
    }

    public static void warningmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to right, #052647, #011c32); "); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: white");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/images/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }
}
