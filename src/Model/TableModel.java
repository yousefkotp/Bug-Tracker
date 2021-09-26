public class TableModel {
    String type,user,pass,fname,lname,email,DOB,Projects,doneprojects;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TableModel(String type, String user, String pass, String fname, String lname, String email, String DOB, String projects) {
        this.type=type;
        this.user = user;
        this.pass = pass;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.DOB = DOB;
        Projects = projects;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getProjects() {
        return Projects;
    }

    public void setProjects(String projects) {
        Projects = projects;
    }

    public String getDoneprojects() {
        return doneprojects;
    }

    public void setDoneprojects(String doneprojects) {
        this.doneprojects = doneprojects;
    }
}