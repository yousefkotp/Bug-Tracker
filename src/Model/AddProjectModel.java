import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddProjectModel {
    public static void addProject(String title, String description, String projectmanager, String developers, String datestarted, String deadline) throws  Exception {
        String  insert = "INSERT INTO projects(title,description,projectmanager,datestarted,deadline,developers) VALUES(?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1,title);
        statement.setString(2,description);
        statement.setString(3,projectmanager);
        statement.setString(4,datestarted);
        statement.setString(5,deadline);
        statement.setString(6,developers);
        statement.execute();
        connection.close();
    }

}
