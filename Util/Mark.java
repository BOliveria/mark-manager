import java.io.Serializable;

public class Mark implements Serializable {
    
    private String markName;
    private double mark, percentage;

    Mark(String markName, double mark, double percentage) {
        this.markName = markName;
        this.mark = mark;
        this.percentage = percentage;
    }

    public String getName() {
        return this.markName;
    }

    public double getMark() {
        return this.mark;
    }

    public double getPercentage() {
        return this.percentage;
    }

}