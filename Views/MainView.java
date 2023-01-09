import javax.swing.*;
import java.awt.*;

public class MainView {

    private JFrame mainWindow;

    private JComboBox courseSelection;

    private JPanel topPanel;
    private JPanel addCoursePanel;
    private JPanel deleteCoursePanel;
    private JPanel switchCoursePanel;
    private JPanel addMarkPanel;
    private JPanel deleteMarkPanel;
    private JPanel averagePanel;

    private JPanel bottomPanel;
    

    private double screenWidth;
    private double screenHeight;

    MainView() {
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.2;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.2;
        
        mainWindow = new JFrame();
        mainWindow.setTitle("Mark Manager");
        mainWindow.setLayout(null);
        mainWindow.setLocationRelativeTo(null);

        topPanel = new JPanel();

        addCoursePanel = new JPanel();
        JButton addCourseButton = new JButton("Add Course     ");
        JTextField inputNewCourse = new JTextField("Name of course     ");
        addCoursePanel.add(addCourseButton);
        addCoursePanel.add(inputNewCourse);
        addCourseButton.setLayout(new FlowLayout());

        deleteCoursePanel = new JPanel();
        JButton deleteCourseButton = new JButton("Delete Course     ");
        JTextField inputDelete = new JTextField("Name of course     ");
        deleteCoursePanel.add(deleteCourseButton);
        deleteCoursePanel.add(inputDelete);
        deleteCoursePanel.setLayout(new FlowLayout());

        switchCoursePanel = new JPanel();
        JButton switchButton = new JButton("Switch Courses     ");
        courseSelection = new JComboBox<String>();
        switchCoursePanel.add(switchButton);
        switchCoursePanel.add(courseSelection);
        switchCoursePanel.setLayout(new FlowLayout());

        addMarkPanel = new JPanel();
        JButton addMarkButton = new JButton("Add Mark     ");
        JTextField inputName, inputMark, inputWorth;
        inputName = new JTextField("Evaluation Name   ");
        inputMark = new JTextField("Grade Received   ");
        inputWorth = new JTextField("Percentage (ie. 0.5)   ");
        addMarkPanel.add(addMarkButton);
        addMarkPanel.add(inputName);
        addMarkPanel.add(inputMark);
        addMarkPanel.add(inputWorth);
        addMarkPanel.setLayout(new FlowLayout());

        deleteMarkPanel = new JPanel();
        JButton deleteMarkButton = new JButton("Delete Mark     ");
        JTextField inputMarkDelete = new JTextField("Mark to delete     ");
        deleteMarkPanel.add(deleteMarkButton);
        deleteMarkPanel.add(inputMarkDelete);
        deleteMarkPanel.setLayout(new FlowLayout());

        averagePanel = new JPanel();
        JLabel averageLabel = new JLabel("Average for this course: ");
        JLabel averageItself = new JLabel("0");
        averagePanel.add(averageLabel);
        averagePanel.add(averageItself);


        topPanel.add(addCoursePanel);
        topPanel.add(addMarkPanel);
        topPanel.add(deleteCoursePanel);
        topPanel.add(deleteMarkPanel);
        topPanel.add(switchCoursePanel);
        topPanel.add(averagePanel);
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.setBounds(0, 0, (int)screenWidth, (int)screenHeight/4);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.setVisible(true);



        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, (int)screenHeight/4, (int)screenWidth, ((int)screenHeight/4)*3);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setVisible(true);

        mainWindow.add(topPanel);
        mainWindow.add(bottomPanel);
        mainWindow.setSize((int)screenWidth, (int)screenHeight);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        MainView tmp = new MainView();
    }
}
