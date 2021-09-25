import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GlobalVariables {

    public static String username;
    public static String password;
    public static String email;
    public static String firstname;
    public static String lastname;
    public static String dob;
    public static ArrayList<String> projects= new ArrayList<String>();
    public static int mode =0; //0 for developer, 1 for project manager
    public static ArrayList<String> doneprojects = new ArrayList<String>();
    public static String title,description,dateofsumbission,reporter,severity,priority;
    public static String done;
    public static String project;
    public static void getInfo(String user,String pass,String option) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet result =null;
        String query=null;
        if(option.equals("Developer"))
            query = "SELECT fname, lname, email, DOB, Projects FROM developers WHERE user=? AND pass=?";
        else if (option.equals("ProjectManager"))
            query = "SELECT fname, lname, email, DOB, Projects FROM projectmanagers WHERE user=? AND pass=?";
        statement = con.prepareStatement(query);
        statement.setString(1,user);
        statement.setString(2,pass);
        result = statement.executeQuery();
        while(result.next()){
            firstname=result.getString("fname");
            lastname = result.getString("lname");
            email = result.getString("email");
            dob = result.getString("DOB");
            if(result.getString("Projects")==null){

            }else{
                StringTokenizer tokenizer = new StringTokenizer(result.getString("Projects"),"\n");
                while(tokenizer.hasMoreTokens()){
                    projects.add(tokenizer.nextToken());
                }
            }
            int n =projects.size();
            for(int i=0;i<projects.size();i++){
                String query2 ="select isDone from projects where title = ?";
                PreparedStatement statement1 = con.prepareStatement(query2);
                statement1.setString(1,projects.get(i));
                ResultSet result2 = statement1.executeQuery();
                while(result2.next()){
                    if(result2.getString("isDone").equals("YES")){
                        doneprojects.add(projects.get(i));
                        projects.remove(i);
                    }
                }
            }
        }

        con.close();
    }
}
