import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;

import java.net.URL;

public class Launcher extends Application{
    public static void main(String[] args) {
        //SessionFactoryConfig.getInstance().getSession();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("/view/login_form.fxml");
        Parent load = FXMLLoader.load(resource);
        stage.setScene(new Scene(load));
        //stage.setTitle("");
        stage.getIcons().add(new Image("/assets/images/title.png"));
        stage.centerOnScreen();
        stage.show();
    }
}
