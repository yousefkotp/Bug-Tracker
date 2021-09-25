import com.mysql.cj.log.Log;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    public static int mode =0; //0 for personnel, 1 for projects

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private TextArea descriptionText;
    @FXML
    private TextArea developersText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField projectmanagerText;
    @FXML
    private TextField datestartedText;
    @FXML
    private TextField deadlineText;
    @FXML
    private TextField isDoneText;
    @FXML
    private MenuButton menuButton;
    @FXML
    private TableView<TableModel> table;
    @FXML
    private TableColumn<TableModel, String> type_col;
    @FXML
    private TableColumn<TableModel, String> username_col;
    @FXML
    private TableColumn<TableModel, String> password_col;
    @FXML
    private TableColumn<TableModel, String> firstname_col;
    @FXML
    private TableColumn<TableModel, String> lastname_col;
    @FXML
    private TableColumn<TableModel, String> email_col;
    @FXML
    private TableColumn<TableModel, String> dob_col;
    @FXML
    private TableColumn<TableModel, String> projects_col;
    @FXML
    private ProjectsTableModel old;

    public void setCellValueFactory(){
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("user"));
        password_col.setCellValueFactory(new PropertyValueFactory<>("pass"));
        firstname_col.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        dob_col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        projects_col.setCellValueFactory(new PropertyValueFactory<>("Projects"));
    }
    public void reload(){
        menuButton.getItems().clear();
        titleText.setText("");
        deadlineText.setText("");
        datestartedText.setText("");
        isDoneText.setText("");
        developersText.setAccessibleText("");
        projectmanagerText.setText("");
        descriptionText.setText("");
        developersText.setText("");
        menuButton.setText("Select Project");
        try {
            Connection con = DBConnection.getConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT * from projects");
            ArrayList<ProjectsTableModel> list = new ArrayList<>();
            while(result.next()){
                list.add(new ProjectsTableModel(result.getString("title"),result.getString("description"),result.getString("projectmanager"),result.getString("developers"),result.getString("datestarted"),result.getString("deadline"),result.getString("isDone")));
            }
            for (int i = 0; i < list.size(); i++) {
                MenuItem item = new MenuItem(list.get(i).getTitle());
                int finalI = i;
                item.setOnAction(event -> {
                    titleText.setText(list.get(finalI).getTitle());
                    deadlineText.setText(list.get(finalI).getDeadline());
                    datestartedText.setText(list.get(finalI).getDatestarted());
                    isDoneText.setText(list.get(finalI).getIsDone());
                    developersText.setAccessibleText(list.get(finalI).getDevelopers());
                    projectmanagerText.setText(list.get(finalI).getProjectmanager());
                    descriptionText.setText(list.get(finalI).getDescription());
                    developersText.setText(list.get(finalI).getDevelopers());
                    item.setText(list.get(finalI).getTitle());
                    menuButton.setText(list.get((finalI)).getTitle());

                });
                menuButton.getItems().add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        titleText.setEditable(false);
        projectmanagerText.setEditable(false);
        datestartedText.setEditable(false);
        deadlineText.setEditable(false);
        isDoneText.setEditable(false);
        developersText.setEditable(false);
        descriptionText.setEditable(false);
        ObservableList<TableModel> oblist = FXCollections.observableArrayList();
        try {
            Connection con = DBConnection.getConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT * from developers");
            while(result.next()){
                oblist.add(new TableModel("Developer",result.getString("user"),result.getString("pass"),result.getString("fname"),result.getString("lname"),result.getString("email"),result.getString("DOB"),result.getString("Projects")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DBConnection.getConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT * from projectmanagers");
            while(result.next()){
                oblist.add(new TableModel("Project Manager",result.getString("user"),result.getString("pass"),result.getString("fname"),result.getString("lname"),result.getString("email"),result.getString("DOB"),result.getString("Projects")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setCellValueFactory();
        table.setItems(oblist);
        reload();

    }

    public void setEditable(ActionEvent event) {
        if(mode ==0){
            table.setEditable(true);
            username_col.setCellFactory(TextFieldTableCell.forTableColumn());
            password_col.setCellFactory(TextFieldTableCell.forTableColumn());
            email_col.setCellFactory(TextFieldTableCell.forTableColumn());
            firstname_col.setCellFactory(TextFieldTableCell.forTableColumn());
            lastname_col.setCellFactory(TextFieldTableCell.forTableColumn());
        }else{
            saveButton.setVisible(true);
            cancelButton.setVisible(true);
            old = new ProjectsTableModel(titleText.getText(),descriptionText.getText(),projectmanagerText.getText(),developersText.getText(),datestartedText.getText(),deadlineText.getText(),isDoneText.getText());
            titleText.setEditable(true);
            projectmanagerText.setEditable(true);
            datestartedText.setEditable(true);
            deadlineText.setEditable(true);
            isDoneText.setEditable(true);
            developersText.setEditable(true);
            descriptionText.setEditable(true);
        }
    }



    public void removeButton(ActionEvent event) {
        table.setEditable(false);
        if(mode ==0){
            int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?");
            if(input==0){
                try{
                    ObservableList<TableModel> all,single;
                    TableModel model = table.getSelectionModel().getSelectedItem();
                    all = table.getItems();
                    single =table.getSelectionModel().getSelectedItems();
                    single.forEach(all::remove);
                    String query=null;
                    if(model.type=="Developer")
                        query = "delete from developers where fname = ? and lname =?";
                    else
                        query ="delete from projectmanagers where fname = ? and lname =?";
                    Connection con = DBConnection.getConnection();
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1,model.getFname());
                    statement.setString(2,model.getLname());
                    statement.execute();
                    con.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Kindly select any row");
                }
            }
        }else{
            String title = this.menuButton.getText();
            int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?");
            if(input==0){
                try{
                    String query=null;
                    query = "delete from projects where title = ?";
                    Connection con = DBConnection.getConnection();
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1,title);
                    statement.execute();
                    con.close();
                    reload();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Kindly select any row");
                }
            }
        }


    }
    public void usernameEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setUser(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set user = ? where fname=?";
        else
            query = "update projectmanagers set user = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getUser());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }
    public void doneprojectsEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setDoneprojects(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set doneprojects = ? where fname=?";
        else
            query = "update projectmanagers set doneprojects = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getDoneprojects());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }
    public void projectsEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setProjects(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set projects = ? where fname=?";
        else
            query = "update projectmanagers set projects = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getProjects());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }
    public void dobEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setDOB(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set DOB = ? where fname=?";
        else
            query = "update projectmanagers set DOB = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getDOB());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }
    public void emailEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setEmail(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set email = ? where fname=?";
        else
            query = "update projectmanagers set email = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getEmail());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }
    public void lastnameEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setLname(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set lname = ? where fname=?";
        else
            query = "update projectmanagers set lname = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getLname());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }

    public void firstnameEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setFname(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set fname = ? where lname=?";
        else
            query = "update projectmanagers set fname = ? where lanme = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getFname());
        statement.setString(2,model.getLname());
        statement.executeUpdate();
        con.close();
    }

    public void passwordEdit(TableColumn.CellEditEvent<TableModel, String> tableModelStringCellEditEvent) throws SQLException {
        TableModel model = table.getSelectionModel().getSelectedItem();
        model.setPass(tableModelStringCellEditEvent.getNewValue());
        String query = null;
        Connection con = DBConnection.getConnection();
        if(model.type=="Developer")
            query = "update developers set pass = ? where fname=?";
        else
            query = "update projectmanagers set pass = ? where fname = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1,model.getPass());
        statement.setString(2,model.getFname());
        statement.executeUpdate();
        con.close();
    }


    public void projectsButton() {
        mode=1;
    }


    public void personnelButton(Event event) {
        mode =0;
    }

    public void back(ActionEvent event) {
        try {
            LoginController.flag=true;
            Stage stage = (Stage) this.addButton.getScene().getWindow();
            stage.close();
            new Login().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void add(){
        if(mode ==0){
            try {
                LoginController.flag=true;
                Stage stage = (Stage) this.addButton.getScene().getWindow();
                stage.close();
                new Signup().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                GlobalVariables.mode=2;
                Stage stage = (Stage) this.addButton.getScene().getWindow();
                stage.close();
                new AddProject().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void save(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null,"Are you sure you want to save?");
        if(input==0){
            try{
                String   query = "delete from projects where title = ?";
                Connection con = DBConnection.getConnection();
                PreparedStatement statement2 = con.prepareStatement(query);
                statement2.setString(1,old.getTitle());
                statement2.execute();
                String query2 ="INSERT INTO projects(title,description,projectmanager,developers,datestarted,deadline,isDone) VALUES(?,?,?,?,?,?,?) ";
                PreparedStatement statement = con.prepareStatement(query2);
                statement.setString(1,titleText.getText());
                statement.setString(2,descriptionText.getText());
                statement.setString(3,projectmanagerText.getText());
                statement.setString(4,developersText.getText());
                statement.setString(5,datestartedText.getText());
                statement.setString(6,deadlineText.getText());
                statement.setString(7,isDoneText.getText());
                statement.execute();
                con.close();
                titleText.setEditable(false);
                projectmanagerText.setEditable(false);
                datestartedText.setEditable(false);
                deadlineText.setEditable(false);
                isDoneText.setEditable(false);
                developersText.setEditable(false);
                descriptionText.setEditable(false);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void cancel(ActionEvent event) {
        titleText.setEditable(false);
        projectmanagerText.setEditable(false);
        datestartedText.setEditable(false);
        deadlineText.setEditable(false);
        isDoneText.setEditable(false);
        developersText.setEditable(false);
        descriptionText.setEditable(false);
        titleText.setText(old.getTitle());
        projectmanagerText.setText(old.getProjectmanager());
        datestartedText.setText(old.getDatestarted());
        deadlineText.setText(old.getDeadline());
        isDoneText.setText(old.getIsDone());
        developersText.setText(old.getDevelopers());
        descriptionText.setText(old.getDescription());
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
    }
}
