import java.util.StringTokenizer;
/**
 * This class defines the profile for a banking customer.
 * @author Aum Pathak, Tyler Sarno
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    /**
    constructor for the profile object.
    @param fname the first name of the customer.
    @param lname the last name of the customer.
    @param dob the date of birth of the customer in string form, converted
    to a date object within the constructor.
     */
    public Profile (String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * constructor for profile that takes in full name as 1 string to save space.
     * @param name full name of customer.
     * @param dob date of birth of the customer.
     */
    public Profile (String name, Date dob) {
        StringTokenizer str = new StringTokenizer(name, " ");
        this.fname = str.nextToken();
        this.lname = str.nextToken();
        this.dob = dob;
    }

    /**
    convert profile to a string.
    @return a string of all the info about the profile.
     */
    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString();
    }

    /**
    check to see if two profiles are equal.
    @param obj the object you are comparing.
    @return true if the two profiles are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            return this.toString().equals(obj.toString());
        }
        return false;
    }
}
