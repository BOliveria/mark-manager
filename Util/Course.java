import java.util.HashMap;

public class Course {
    
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
        float percentLeft = 100;

        for (Mark evaluation : marks.values()) {
            percentLeft -= evaluation.getPercentage();
            this.average += evaluation.getMark() * evaluation.getPercentage();
        }

        this.average += percentLeft * 100;

        return this.average;
    }

    public static void main(String[] args) {
        Course tmp = new Course("CS1026");
        if (tmp.addMark("Test", 80.0, 0.10)) {
            System.out.println("Woot woot");
        } else {
            System.out.println("Darn");
        }

        System.out.println(tmp.calculateAverage());
    
    }
}


