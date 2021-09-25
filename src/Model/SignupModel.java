import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.*;

public class SignupModel {
    Connection connection;
    public SignupModel(){
        try{
            this.connection = DBConnection.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean isConnected(){
        return this.connection!=null;
    }
    @FXML
    public void signUp(String user, String pass, String fname, String lname, String email, String date, String option) throws  Exception {
        String insert;
        if(option.equals("Developer"))
            insert = "INSERT INTO developers(user,pass,fname,lname,email,DOB) VALUES(?,?,?,?,?,?)";
        else
            insert = "INSERT INTO projectmanagers(user,pass,fname,lname,email,DOB) VALUES(?,?,?,?,?,?)";


        this.connection = DBConnection.getConnection();
        PreparedStatement statement = this.connection.prepareStatement(insert);
        statement.setString(1,user);
        statement.setString(2,pass);
        statement.setString(3,fname);
        statement.setString(4,lname);
        statement.setString(5,email);
        statement.setString(6,date);
        statement.execute();
        this.connection.close();
    }

}
