import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    SignupModel signupModel  = new SignupModel();
    @FXML
    public Button signup;
    @FXML
    private Button back;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<SignupEnum> combobox;
    @FXML
    private Label checkLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.combobox.setItems(FXCollections.observableArrayList(SignupEnum.values()));
        this.combobox.getSelectionModel().selectFirst();
    }
    @FXML
    public void signUp(){
        if(this.username.getText().isEmpty() || this.password.getText().isEmpty() || this.firstname.getText().isEmpty() ||this.lastname.getText().isEmpty() || this.dob.getEditor().getText().isEmpty())
            this.checkLabel.setText("Please check your inputs");
        else{
            try {
                signupModel.signUp(this.username.getText(),this.password.getText(),this.firstname.getText(),this.lastname.getText(),this.email.getText(),this.dob.getEditor().getText(),((SignupEnum)this.combobox.getValue()).toString());
                JOptionPane.showMessageDialog(null,"You have successfully signed up!");
                Stage stage = (Stage) this.signup.getScene().getWindow();
                stage.close();
                if(!LoginController.flag)
                    new Login().start(new Stage());
                else
                    new AdminDashboard().start(new Stage());
            } catch (Exception e) {
                this.checkLabel.setText("Please check your inputs");
            }
        }

    }
    @FXML
    public void back(ActionEvent event){
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
        try {
            if(LoginController.flag)
                new AdminDashboard().start(new Stage());
            else
                new Login().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
