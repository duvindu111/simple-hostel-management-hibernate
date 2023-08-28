package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.UserDTO;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.HomeService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.StudentKeyMoneyTM;

import java.io.IOException;

public class HomeFormController {

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContacts;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colResDate;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private Group grpChangePass;

    @FXML
    private Group grpCurrentPass;

    @FXML
    private Group grpNewPass;

    @FXML
    private Group grpSettings;

    @FXML
    private AnchorPane homeFormAncPane;

    @FXML
    private ImageView icnCloseCP;

    @FXML
    private ImageView icnCloseNP;

    @FXML
    private ImageView icnSettings;

    @FXML
    private Label lblChangePass;

    @FXML
    private Label lblSettings;

    @FXML
    private PasswordField txtCurrentPass;

    @FXML
    private TableView<StudentKeyMoneyTM> tableStudentKeyMoney;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private PasswordField txtConNewPass;

    @FXML
    private PasswordField txtNewPass;

    @FXML
    private Label lblConNewPass;

    @FXML
    private Label lblCurrentPass;

    @FXML
    private Label lblNewPass;

    @FXML
    private ImageView icnEyeBottom;

    @FXML
    private ImageView icnEyeMid;

    @FXML
    private Label lblPassLength;

    @FXML
    private Label lblCharacters;

    @FXML
    private Label lblPassMatch;

    HomeService homeService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.HOME);

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

        setDataToTableView();
        setCellValueFactory();
    }

    public void setDataToTableView() {
        ObservableList<CustomProjection> studentKeyMoneyList = homeService.getDetailsToTableView();
        ObservableList<StudentKeyMoneyTM> studentKeyMoneyTmList = FXCollections.observableArrayList();
        for (CustomProjection cp : studentKeyMoneyList) {
            studentKeyMoneyTmList.add(
                    new StudentKeyMoneyTM(
                            cp.getId(),
                            cp.getAddress(),
                            cp.getDob(),
                            cp.getGender(),
                            cp.getName(),
                            cp.getStudentContact(),
                            cp.getRes_id(),
                            cp.getDate(),
                            cp.getRoom_type(),
                            cp.getKey_money()
                    )
            );
        }
        tableStudentKeyMoney.setItems(studentKeyMoneyTmList);
    }

    public void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContacts.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
    }

    public void tableStudentOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void lblChangePassOnMouseClicked(MouseEvent mouseEvent) {
        grpChangePass.setVisible(true);
        grpCurrentPass.setVisible(true);
        grpNewPass.setVisible(false);
    }

    public void icnSettingsOnMouseClicked(MouseEvent mouseEvent) {
        grpSettings.setVisible(true);
    }

    public void icnSettingsOnMouseEntered(MouseEvent mouseEvent) {
        lblSettings.setVisible(true);
    }

    public void icnCloseCPOnMouseClicked(MouseEvent mouseEvent) {
        grpSettings.setVisible(false);
    }

    public void icnSettingsOnMouseExited(MouseEvent mouseEvent) {
        lblSettings.setVisible(false);
    }

    public void icnCloseNPOnMouseClicked(MouseEvent mouseEvent) {
        grpChangePass.setVisible(false);
        grpCurrentPass.setVisible(true);
        grpNewPass.setVisible(false);
    }

    static String username;

    public static void getUsernameFromLogin(String txt) {
        username = txt;
    }

    public void txtCurrentPassOnAction(ActionEvent actionEvent) {
        String currentPass = txtCurrentPass.getText();
        String dbCurrentPass = homeService.checkCurrentPass(username);

        if (currentPass.equals(dbCurrentPass)) {
            txtCurrentPass.setText("");
            grpCurrentPass.setVisible(false);
            grpNewPass.setVisible(true);
        } else {
            AlertController.errormessage("wrong password\nplease try again");
        }
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        String newPass = txtNewPass.getText();
        String conNewPass = txtConNewPass.getText();

        if (!newPass.isEmpty()) {
            if (newPass.equals(conNewPass)) {
                if (newPass.length() >= 8) {
                    lblPassLength.setStyle("-fx-text-fill: black");
                    lblCharacters.setStyle("-fx-text-fill: black");
                    UserDTO userDto = new UserDTO(username, newPass);
                    boolean changed = homeService.changePassword(userDto);
                    if (changed) {
                        txtNewPass.setText("");
                        txtConNewPass.setText("");
                        grpChangePass.setVisible(false);
                        grpCurrentPass.setVisible(true);
                        grpNewPass.setVisible(false);
                    } else {
                        AlertController.errormessage("Process Interrupted.\nThe password didn't change.");
                    }
                }else{
                   lblPassLength.setStyle("-fx-text-fill: red");
                    lblCharacters.setStyle("-fx-text-fill: red");
                }
            }else{
                lblPassLength.setVisible(false);
                lblCharacters.setVisible(false);
                lblPassMatch.setVisible(true);
            }
        }
    }

    String curPass;

    public void icnEyeTopOnMouseEntered(MouseEvent mouseEvent) {
        curPass = txtCurrentPass.getText();
        txtCurrentPass.setText("");
        lblCurrentPass.setText(curPass);
        lblCurrentPass.setVisible(true);
        lblCurrentPass.requestFocus();
    }

    public void icnEyeTopOnMouseExited(MouseEvent mouseEvent) {
        lblCurrentPass.setVisible(false);
        txtCurrentPass.setText(curPass);
        txtCurrentPass.requestFocus();
        txtCurrentPass.positionCaret(txtCurrentPass.getLength());
    }

    String newPass;

    public void icnEyeMidOnMouseEntered(MouseEvent mouseEvent) {
        newPass = txtNewPass.getText();
        txtNewPass.setText("");
        lblNewPass.setText(newPass);
        lblNewPass.setVisible(true);
        lblNewPass.requestFocus();
    }

    public void icnEyeMidOnMouseExited(MouseEvent mouseEvent) {
        lblNewPass.setVisible(false);
        txtNewPass.setText(newPass);
        txtNewPass.requestFocus();
        txtNewPass.positionCaret(txtNewPass.getLength());
    }

    String conNewPass;

    public void icnEyeBottomOnMouseEntered(MouseEvent mouseEvent) {
        conNewPass = txtConNewPass.getText();
        txtConNewPass.setText("");
        lblConNewPass.setText(conNewPass);
        lblConNewPass.setVisible(true);
        lblConNewPass.requestFocus();
    }

    public void icnEyeBottomOnMouseExited(MouseEvent mouseEvent) {
        lblConNewPass.setVisible(false);
        txtConNewPass.setText(conNewPass);
        txtConNewPass.requestFocus();
        txtConNewPass.positionCaret(txtNewPass.getLength());
    }

    public void txtNewPassOnKeyTyped(KeyEvent keyEvent) {
        lblPassLength.setStyle("-fx-text-fill: black");
        lblCharacters.setStyle("-fx-text-fill: black");
        lblPassMatch.setVisible(false);
        lblPassLength.setVisible(true);
        lblCharacters.setVisible(true);
    }

    public void txtConNewPassOnKeyTyped(KeyEvent keyEvent) {
        lblPassLength.setStyle("-fx-text-fill: black");
        lblCharacters.setStyle("-fx-text-fill: black");
        lblPassMatch.setVisible(false);
        lblPassLength.setVisible(true);
        lblCharacters.setVisible(true);
    }
}
