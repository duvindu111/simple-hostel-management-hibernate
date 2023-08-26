package lk.ijse.simple_hostel_management_hibernate.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.simple_hostel_management_hibernate.controller.util.AlertController;
import lk.ijse.simple_hostel_management_hibernate.dto.StudentDTO;
import lk.ijse.simple_hostel_management_hibernate.service.ServiceFactory;
import lk.ijse.simple_hostel_management_hibernate.service.custom.StudentService;

public class PopupStudentFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private DatePicker dpDob;

    @FXML
    private AnchorPane studentFormAncPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact1;

    @FXML
    private TextField txtStId;

    @FXML
    private TextField txtStName;

    StudentService studentService = ServiceFactory.getServiceFactory().getservice(ServiceFactory.ServiceTypes.STUDENT);

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO studentDTO = getDetailsInTextFields();
        boolean saved = studentService.saveStudent(studentDTO);
        if(saved){
            AlertController.confirmmessage("student details saved successfully");
            clearTxtFields();
        }else{
            AlertController.errormessage("student details saving process unsuccessful");
        }
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

    void clearTxtFields(){
        txtStId.setText("");
        txtStName.setText("");
        txtContact1.setText("");
        txtAddress.setText("");
        dpDob.setValue(null);
        cmbGender.setValue("");
    }

    @FXML
    void initialize() {
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert cmbGender != null : "fx:id=\"cmbGender\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert dpDob != null : "fx:id=\"dpDob\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert studentFormAncPane != null : "fx:id=\"studentFormAncPane\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert txtContact1 != null : "fx:id=\"txtContact1\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert txtStId != null : "fx:id=\"txtStId\" was not injected: check your FXML file 'popup_student_form.fxml'.";
        assert txtStName != null : "fx:id=\"txtStName\" was not injected: check your FXML file 'popup_student_form.fxml'.";

        cmbGender.getItems().addAll("MALE","FEMALE","OTHER");
    }

}
