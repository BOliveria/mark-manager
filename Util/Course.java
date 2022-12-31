import java.io.Serializable;
import java.util.HashMap;

public class Course implements Serializable {
    
    private String courseName;
    private HashMap<String, Mark> marks;
    private double average;

    Course(String courseName) {
        this.courseName = courseName;
        this.marks = new HashMap<String, Mark>();
        this.average = 0;
    }

    public String getName() {
        return this.courseName;
    }

    public HashMap<String, Mark> getMarks() {
        return this.marks;
    }

    public double getAverage() {
        return this.average;
    }

    public boolean addMark(String markName, double mark, double percentage) {
        if (marks.containsKey(markName)) {
            return false;
        } else {
            Mark newMark = new Mark(markName, mark, percentage);
            marks.put(markName, newMark);
            return true;
        }
    }

    public boolean deleteMark(String markName) {
        if (marks.containsKey(markName)) {
            marks.remove(markName);
            return true;
        } else {
            return false;
        }
    }

    public double calculateAverage() {

        this.average = 0;
        int count = 0;

        for (Mark evaluation : marks.values()) {
            this.average += evaluation.getMark();
            count++;
        }

        this.average = this.average/count;
        this.average = Math.round(this.average * 100.0) / 100.0;

        return this.average;
    }

}


