public class ProjectsTableModel {
    String title;
    String description;
    String projectmanager;
    String developers;
    String datestarted;
    String deadline;
    String isDone;
    public ProjectsTableModel() {
    }

    public ProjectsTableModel(String title, String description, String projectmanager, String datestarted, String deadline, String isDone) {
        this.title = title;
        this.description = description;
        this.projectmanager = projectmanager;
        this.datestarted = datestarted;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public ProjectsTableModel(String title, String description, String projectmanager, String developers, String datestarted, String deadline, String isDone) {
        this.title = title;
        this.description = description;
        this.projectmanager = projectmanager;
        this.developers = developers;
        this.datestarted = datestarted;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectmanager() {
        return projectmanager;
    }

    public void setProjectmanager(String projectmanager) {
        this.projectmanager = projectmanager;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(String datestarted) {
        this.datestarted = datestarted;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }
}
