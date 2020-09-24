package PrintFactory.Employee;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;



public abstract class Employee implements EmployeeIncomeCalculator, Serializable {
    private String name;
    protected static double baseEarnings = 300; //in leva
    private double workedHours; //for 24h;
    private double experience;
    protected double totalEarnings;

    public Employee() {
        name = new String("");
        workedHours = 0;
        experience = 0;
    }

    public Employee(String name) //creating new employee means he haves 0 experience and worked hours
    {
        this.name = new String(name);
    }

    public Employee(@NotNull Employee toCpy) {
        this.name = new String(toCpy.name);
        this.experience = toCpy.experience;
        this.workedHours = toCpy.workedHours;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    protected void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getBaseEarnings() {
        return baseEarnings;
    }

    public static void setBaseEarnings(double baseEarnings) {
        Employee.baseEarnings = baseEarnings;
    }


    public double getWorkedHours() {
        return workedHours;
    }

    public int setWorkedHours(double workedHours) {
        if (this.workedHours > 24) {
            System.out.printf("Worked hours limit per day reached");
            return -1;
        } else {
            this.workedHours += workedHours;
            return 1;
        }
    }

    public void addAndRefleshWorkedHours() {
        this.setExperience(this.workedHours);
        this.workedHours = 0;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience += experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.workedHours, workedHours) == 0 &&
                Double.compare(employee.experience, experience) == 0 &&
                Double.compare(employee.totalEarnings, totalEarnings) == 0 &&
                Objects.equals(name, employee.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", workedHours=" + workedHours +
                ", experience=" + experience +
                ", experience=" + totalEarnings +
                '}';
    }
}
