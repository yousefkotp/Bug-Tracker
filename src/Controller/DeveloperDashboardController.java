import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class DeveloperDashboardController implements Initializable {

    public Button dashboardButton;
    public Button projectsButton;
    public Button settingsButton;
    public Button logoutButton;
    public Label welcomeLabel;
    public Label activeLabel;
    public Label doneLabel;
    public Label dateLabel;
    public MenuButton menuButton;
    public AnchorPane dashboardPane;
    public AnchorPane projectsPane;
    public AnchorPane settingsPane;
    public Button seeBug;
    public TableView<ProjectsTableModel> table;
    public TableColumn <ProjectsTableModel,String> title_col;
    public TableColumn<ProjectsTableModel,String >description_col;
    public TableColumn<ProjectsTableModel,String> projectmanager_col;
    public TableColumn<ProjectsTableModel,String > datestarted_col;
    public TableColumn<ProjectsTableModel,String> deadline_col;
    public TableColumn<ProjectsTableModel,String> done_col;
    public Label checkLabel;
    public TableView<BugsTableModel> table2;
    public TableColumn<BugsTableModel,String> titleCol;
    public TableColumn<BugsTableModel,String> severityCol;
    public TableColumn<BugsTableModel,String> priorityCol;
    public TableColumn<BugsTableModel,String> reporterCol;
    public TableColumn<BugsTableModel,String> dateofsumbissionCol;
    public TableColumn<BugsTableModel,String> statusCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectsPane.setVisible(false);
        checkLabel.setVisible(false);
        settingsPane.setVisible(false);

        welcomeLabel.setText("Welcome aborad "+GlobalVariables.firstname+" "+GlobalVariables.lastname+"!");
        activeLabel.setText(String.valueOf(GlobalVariables.projects.size()));
        doneLabel.setText(String.valueOf(GlobalVariables.doneprojects.size()));
        dateLabel.setText(String.valueOf(java.time.LocalDate.now()));

        //setting menu Button
        for (int i = 0; i < GlobalVariables.projects.size(); i++) {
            MenuItem item = new MenuItem(GlobalVariables.projects.get(i));
            int finalI1 = i;
            item.setOnAction(event -> {
                Connection con = null;
                try {
                    con = DBConnection.getConnection();
                    ObservableList<BugsTableModel> oblist2 = FXCollections.observableArrayList();
                    item.setText(GlobalVariables.projects.get(finalI1));
                    menuButton.setText(GlobalVariables.projects.get(finalI1));
                    String query = "select * from bugs where assignedto = ? and project=?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1,GlobalVariables.username);
                    statement.setString(2,menuButton.getText());
                    ResultSet result = statement.executeQuery();
                    while(result.next()){
                        oblist2.add(new BugsTableModel(result.getString("title"),result.getString("severity"),result.getString("priority"),result.getString("reporter"),result.getString("status"),result.getString("dateofsubmission")));
                    }
                    con.close();
                    setCellValueFactory();
                    table2.setItems(oblist2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
            menuButton.getItems().add(item);

        }
        //Setting tables
        ObservableList<ProjectsTableModel> oblist = FXCollections.observableArrayList();
        ObservableList<BugsTableModel> oblist2 = FXCollections.observableArrayList();
        try {
            Connection con = DBConnection.getConnection();
            for(int i=0;i<GlobalVariables.projects.size();i++){
                String query = "SELECT * from projects where title =? and isDone='NO'";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,GlobalVariables.projects.get(i));
                ResultSet result = statement.executeQuery();
                while(result.next()){
                    oblist.add(new ProjectsTableModel(result.getString("title"),result.getString("description"),result.getString("projectmanager"),result.getString("datestarted"),result.getString("deadline"),result.getString("isDone")));
                }
            }
            for(int i=0;i<GlobalVariables.doneprojects.size();i++){
                String query = "SELECT * from projects where title =? and isDone = 'YES'";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,GlobalVariables.doneprojects.get(i));
                ResultSet result = statement.executeQuery();
                while(result.next()){
                    oblist.add(new ProjectsTableModel(result.getString("title"),result.getString("description"),result.getString("projectmanager"),result.getString("datestarted"),result.getString("deadline"),result.getString("isDone")));
                }
            }
            String query = "select * from bugs where assignedto = ? and project=? ";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,GlobalVariables.username);
            statement.setString(2,menuButton.getText());
            ResultSet result = statement.executeQuery();
            while(result.next()){
                oblist2.add(new BugsTableModel(result.getString("title"),result.getString("severity"),result.getString("priority"),result.getString("reporter"),result.getString("description"),result.getString("status"),result.getString("dateofsubmission"),result.getString("project")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setCellValueFactory();
        table.setItems(oblist);
        table2.setItems(oblist2);
    }
    public void setCellValueFactory(){
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        projectmanager_col.setCellValueFactory(new PropertyValueFactory<>("projectmanager"));
        datestarted_col.setCellValueFactory(new PropertyValueFactory<>("datestarted"));
        deadline_col.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        done_col.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        reporterCol.setCellValueFactory(new PropertyValueFactory<>("reporter"));
        dateofsumbissionCol.setCellValueFactory(new PropertyValueFactory<>("dateofsubmission"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    public void logout(ActionEvent event) {
        Stage stage = (Stage) this.logoutButton.getScene().getWindow();
        stage.close();
        GlobalVariables.projects.clear();
        GlobalVariables.doneprojects.clear();
        try {
            new Login().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void settingsClicked(ActionEvent event) {
        dashboardPane.setVisible(false);
        projectsPane.setVisible(false);
        settingsPane.setVisible(true);

    }

    public void projectsClicked(ActionEvent event) {
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
        projectsPane.setVisible(true);
    }

    public void dashboardClicked(ActionEvent event) {
        settingsPane.setVisible(false);
        projectsPane.setVisible(false);
        dashboardPane.setVisible(true);
    }

    public void usernameChanged(ActionEvent event) {
        try {
            Connection con = DBConnection.getConnection();
            String input = JOptionPane.showInputDialog(null,"Please enter your new username");
            String query = "update developers set user= ? where pass = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,input);
            statement.setString(2,GlobalVariables.password);
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void passwordChanged(ActionEvent event) {
        try {
            Connection con = DBConnection.getConnection();
            String input = JOptionPane.showInputDialog(null,"Please enter your new password");
            String query = "update developers set pass= ? where user = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,input);
            statement.setString(2,GlobalVariables.username);
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emailChanged(ActionEvent event) {
        try {
            Connection con = DBConnection.getConnection();
            String input = JOptionPane.showInputDialog(null,"Please enter your new email");
            String query = "update developers set email= ? where user = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,input);
            statement.setString(2,GlobalVariables.username);
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void seeBug(ActionEvent event) {
        try{

            GlobalVariables.mode=0;
            BugsTableModel model = table2.getSelectionModel().getSelectedItem();
            GlobalVariables.done= model.getStatus();
            GlobalVariables.done= model.getStatus();
            GlobalVariables.title = model.getTitle();
            GlobalVariables.priority = model.getPriority();
            GlobalVariables.reporter = model.getReporter();
            GlobalVariables.severity = model.getSeverity();
            GlobalVariables.dateofsumbission = model.getDateofsubmission();
            GlobalVariables.project = menuButton.getText();
            Stage stage = (Stage) this.seeBug.getScene().getWindow();
            stage.close();
            try {
                new ViewBug().start(new Stage());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Please choose bug first!");
        }

    }

    public void addBug(ActionEvent event) {
            GlobalVariables.mode=0;

            if(menuButton.getText().equals("Select a Project")){
                JOptionPane.showMessageDialog(null,"Choose project first!");
            }else{
                Stage stage = (Stage) this.seeBug.getScene().getWindow();
                stage.close();
                GlobalVariables.project = menuButton.getText();
                try {
                    new AddBug().start(new Stage());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


}
