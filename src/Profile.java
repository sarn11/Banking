/**
 * @author Tyler Sarno
 * This class defines the profile for a banking customer
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    //Constructor for the profile object
    public Profile (String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

}
