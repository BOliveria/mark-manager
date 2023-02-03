import java.io.Serializable;

/**
 * This class represents a mark/evaluation a student may receive
 */
public class Mark implements Serializable {
    
    //The name of the evaluation (Quiz 1, Midterm 1, Asn1, etc.)
    private String markName;
    //The worth and mark received for the evaluation
    private double mark, percentage;

    /**
     * The constructor of this class requires all the details of the evaluation
     * and initializes that data into the object
     * @param markName Name of Evaluation
     * @param mark Grade received for the evaluation
     * @param percentage  Worth of the evaluation 
     */
    Mark(String markName, double mark, double percentage) {
        this.markName = markName;
        this.mark = mark;
        this.percentage = percentage;
    }

    /** 
     * Method retrieves the name of the evaluation
     * @return String The name of the evaluation
     */
    public String getName() {
        return this.markName;
    }
    
    /** 
     * Method retrieves the mark received for the evaluation
     * @return double Mark received
     */
    public double getMark() {
        return this.mark;
    }

    /** 
     * Method retrieves the percentage/worth of the evaluation
     * @return double Weight of evaluation 
     */
    public double getPercentage() {
        return this.percentage;
    }

}