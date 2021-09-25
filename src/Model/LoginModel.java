import java.sql.*;

public class LoginModel {
    Connection connection;
    public LoginModel(){
        try{
            this.connection = DBConnection.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean isConnected(){
        return this.connection!=null;
    }

    public boolean isLogin(String user, String pass,String option) throws  Exception{
            PreparedStatement statement =null;
            ResultSet result =null;
            String query;
            if(option.equals("Admin"))
                query = "SELECT * FROM admins where user = ? and pass = ?";
            else if(option.equals("ProjectManager"))
                query = "SELECT * FROM projectmanagers where user = ? and pass = ?";
            else
                query = "SELECT * FROM developers where user=? and pass = ?";
            try{
                statement = this.connection.prepareStatement(query);
                statement.setString(1,user);
                statement.setString(2,pass);
                result = statement.executeQuery();
                if(result.next())
                    return true;
                else
                    return false;
            }catch (SQLException e){
                return false;
            }finally {
                statement.close();
                result.close();
            }
    }
}
