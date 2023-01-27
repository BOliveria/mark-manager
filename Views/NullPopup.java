import javax.swing.*;
import java.awt.*;

public class NullPopup extends Popup{
    
    private JFrame window;
    private JLabel message;
    private double windowWidth;
    private double windowHeight;

    NullPopup() {
        windowWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        windowWidth = windowWidth/3;
        windowHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        windowHeight = windowHeight/6;
    }

    void display() {
        window = new JFrame("Error Popup");
        window.setLayout(new FlowLayout());

        message = new JLabel("That record does not exist in the database.");

        window.add(message);
        window.setSize((int)windowWidth, (int)windowHeight);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
