import java.sql.Date;

public class TicketModel {
    private String title;
    private String description;
    private String submittedBy;
    private String assignedTo;
    private String feature;
    private Date dateOfSubmission;
    private int priority; //0 for low, 1 for medium, 2 for high, 3 for immediate
    private int severity; //0 for low, 1 for minor, 2 for major, 3 for critical
    private int status;  //0 to do, 1 doing, 2 done

}
