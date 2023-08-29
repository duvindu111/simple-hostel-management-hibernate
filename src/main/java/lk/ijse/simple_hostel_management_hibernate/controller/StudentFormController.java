package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.controller.util.ValidateFields;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.StudentService;
import lk.ijse.simple_hostel_management_hibernate.view.tm.StudentTM;

public class StudentFormController {

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
    private ComboBox<String> cmbGender;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContacts;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private DatePicker dpDob;

    @FXML
    private AnchorPane studentFormAncPane;

    @FXML
    private TableView<StudentTM> tableStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact1;

    @FXML
    private TextField txtContact2;

    @FXML
    private TextField txtStId;

    @FXML
    private TextField txtStName;

    StudentService studentService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.STUDENT);

    @FXML
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'student_form.fxml'.";
        assert studentFormAncPane != null : "fx:id=\"studentFormAncPane\" was not injected: check your FXML file 'student_form.fxml'.";

        Image image = new Image("assets/images/back_icon.png");
        ImageView imageView = new ImageView(image);
        btnBack.setGraphic(imageView);

        cmbGender.getItems().addAll("MALE","FEMALE","OTHER");

        setDataToTableView();
        setCellValueFactory();
    }

    public StudentDTO getDetailsInTextFields(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(txtStId.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setDob(dpDob.getValue());
        studentDTO.setGender((String) cmbGender.getValue());
        studentDTO.setName(txtStName.getText());
        studentDTO.setStudentContact(txtContact1.getText());
        return studentDTO;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean emptyFields = noEmptyValuesInTextFields();
        if(emptyFields) {
            boolean deleted = studentService.deleteStudent(studentDTO);
            setDataToTableView();
            if (deleted) {
                AlertController.confirmmessage("student details deleted successfully");
                clearTxtFields();
            } else {
                AlertController.errormessage("student details deleting process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean emptyFields = noEmptyValuesInTextFields();
        if(emptyFields) {
            boolean saved = studentService.saveStudent(studentDTO);
            setDataToTableView();
            if (saved) {
                AlertController.confirmmessage("student details saved successfully");
                clearTxtFields();
            } else {
                AlertController.errormessage("student details saving process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean emptyFields = noEmptyValuesInTextFields();
        if(emptyFields) {
            boolean updated = studentService.updateStudent(studentDTO);
            setDataToTableView();
            if (updated) {
                AlertController.confirmmessage("student details updated successfully");
                clearTxtFields();
            } else {
                AlertController.errormessage("student details updating process unsuccessful");
            }
        }else{
            AlertController.errormessage("please make sure to fill out all the required fields");
        }
    }

    public void setDataToTableView(){
        ObservableList<StudentDTO> studentDtoList = studentService.getDetailsToTableView();
        ObservableList<StudentTM> studentTmList = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDtoList){
            studentTmList.add(
                    new StudentTM(
                            dto.getId(),
                            dto.getAddress(),
                            dto.getDob(),
                            dto.getGender(),
                            dto.getName(),
                            dto.getStudentContact()
                    )
            );
        }
        tableStudent.setItems(studentTmList);
    }

    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContacts.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void tableStudentOnMouseClicked(MouseEvent event) {
        TablePosition pos = tableStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<StudentTM, ?>> columns = tableStudent.getColumns();

        txtStId.setText(columns.get(0).getCellData(row).toString());
        txtStName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtContact1.setText(columns.get(3).getCellData(row).toString());
        dpDob.setValue((LocalDate) columns.get(4).getCellData(row));
        cmbGender.setValue(columns.get(5).getCellData(row).toString());

        txtStId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtStName.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtAddress.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
        txtContact1.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");

        btnSave.setDisable(false);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    void clearTxtFields(){
        txtStId.setText("");
        txtStName.setText("");
        txtContact1.setText("");
        txtAddress.setText("");
        dpDob.setValue(null);
        cmbGender.setValue("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        studentFormAncPane.getChildren().clear();
        studentFormAncPane.getChildren().add(load);
    }

    public boolean noEmptyValuesInTextFields(){
        String stId = txtStId.getText();
        String stAdd = txtAddress.getText();
        LocalDate dob = dpDob.getValue();
        String gender = cmbGender.getValue();
        String name = txtStName.getText();
        String contact = txtContact1.getText();
        if (!stId.isEmpty() && !stAdd.isEmpty() && !contact.isEmpty() && gender!=null && dob!=null &&
                !gender.isEmpty() && !name.isEmpty() ) {
            return true;
        } else {
            return false;
        }
    }

    public void txtStIdOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtStId.getText();
        if(ValidateFields.studentIdCheck(txt)){
            txtStId.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        }else{
            txtStId.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtStNameOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtStName.getText();
        if(ValidateFields.nameCheck(txt)){
            txtStName.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        }else{
            txtStName.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtAddressOnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtAddress.getText();
        if(ValidateFields.addressCheck(txt)){
            txtAddress.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        }else{
            txtAddress.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void txtContact1OnMouseKeyTyped(KeyEvent keyEvent) {
        String txt = txtContact1.getText();
        if(ValidateFields.contactCheck(txt)){
            txtContact1.setStyle("-fx-text-fill: black; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnEnable();
        }else{
            txtContact1.setStyle("-fx-text-fill: red; -fx-background-color: #ebebeb; -fx-background-radius: 15");
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    public void btnEnable(){
        if(ValidateFields.nameCheck(txtStName.getText()) && ValidateFields.studentIdCheck(txtStId.getText()) &&
                ValidateFields.addressCheck(txtAddress.getText()) && ValidateFields.contactCheck(txtContact1.getText())
        ){
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTxtFields();
    }
}
