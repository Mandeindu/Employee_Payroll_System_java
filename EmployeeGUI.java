import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Custom Exception for Text Format
class TextFormatException extends Exception {
    public TextFormatException(String message) {
        super(message);
    }

    public TextFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Utility Class for Text Formatting
class TextFormatter {
    public static void formatText(String text) throws TextFormatException {
        if (!text.matches("[a-zA-Z ]+")) {
            throw new TextFormatException("only alphabets are allowed , no Numbers allowed.");
        }
    }
}

// Employee and Payroll classes (Assumed for completeness)
abstract class Employee {
    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends Employee {
    private double annualSalary;

    public FullTimeEmployee(String name, int id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculatePay() {
        return annualSalary / 12; // Monthly pay
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int hoursWorked;

    public PartTimeEmployee(String name, int id, double hourlyWage, int hoursWorked) {
        super(name, id);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyWage * hoursWorked;
    }
}

class Payroll {
    public double calculateTotalPay(Employee employee) {
        return employee.calculatePay();
    }
}

// GUI Class
public class EmployeeGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField idField;
    private JTextField salaryField;
    private JTextField hourlyWageField;
    private JTextField hoursWorkedField;
    private JTextArea resultArea;
    private JComboBox<String> employeeTypeCombo;

    public EmployeeGUI() {
        // Initialize frame
        frame = new JFrame("Employee Payroll System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 5, 5)); // Adding padding between components

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Employee Type:"));
        employeeTypeCombo = new JComboBox<>(new String[]{"Full-Time", "Part-Time"});
        inputPanel.add(employeeTypeCombo);

        inputPanel.add(new JLabel("Annual Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        inputPanel.add(new JLabel("Hourly Wage:"));
        hourlyWageField = new JTextField();
        inputPanel.add(hourlyWageField);

        inputPanel.add(new JLabel("Hours Worked:"));
        hoursWorkedField = new JTextField();
        inputPanel.add(hoursWorkedField);

        // Add input panel to frame
        frame.add(inputPanel, BorderLayout.CENTER);

        // Panel for buttons and result
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout()); // To align button and result

        JButton calculateButton = new JButton("Calculate Salary");
        controlPanel.add(calculateButton, BorderLayout.NORTH);
        
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        controlPanel.add(scrollPane, BorderLayout.CENTER);

        // Add control panel to frame
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Button click action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSalary();
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }

    private void calculateSalary() {
        String name = nameField.getText();

        // Validate name using TextFormatter
        try {
            TextFormatter.formatText(name); // Using formatText method for validation
        } catch (TextFormatException e) {
            resultArea.setText("Error: " + e.getMessage());
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid ID! Must be a number.");
            return;
        }
        String employeeType = (String) employeeTypeCombo.getSelectedItem();

        double salary = 0;
        double hourlyWage = 0;
        int hoursWorked = 0;

        try {
            if (employeeType.equals("Full-Time")) {
                salary = Double.parseDouble(salaryField.getText());
            } else if (employeeType.equals("Part-Time")) {
                hourlyWage = Double.parseDouble(hourlyWageField.getText());
                hoursWorked = Integer.parseInt(hoursWorkedField.getText());
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input!");
            return;
        }

        Employee employee;
        if (employeeType.equals("Full-Time")) {
            employee = new FullTimeEmployee(name, id, salary);
        } else {
            employee = new PartTimeEmployee(name, id, hourlyWage, hoursWorked);
        }

        Payroll payroll = new Payroll();
        double pay = payroll.calculateTotalPay(employee);
        resultArea.setText("Employee: " + name + "\n" +
                           "ID: " + id + "\n" +
                           "Type: " + employeeType + "\n" +
                           "Calculated Salary: $" + pay);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeGUI::new);
    }
}
