import java.sql.*;
import java.time.Year;
import java.util.Properties;

public class DBConnection<connection> {
    public static final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11440034";
    public static String USERNAME = "sql11440034";
    public static String PASSWORD = "gRSA22iah3";

    public static void main(String[] args) throws SQLException {

    }
    public static Connection getConnection()throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, USERNAME,PASSWORD);
            return con;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
