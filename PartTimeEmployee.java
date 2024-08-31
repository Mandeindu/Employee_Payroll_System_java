public class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int hoursWorked;

    // Constructor
    public PartTimeEmployee(String name, int id, double hourlyWage, int hoursWorked) {
        super(name, id);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    // Getter and Setter for hourlyWage
    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    // Getter and Setter for hoursWorked
    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        // Calculate total pay for part-time employee
        return hourlyWage * hoursWorked;
    }
}

