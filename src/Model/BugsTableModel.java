

public class BugsTableModel {
    String title,severity,priority,reporter,assignedto,description,status,dateofsubmission,project;

    public BugsTableModel(String title, String severity, String priority, String reporter, String status, String dateofsubmission) {
        this.title = title;
        this.severity = severity;
        this.priority = priority;
        this.reporter = reporter;
        this.status = status;
        this.dateofsubmission = dateofsubmission;
    }

    public BugsTableModel(String title, String severity, String priority, String reporter, String assignedto, String status, String dateofsubmission) {
        this.title = title;
        this.severity = severity;
        this.priority = priority;
        this.reporter = reporter;
        this.assignedto = assignedto;
        this.status = status;
        this.dateofsubmission = dateofsubmission;
    }

    public BugsTableModel(String title, String severity, String priority, String reporter, String description, String status, String dateofsubmission, String project) {
        this.title = title;
        this.severity = severity;
        this.priority = priority;
        this.reporter = reporter;
        this.description = description;
        this.status = status;
        this.dateofsubmission = dateofsubmission;
        this.project = project;
    }

    public BugsTableModel(String title, String severity, String priority, String reporter, String assignedto, String description, String status, String dateofsubmission, String project) {
        this.title = title;
        this.severity = severity;
        this.priority = priority;
        this.reporter = reporter;
        this.assignedto = assignedto;
        this.description = description;
        this.status = status;
        this.dateofsubmission = dateofsubmission;
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateofsubmission() {
        return dateofsubmission;
    }

    public void setDateofsubmission(String dateofsubmission) {
        this.dateofsubmission = dateofsubmission;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
