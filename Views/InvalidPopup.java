import javax.swing.*;
import java.awt.*;

public class InvalidPopup extends Popup {
    
    private JFrame window;
    private JLabel message;
    private double windowWidth;
    private double windowHeight;

    InvalidPopup() {
        windowWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        windowWidth = windowWidth/3;
        windowHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        windowHeight = windowHeight/6;
    }

    void display() {
        window = new JFrame("Error Window");
        window.setLayout(new FlowLayout());

        message = new JLabel("Please fill in all necessary information.");

        window.add(message);
        window.setSize((int)windowWidth, (int)windowHeight);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
