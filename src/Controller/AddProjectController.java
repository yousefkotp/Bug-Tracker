import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddProjectController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ListView listView;
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker deadlineField;
    @FXML
    private TextArea descriptionField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList();
        ObservableList<String> developers = FXCollections.observableArrayList();
        try {
            Connection con  = DBConnection.getConnection();
            String query = "SELECT user FROM projectmanagers";
            ResultSet result = con.createStatement().executeQuery(query);
            while(result.next())
                options.add(result.getString("user"));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBox.setItems(options);

        try{
            Connection con = DBConnection.getConnection();
            String query = "SELECT user FROM developers";
            ResultSet result = con.createStatement().executeQuery(query);
            while (result.next())
                developers.add(result.getString("user"));
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setItems(developers);
    }

    public void add(ActionEvent event) {
        try{
            if(titleField.getText().isEmpty() || deadlineField.getEditor().getText().isEmpty() || descriptionField.getText().isEmpty())
                JOptionPane.showMessageDialog(null,"Please enter all fields");
            else{
                try {
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    List<String> numbers = listView.getItems();
                    String listString = String.join("\n", numbers);
                    AddProjectModel.addProject(this.titleField.getText(),this.descriptionField.getText(),this.comboBox.getValue().toString(),listString,date,this.deadlineField.getEditor().getText());
                    Connection con = DBConnection.getConnection();
                    List <String> devUsers= listView.getSelectionModel().getSelectedItems();

                    for(int i=0;i<devUsers.size();i++) {
                        String projects = new String();
                        String query = "select Projects from developers where user =?";
                        PreparedStatement statement = con.prepareStatement(query);
                        statement.setString(1, devUsers.get(i));
                        ResultSet result = statement.executeQuery();
                        while (result.next()) {
                            if (result.getString("Projects") == null) {
                            } else {
                                projects += result.getString("Projects") + "\n";
                            }
                        }
                        projects += this.titleField.getText();
                        String update = "update developers set Projects =? where user =?";
                        PreparedStatement statement1 = con.prepareStatement(update);
                        statement1.setString(1, projects);
                        statement1.setString(2, devUsers.get(i));
                        statement1.executeUpdate();

                    }
                    String projects2 =new String();
                    String query2 = "select Projects from projectmanagers where user =?";
                    PreparedStatement statement2 = con.prepareStatement(query2);
                    statement2.setString(1, this.comboBox.getValue());
                    ResultSet result2 = statement2.executeQuery();
                    while (result2.next()) {
                        if (result2.getString("Projects") == null) {

                        } else {
                            projects2 += result2.getString("Projects") + "\n";
                        }
                    }
                    projects2 += this.titleField.getText();
                    String update2 = "update projectmanagers set Projects = ? where user = ?";
                    PreparedStatement statement3 = con.prepareStatement(update2);
                    statement3.setString(1, projects2);
                    statement3.setString(2, this.comboBox.getValue());
                    statement3.executeUpdate();
                    GlobalVariables.projects.add(this.titleField.getText());

                    JOptionPane.showMessageDialog(null,"You have successfully added project!");
                    Stage stage = (Stage) this.addButton.getScene().getWindow();
                    stage.close();
                    con.close();
                    if(GlobalVariables.mode==2)
                        new AdminDashboard().start(new Stage());
                    else
                        new ProjectManagerDashboard().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void back(ActionEvent event) {
        try {
            Stage stage = (Stage) this.addButton.getScene().getWindow();
            stage.close();
            if(GlobalVariables.mode==2)
                new AdminDashboard().start(new Stage());
            else if(GlobalVariables.mode==1)
                new ProjectManagerDashboard().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
