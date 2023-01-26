import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Courses implements Serializable {
    
    private HashMap<String, Course> courses;

    Courses() {
        this.courses = new HashMap<String, Course>();
    }

    public HashMap<String, Course> getCourses() {
        return this.courses;
    }

    public boolean addCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            return false;
        } else {
            courses.put(courseName, new Course(courseName));
            this.writeToDatabase(MainView.databaseFilePath);
            return true;
        }
    }

    public boolean deleteCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            courses.remove(courseName);
            return true;
        } else {
            return false;
        }
    }

    private void writeToDatabase(String filePath) {
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

    public static Courses readFromDatabase(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
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
