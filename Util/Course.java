import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a course a student may be taking and will
 * contain the course name as well as a collection of the marks/evaluations
 * for a course
 */
public class Course implements Serializable {
    
    //Name of the course
    private String courseName;
    //Collection of the marks/evaluations for the course
    private HashMap<String, Mark> marks;
    //Average grade for a course
    private double average;

    /**
     * The constructor simply creates an empty hash map and initializes
     * the name of the course
     * @param courseName The name of the course to be created
     */
    Course(String courseName) {
        this.courseName = courseName;
        this.marks = new HashMap<String, Mark>();
        this.average = 0;
    }

    /**
     * @return The name of the course
     */
    public String getName() {
        return this.courseName;
    }

    /**
     * @return The hash map containing mark objects
     */
    public HashMap<String, Mark> getMarks() {
        return this.marks;
    }

    /**
     * This method will take mark information and then check if the mark can be
     * added to the course. A mark will only be rejected if it already exists in 
     * the hash map
     * @param markName Name of the mark to add
     * @param mark Grade received of the mark to add
     * @param percentage Worth/percentage of the mark to add
     * @return A boolean indicating if the add was successful or not
     */
    public boolean addMark(String markName, double mark, double percentage) {
        if (marks.containsKey(markName)) {
            return false;
        } else {
            Mark newMark = new Mark(markName, mark, percentage);
            marks.put(markName, newMark);
            return true;
        }
    }

    /**
     * This method will take a mark name and see if it in the marks collection to be deleted
     * @param markName Name of the mark to be deleted
     * @return A boolean indicating if the delete was successful or not
     */
    public boolean deleteMark(String markName) {
        if (marks.containsKey(markName)) {
            marks.remove(markName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Program will do a very general calculation of a student's average
     * in a course by adding all the grades of the marks and then dividing that
     * sum by the number of marks in the course
     * @return
     */
    public double calculateAverage() {

        this.average = 0;
        int count = 0;

        for (Mark evaluation : marks.values()) {
            this.average += evaluation.getMark();
            count++;
        }

        this.average = this.average/count;
        //Rounding the average to 2 decimal places for readability
        this.average = Math.round(this.average * 100.0) / 100.0;

        return this.average;
    }

}


