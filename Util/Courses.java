import java.io.Serializable;
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
    
}
