package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.StudentService;
import lk.ijse.simple_hostel_management_hibernate.service.custom.impl.StudentServiceImpl;
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
        boolean deleted = studentService.deleteStudent(studentDTO);
        setDataToTableView();
        if(deleted){
            AlertController.confirmmessage("student details deleted successfully");
            clearTxtFields();
        }else{
            AlertController.errormessage("student details deleting process unsuccessful");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean saved = studentService.saveStudent(studentDTO);
        setDataToTableView();
        if(saved){
            AlertController.confirmmessage("student details saved successfully");
            clearTxtFields();
        }else{
            AlertController.errormessage("student details saving process unsuccessful");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean updated = studentService.updateStudent(studentDTO);
        setDataToTableView();
        if(updated){
            AlertController.confirmmessage("student details updated successfully");
            clearTxtFields();
        }else{
            AlertController.errormessage("student details updating process unsuccessful");
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
    }

    void clearTxtFields(){
        txtStId.setText("");
        txtStName.setText("");
        txtContact1.setText("");
        txtAddress.setText("");
        dpDob.setValue(null);
        cmbGender.setValue("");
    }

}
