import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class is the main view to the user and controls the overall flow of the program.
 * It creates the view using the javax swing and awt and implements the buttons to make
 * use of all the methods created in the Util folder
 */
public class MainView implements ActionListener{

    private JFrame mainWindow;

    /**
     * This top panel is where all the user interaction takes place as the bottom panel is simply the
     * display of all the course information
     */
    private JPanel topPanel;

    //These three lines are for adding a new course to the database
    private JPanel addCoursePanel;
    private JButton addCourseButton;
    private JTextField inputNewCourse;

    //These three lines are deleting an existing course from the database
    private JPanel deleteCoursePanel;
    private JButton deleteCourseButton;
    private JTextField inputDelete;

    //These two lines are for switching from one course to another
    private JPanel switchCoursePanel;
    private JButton switchButton;

    //These three lines are for adding new marks to a selected course
    private JPanel addMarkPanel;
    private JButton addMarkButton;
    private JTextField inputName, inputMark, inputWorth;

    //These three lines are for deleting existing marks from a selected course
    private JPanel deleteMarkPanel;
    private JButton deleteMarkButton;
    private JTextField inputMarkDelete;

    //These two lines display the student's loose average for a course
    private JPanel averagePanel;
    private JLabel averageLabel, averageItself;

    /**
     * This bottom panel is where all the mark information for a course is outputted and this panel
     * alters the table by updating it with a different courses marks when selected by the user
     */
    private JPanel bottomPanel;

    //The dimensions of the user's screen
    private double screenWidth;
    private double screenHeight;

    //A visible list to the user of all their current courses in the database
    private JComboBox<String> courseSelection = new JComboBox<String>();
    //The course currently being edited in the UI
    private Course state;
    //A reference to all the courses (mainly used to write to the database)
    private Courses copyOfCourses;
    //Path of the database.txt file 
    public static String databaseFilePath;

    //These three lines are for the table which displays all course marks
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    
    /**
     * The consturctor of this class will initialize all of the above variables to be visible on the
     * screen as well as arranges them to be easily readable for the user
     * @throws IOException This is here due to opening the database.txt file for reading information
     */
    MainView() throws IOException {

        /**
         * Lines 90-100 will look into the database.txt file and check if any data exists. If not, then it
         * creates a brand new courses object for future uses. If data does already exist, it is stored
         * in a variable and altered throughout the user's time with the program
         */
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

        /**
         * Lines 116 - 137 are all related to GUI elements for courses (adding, deleting and switching courses)
         */
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


        /**
         * Lines 143 - 167 are all related to GUI elements for marks (adding, removing and displaying marks)
         */
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

        /**
         * Lines 172 - 181 finalizes the top panel GUI elements by adding everything to the panel
         * and setting the panel's size and visibility
         */
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

        /**
         * Lines 187 - 194 are for creating the base of the table which will display all of the user's marks
         * for a selected course. It also adds a scroll pane in case the user has a lot of marks for a particular course.
         */
        table = new JTable(1, 3);
        table.getColumnModel().getColumn(0).setHeaderValue("Evaluation Name");
        table.getColumnModel().getColumn(1).setHeaderValue("Evaluation Mark Received");
        table.getColumnModel().getColumn(2).setHeaderValue("Evaluation Percentage/Worth");
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        model = (DefaultTableModel) table.getModel();
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        /**
         * Lines 200 - 206 finalizes the bottom panel GUI elements in a similar way to the top panel
         */
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

        /**
         * This block of code will allow the user to add courses to the database and outputs an
         * appropriate popup message indicating whether or not the operation was successful
         */
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
        /**
         * This block of code will allow the user to delete courses from the database and outputs an
         * appropriate popup message indicating whether or not the operation was successful
         */
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
        /**
         * This block of code will allow a user to switch to a course in the database and will update the
         * table on screen by removing the old course information and displaying the information of the current
         * state course. It also lets the user know which course they switched to with a popup message in case 
         * they selected the wrong course
         */
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
        
        /**
         * This block of code will allow the user to input a new mark to the database and checks that the user
         * has inputted all the required information for the mark to be created. 
         */
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
        /**
         * This block of code is very similar to the code above for adding a mark but applies to deleting
         * marks from the database
         */
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

    /**
     * This block of code calculates the average of the current state course and will be called whenever
     * a mark is added/deleted to remain accurate.
     */
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
