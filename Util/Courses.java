import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * This class represents the collection of courses a student may be taking.
 * This class is also responsible for writing and reading to/from the database
 * which is simply a .txt file and all objects are written to the .txt file
 * using the Serializable implementation
 */
public class Courses implements Serializable {
    
    //Collection of all the courses a student may be taking
    private HashMap<String, Course> courses;

    /**
     * Simple initializes the courses hash map
     */
    Courses() {
        this.courses = new HashMap<String, Course>();
    }

    /**
     * Retrieves the hash map of the courses
     * @return Hash map collection of the courses in the database
     */
    public HashMap<String, Course> getCourses() {
        return this.courses;
    }

    /**
     * This method will allow one to add a course to the database and also 
     * checks if the course already exists in the database to avoid duplication
     * @param courseName Name of the course to be added to the database
     * @return A boolean indicating if adding the course was successful or not
     */
    public boolean addCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            return false;
        } else {
            courses.put(courseName, new Course(courseName));
            this.writeToDatabase(MainView.databaseFilePath);
            return true;
        }
    }

    /**
     * This method will allow one to delete a course from the database and also 
     * verifies first if the course is in the database to avoid errors
     * @param courseName The name of the course to be deleted from the database
     * @return A boolean indicating if the removal was successful or not
     */
    public boolean deleteCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            courses.remove(courseName);
            this.writeToDatabase(MainView.databaseFilePath);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method will take the courses object containg all course and mark data and then write it
     * to a database.txt file using the Serializable implementation
     * @param filePath The path name of where the database.txt file is located
     */
    public void writeToDatabase(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception e) {
            Popup errorMsg = new ErrorPopup();
            errorMsg.display();
        }
    }

    /**
     * This method is only used at the beginning of program execution to read all the courses data
     * from the database.txt file and then store it in a variable in MainView.java
     * @param filePath The path name of where the database.txt file is located
     * @return A boolean indicating if the read was successsful or not
     */
    public static Courses readFromDatabase(String filePath) {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Courses reference = (Courses) objectIn.readObject();
            objectIn.close();
            return reference;
        } catch (Exception e) {
            Popup error = new ErrorPopup();
            error.display();
            return null;
        }
    }

}
