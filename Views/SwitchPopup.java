import javax.swing.*;
import java.awt.*;

public class SwitchPopup extends Popup {
    
    private JFrame window;
    private JLabel message;
    private double windowWidth;
    private double windowHeight;
    private String courseName;

    SwitchPopup(String name) {

        this.courseName = name;

        windowWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        windowWidth = windowWidth/3;
        windowHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        windowHeight = windowHeight/6;
    }

    void display() {

        window = new JFrame("Confirmation Window");
        window.setLayout(new FlowLayout());

        message = new JLabel("You have switched to the course: " + this.courseName);

        window.add(message);
        window.setSize((int)windowWidth, (int)windowHeight);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
