import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel  = new LoginModel();
    public static boolean flag;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private ComboBox<option> comboBox;
    @FXML
    private Label checkLabel;
    public void initialize(URL url, ResourceBundle rb){
        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
        this.comboBox.getSelectionModel().selectFirst();
    }
    @FXML
    public void Login(ActionEvent event){
        try {
            flag=true;
            if(loginModel.isLogin(this.username.getText(),this.password.getText(),((option)this.comboBox.getValue()).toString())){
                Stage stage = (Stage) this.loginButton.getScene().getWindow();

                stage.close();
                switch(((option)this.comboBox.getValue()).toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "ProjectManager":
                        GlobalVariables.username = this.username.getText();
                        GlobalVariables.password = this.password.getText();
                        GlobalVariables.getInfo(this.username.getText(),this.password.getText(),((option)this.comboBox.getValue()).toString());
                        projectmanagerLogin();
                        break;
                    case "Developer":
                        GlobalVariables.username = this.username.getText();
                        GlobalVariables.password = this.password.getText();
                        GlobalVariables.getInfo(this.username.getText(),this.password.getText(),((option)this.comboBox.getValue()).toString());
                        developerLogin();
                        break;
                }
            }else
                this.checkLabel.setText("Please check your username and password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Signup(ActionEvent event) {
        flag=false;
        Stage stage = (Stage) this.loginButton.getScene().getWindow();
        stage.close();
        try {
                new Signup().start(new Stage());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void adminLogin(){
        try {
            new AdminDashboard().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void projectmanagerLogin(){
        try {
            new ProjectManagerDashboard().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void developerLogin(){
        try {
            new DeveloperDashboard().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//try {
//        Stage stage = (Stage) this.signupButton.getScene().getWindow();
//        stage.close();
//        Stage userStage = new Stage();
//        FXMLLoader loader = new FXMLLoader();
//        Pane root = (Pane) loader.load(getClass().getResource("signup.fxml").openStream());             ----> very important
//        SignupController adminDashboardController = (SignupController) loader.getController();
//        Scene scene = new Scene(root);
//        userStage.setTitle("Admin Dashboard");
//        userStage.setScene(scene);
//        userStage.show();
//        } catch (IOException e) {
//        e.printStackTrace();
//        }