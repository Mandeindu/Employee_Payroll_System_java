public class FullTimeEmployee extends Employee {
    private double annualSalary;

    // Constructor
    public FullTimeEmployee(String name, int id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    // Getter and Setter for annualSalary
    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateSalary() {
        // Return monthly salary
        return annualSalary / 12;
    }
}
