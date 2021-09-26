import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewBugController implements Initializable {
    public Label titleLabel,descriptionLabel,dateofsubmissionLabel,reporterLabel,commentsLabel,severityLabel,priorityLabel;
    public TextField commentLabel;
    public Button cancelButton,doneButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText(GlobalVariables.title);
        dateofsubmissionLabel.setText(GlobalVariables.dateofsumbission);
        reporterLabel.setText(GlobalVariables.reporter);
        severityLabel.setText(GlobalVariables.severity);
        priorityLabel.setText(GlobalVariables.priority);
        //description and comments
        try {
            Connection con = DBConnection.getConnection();
            String query = "select description from bugs where title = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,GlobalVariables.title);
            ResultSet result = statement.executeQuery();
            while(result.next())
                descriptionLabel.setText(result.getString("description"));

            query = "select comments from bugs where title =?";
            PreparedStatement statement1 = con.prepareStatement(query);
            statement1.setString(1,GlobalVariables.title);
            ResultSet result1 = statement1.executeQuery();
            while(result1.next())
                commentsLabel.setText(result1.getString("comments"));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void done(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to mark this bug as done?");
        if(input==0){
            try {
                Connection con = DBConnection.getConnection();
                String query = "update bugs set status =? ";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,"Done");
                statement.executeUpdate();
                con.close();
                Stage stage = (Stage) this.cancelButton.getScene().getWindow();
                stage.close();
                if(GlobalVariables.mode==0)
                    new DeveloperDashboard().start(new Stage());
                else
                    new ProjectManagerDashboard().start(new Stage());
            } catch (SQLException | IOException e) {
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

    public void comment(ActionEvent event) {
        String comment= new String("");
        try {
            Connection con = DBConnection.getConnection();
            String query = "Select comments from bugs where title =?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,GlobalVariables.title);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(result.getString("comments")==null)
                    break;
                comment += result.getString("comments");
            }
            comment += GlobalVariables.username + ": "+commentLabel.getText()+"\n";
            commentsLabel.setText(comment);
            String query2 = "update bugs set comments =? where title =?";
            PreparedStatement statement1 = con.prepareStatement(query2);
            statement1.setString(1,comment);
            statement1.setString(2,GlobalVariables.title);
            statement1.executeUpdate();
            commentLabel.setText("");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
