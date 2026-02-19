import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentTrackerGUI extends JFrame {

    private ArrayList<Integer> grades = new ArrayList<>();
    private JTextField gradeField;
    private JTextArea outputArea;

    public StudentTrackerGUI() {

        setTitle("Student Grade Tracker");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        // Title
        JLabel title = new JLabel("Student Grade Tracker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel gradeLabel = new JLabel("Enter Grade:");
        gradeField = new JTextField(10);

        JButton addButton = new JButton("Add Grade");
        JButton clearButton = new JButton("Clear All");

        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);
        inputPanel.add(clearButton);

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        JButton reportButton = new JButton("Generate Report");
        bottomPanel.add(reportButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(e -> {
            try {
                int grade = Integer.parseInt(gradeField.getText());

                if (grade < 0 || grade > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter marks between 0 and 100.");
                    return;
                }

                grades.add(grade);
                outputArea.append("✔ Added Grade: " + grade + "\n");
                gradeField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number!");
            }
        });

        
        reportButton.addActionListener(e -> {

            if (grades.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No grades entered yet!");
                return;
            }

            double sum = 0;
            for (int grade : grades) {
                sum += grade;
            }

            double average = sum / grades.size();
            int highest = Collections.max(grades);
            int lowest = Collections.min(grades);

            outputArea.append("\n=========== SUMMARY REPORT ===========\n");
            outputArea.append("Total Students : " + grades.size() + "\n");
            outputArea.append("All Grades     : " + grades + "\n");
            outputArea.append("Average Marks  : " + String.format("%.2f", average) + "\n");
            outputArea.append("Highest Marks  : " + highest + "\n");
            outputArea.append("Lowest Marks   : " + lowest + "\n");
            outputArea.append("======================================\n\n");
        });

        clearButton.addActionListener(e -> {
            grades.clear();
            outputArea.setText("");
            JOptionPane.showMessageDialog(this,
                    "All data cleared successfully!");
        });
    }

    public static void main(String[] args) {
        new StudentTrackerGUI().setVisible(true);
    }
}
