import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBugController implements Initializable {
    public Button cancelButton;
    public ComboBox<String > severityBox,priorityBox,assigntoBox;
    public TextField titleField,descriptionField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<String>();
        list.add("Severe");
        list.add("Major");
        list.add("Minor");
        list.add("Low");
        ObservableList oblist = FXCollections.observableList(list);
        severityBox.setItems(oblist);

        List<String> list2 = new ArrayList<String>();
        list2.add("Q1");
        list2.add("Q2");
        list2.add("Q3");
        list2.add("Q4");
        ObservableList oblist2 = FXCollections.observableList(list2);
        priorityBox.setItems(oblist2);

        List<String> list3 = new ArrayList<String>();
        try {
            Connection con = DBConnection.getConnection();
            String query ="SELECT user FROM developers WHERE Projects LIKE '%"+ GlobalVariables.project+"%'";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result =statement.executeQuery();
            while(result.next()){
                list3.add(result.getString("user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList oblist3 = FXCollections.observableArrayList(list3);
        assigntoBox.setItems(oblist3);
    }

    public void add(ActionEvent event) {
        if(titleField.getText().isEmpty() || descriptionField.getText().isEmpty() ||severityBox.getValue().equals("Severity")||priorityBox.getValue().equals("Priority")|| assigntoBox.getValue().equals("Assign to"))
            JOptionPane.showMessageDialog(null,"Please fill the empty fields");
        else{
            try {
                Connection con = DBConnection.getConnection();
                String query = "INSERT INTO bugs(title,severity,priority,reporter,assignedto,description,status,dateofsubmission,project) VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,titleField.getText());
                statement.setString(2,severityBox.getValue());
                statement.setString(3,priorityBox.getValue());
                statement.setString(4,GlobalVariables.username);
                statement.setString(5,assigntoBox.getValue());
                statement.setString(6,descriptionField.getText());
                statement.setString(7,"To-Do");
                statement.setString(8,String.valueOf(java.time.LocalDate.now()));
                statement.setString(9,GlobalVariables.project);
                statement.execute();
                con.close();
                Stage stage = (Stage) this.cancelButton.getScene().getWindow();
                stage.close();
                try {
                    if(GlobalVariables.mode ==0)
                        new DeveloperDashboard().start(new Stage());
                    else
                        new ProjectManagerDashboard().start(new Stage());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
        try {
            if(GlobalVariables.mode==0)
                new DeveloperDashboard().start(new Stage());
            else
                new ProjectManagerDashboard().start(new Stage());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
