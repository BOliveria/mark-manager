import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class MainView implements ActionListener{

    private JFrame mainWindow;

    private JPanel topPanel;

    private JPanel addCoursePanel;
    private JButton addCourseButton;
    private JTextField inputNewCourse;

    private JPanel deleteCoursePanel;
    private JButton deleteCourseButton;
    private JTextField inputDelete;

    private JPanel switchCoursePanel;
    private JButton switchButton;

    private JPanel addMarkPanel;
    private JButton addMarkButton;
    private JTextField inputName, inputMark, inputWorth;

    private JPanel deleteMarkPanel;
    private JButton deleteMarkButton;
    private JTextField inputMarkDelete;

    private JPanel averagePanel;
    private JLabel averageLabel, averageItself;


    private JPanel bottomPanel;

    private double screenWidth;
    private double screenHeight;

    private JComboBox<String> courseSelection = new JComboBox<String>();
    private Course state;
    private Courses copyOfCourses;
    public static String databaseFilePath;

    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    
    MainView() throws IOException {

        databaseFilePath = System.getProperty("user.dir") + "/Util/database.txt";
        BufferedReader br = new BufferedReader(new FileReader(databaseFilePath));
        
        if (br.readLine() == null) {
            copyOfCourses = new Courses();
            br.close();
        } else {
            copyOfCourses = Courses.readFromDatabase(databaseFilePath);
            for (Course courseName : copyOfCourses.getCourses().values()) {
                courseSelection.addItem(courseName.getName());
            }
            br.close();
        }

        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.2;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.2;
        
        mainWindow = new JFrame();
        mainWindow.setTitle("Mark Manager");
        mainWindow.setLayout(null);

        topPanel = new JPanel();

        addCoursePanel = new JPanel();
        addCourseButton = new JButton("Add Course     ");
        inputNewCourse = new JTextField("Name of course     ");
        addCoursePanel.add(addCourseButton);
        addCoursePanel.add(inputNewCourse);
        addCourseButton.setLayout(new FlowLayout());
        addCourseButton.addActionListener(this);

        deleteCoursePanel = new JPanel();
        deleteCourseButton = new JButton("Delete Course     ");
        inputDelete = new JTextField("Name of course     ");
        deleteCoursePanel.add(deleteCourseButton);
        deleteCoursePanel.add(inputDelete);
        deleteCoursePanel.setLayout(new FlowLayout());
        deleteCourseButton.addActionListener(this);

        switchCoursePanel = new JPanel();
        switchButton = new JButton("Switch Courses     ");
        switchCoursePanel.add(switchButton);
        switchCoursePanel.add(courseSelection);
        switchCoursePanel.setLayout(new FlowLayout());
        switchButton.addActionListener(this);

        addMarkPanel = new JPanel();
        addMarkButton = new JButton("Add Mark     ");
        inputName = new JTextField("Evaluation Name   ");
        inputMark = new JTextField("Grade Received   ");
        inputWorth = new JTextField("Percentage (ie. 0.5)   ");
        addMarkPanel.add(addMarkButton);
        addMarkPanel.add(inputName);
        addMarkPanel.add(inputMark);
        addMarkPanel.add(inputWorth);
        addMarkPanel.setLayout(new FlowLayout());
        addMarkButton.addActionListener(this);

        deleteMarkPanel = new JPanel();
        deleteMarkButton = new JButton("Delete Mark     ");
        inputMarkDelete = new JTextField("Mark to delete     ");
        deleteMarkPanel.add(deleteMarkButton);
        deleteMarkPanel.add(inputMarkDelete);
        deleteMarkPanel.setLayout(new FlowLayout());
        deleteMarkButton.addActionListener(this);

        averagePanel = new JPanel();
        averageLabel = new JLabel("Average for this course: ");
        averageItself = new JLabel("0");
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

        table = new JTable(1, 3);
        table.getColumnModel().getColumn(0).setHeaderValue("Evaluation Name");
        table.getColumnModel().getColumn(1).setHeaderValue("Evaluation Mark Received");
        table.getColumnModel().getColumn(2).setHeaderValue("Evaluation Percentage/Worth");
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        model = (DefaultTableModel) table.getModel();
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.setBounds(0, (int)screenHeight/4, (int)screenWidth, ((int)screenHeight/4)*3);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setVisible(true);
        bottomPanel.setBackground(Color.black);

        mainWindow.add(topPanel);
        mainWindow.add(bottomPanel);
        mainWindow.setSize((int)screenWidth, (int)screenHeight);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addCourseButton) {
            String newCourseName = inputNewCourse.getText();
            if (newCourseName.equals("")) {
                Popup popup = new InvalidPopup();
                popup.display();
            } else {
                if (copyOfCourses.addCourse(newCourseName)) {
                    Popup popup = new ConfirmPopup();
                    courseSelection.addItem(newCourseName);
                    popup.display();
                } else {
                    Popup popup = new DupePopup();
                    popup.display();
                }
            }
        } else if (e.getSource() == deleteCourseButton) {
            String deleteName = inputDelete.getText();
            if (deleteName.equals("")) {
                Popup popup = new InvalidPopup();
                popup.display();
            } else {
                if (copyOfCourses.deleteCourse(deleteName)) {
                    Popup popup = new RemoveConfirmPopup();
                    courseSelection.removeItem(deleteName);
                    popup.display();
                } else {
                    Popup popup = new NullPopup();
                    popup.display();
                }
            }
        } else if (e.getSource() == switchButton) {
            String courseSwitch = (String) courseSelection.getSelectedItem();
            state = copyOfCourses.getCourses().get(courseSwitch);
            model.setRowCount(0);

            HashMap<String, Mark> tmp = state.getMarks();
            for (Mark mark : tmp.values()) {
                String name = mark.getName();
                String grade = Double.toString(mark.getMark());
                String worth = Double.toString(mark.getPercentage());
                String[] values = {name, grade, worth};
                model.addRow(values);
            }
            this.updateAverage();
            Popup popup = new SwitchPopup(courseSwitch);
            popup.display();

        } else if (e.getSource() == addMarkButton) {
            String markName = inputName.getText();
            String tmp1 = inputMark.getText();
            String tmp2 = inputWorth.getText();

            if (markName.equals("") || tmp1.equals("") || tmp2.equals("")) {
                Popup popup = new InvalidPopup();
                popup.display();
            } else {
                double mark = Double.valueOf(inputMark.getText());
                double percentage = Double.valueOf(inputWorth.getText());
                if (state.addMark(markName, mark, percentage)) {
                    copyOfCourses.writeToDatabase(databaseFilePath);
                    String[] values = {markName, tmp1, tmp2};
                    model.addRow(values);
                    Popup popup = new ConfirmPopup();
                    popup.display();
                    this.updateAverage();
                } else {
                    Popup popup = new DupePopup();
                    popup.display();
                }   
            }
        } else if (e.getSource() == deleteMarkButton) {
            String deleteThis = inputMarkDelete.getText();
            if (deleteThis.equals("")) {
                Popup popup = new InvalidPopup();
                popup.display();
            } else {
                if (state.deleteMark(deleteThis)) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String tmp = (String)model.getValueAt(i, 0);
                        if (tmp.equals(deleteThis)) {
                            model.removeRow(i);
                            break;
                        }
                    }
                    copyOfCourses.writeToDatabase(deleteThis);
                    Popup popup = new RemoveConfirmPopup();
                    popup.display();
                    this.updateAverage();
                } else {
                    Popup popup = new NullPopup();
                    popup.display();
                }
            }
        }
    }

    private void updateAverage() {
        double average = state.calculateAverage();
        String tmpAverage = Double.toString(average);
        averageItself.setText(tmpAverage);
    }

    public static void main(String[] args) {
        try {
            MainView tmp = new MainView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
