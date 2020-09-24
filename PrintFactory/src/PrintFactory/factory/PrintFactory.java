package PrintFactory.factory;

/*
    When printing is done it will have two
    functions for printing one for specific machine to
     start the print one to start all the machines
    when do so before this all the selected machines have to
     be first loaded with paper then added request print size.
     when those steps are done printfactory before starting
     the print process have to recalculate the earnings by using
     interface function calls for each print machine becouse
     after this the requested print size will be restored to zero
     and will be no more avalible to calculate the costs and earnings
     after the print it have to be chekked what are the incoms and are they more
     then then said so it can be added more to the managers;
     */
//when printing is executed before it a countDown have to be created!!



import PrintFactory.CostCalculator.CostCalculator;
import PrintFactory.Employee.Employee;
import PrintFactory.Employee.Manager;
import PrintFactory.IncomeCalculator.IncomeCalculator;
import PrintFactory.Machine.PrintMachine;
import PrintFactory.elements.Page;
import PrintFactory.paperExeptions.PaperMismatch;
import PrintFactory.paperExeptions.PaperOverflow;
import PrintFactory.paperExeptions.PaperOverload;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class PrintFactory implements CostCalculator, IncomeCalculator, Serializable {
    private String name;
    ArrayList<PrintMachine> printMachinesList;
    ArrayList<Employee> employeeList;
    double earnings; //income - cost
    double income;
    double cost;
    double salaries;
    double earningsIncomeBonus; //value on witch the salary of the workers is inscreased

    //constructors


    public PrintFactory(String name, ArrayList<PrintMachine> printMachinesList, ArrayList<Employee> employeeList, double earningsIncomeBonus) {
        this.name = new String(name);
        this.printMachinesList = new ArrayList<PrintMachine>(printMachinesList);
        this.employeeList = new ArrayList<Employee>(employeeList);
        this.earningsIncomeBonus = earningsIncomeBonus;
        this.income = 0;
        this.earnings = 0; //no printigs are made yet so no earnings
        this.revaluateSalariesCosts();

    }


    //setters and getters;

    protected void incSalary(double value) {
        this.salaries += value;
    }

    protected void incIncome(double value) {
        this.income += value;
    }

    protected void incCost(double value) {
        this.cost += value;
    }

    protected void incEarnings(double value) {
        this.earnings += value;
    }

    protected void decIncome(double value) {
        this.income -= value;
    }

    protected void decCost(double value) {
        this.cost -= value;
    }

    protected void decEarnings(double value) {
        this.earnings -= value;
    }

    protected void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    protected void setIncome(double income) {
        this.income = income;
    }

    protected void setCost(double cost) {
        this.cost = cost;
    }

    public void setSalaries(double salaries) {
        this.salaries = salaries;
    }

    public void setIncomeBonus(double incomeBonus) {
        this.earningsIncomeBonus = incomeBonus;
    }


    public String getName() {
        return name;
    }

    public ArrayList<PrintMachine> getPrintMachinesList() {
        return printMachinesList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public double getEarnings() {
        return earnings;
    }

    public double getIncome() {
        return income;
    }

    public double getCost() {
        return cost;
    }

    public double getSalaries() {
        return salaries;
    }

    public double getEarningsIncomeBonus() {
        return earningsIncomeBonus;
    }


    //employee related actions
    public Employee getSpecificEmployee(String name) {
        for (Employee i : employeeList) {
            if (i.getName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    public Employee getSpecificEmployee(Employee toFind) {
        for (Employee i : employeeList) {
            if (i.equals(toFind)) {
                return i;
            }
        }
        return null;
    }

    public void revaluateSalariesCosts() {
        decCost(getSalaries());
        setSalaries(0);

        for (Employee i : employeeList) {
            this.incSalary(i.getTotalEarnings());
        }
        incCost(getSalaries());
    }

    public boolean checkEarningsLevel() {
        if (getEarnings() >= getEarningsIncomeBonus()) {
            return true;
        }
        return false;
    }

    public void incManagersSalary() {
        if (!checkEarningsLevel()) {
            return;
        } else {
            double factor = ((earnings - earningsIncomeBonus) / 100) * 3;// 3 procents bonus of the bonus income
            for (Employee i : employeeList) {
                if (i instanceof Manager) {
                    i.calculateIncome(factor);
                } else {
                    continue;
                }

            }
            this.revaluateSalariesCosts();
        }
    }

    public void fireEmployee(String name) {
        for (Employee i : employeeList) {
            if (i.getName().toLowerCase().equals(name.toLowerCase())) {
                employeeList.remove(i);
                break;
            }
        }
        this.revaluateSalariesCosts();
    }


    public void fireEmployee(Employee i) {
        employeeList.remove(i);
        this.revaluateSalariesCosts();
    }

    public void hireEmployee(Employee i) {
        employeeList.add(i);
        this.revaluateSalariesCosts();
    }

    public void hireEmployee(ArrayList<Employee> i) {
        for (Employee s : i) {
            employeeList.add(s);
        }
        this.revaluateSalariesCosts();
    }

    public String allEmployeesReport() {
        StringBuilder s = new StringBuilder();
        s.append(System.lineSeparator());
        for (Employee i : employeeList) {
            s.append(i.toString() + System.lineSeparator());
        }
        return s.toString();
    }


    //machine operations
    public PrintMachine getSpecificMachine(String name) {
        for (PrintMachine i : printMachinesList) {
            if (i.getName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    public PrintMachine getSpecificMachine(PrintMachine toFind) {
        for (PrintMachine i : printMachinesList) {
            if (i.equals(toFind)) {
                return i;
            }
        }
        return null;
    }

    public String allPrintMachinesReport() {
        StringBuilder s = new StringBuilder();
        s.append(System.lineSeparator());
        for (PrintMachine i : printMachinesList) {
            s.append(i.toString() + System.lineSeparator());
        }
        return s.toString();
    }


    public void addPrintMachine(PrintMachine i) {
        printMachinesList.add(i);
    }

    public void addPrintMachine(ArrayList<PrintMachine> e) {

        for (PrintMachine i : e) {
            PrintMachine res = new PrintMachine(i);
            printMachinesList.add(res);
        }
    }

    public void removePrintMachine(PrintMachine i) {
        printMachinesList.remove(i);
    }

    public void reloadAllMachinesPaper(int size, Page Type) {
        for (PrintMachine i : printMachinesList) {
            try {
                i.loadPages(size, Type, true);
            } catch (PaperOverload e) {
                System.out.println("Paper Overload: loaded size: " + size + " maximum machine size: " + i.getMaximumLoad());
            } catch (PaperMismatch e) {
                System.out.println("Different type of paper not supported! Loaded paper: " + Type.toString() + " current paper" + i.getPageType().toString());
            }
        }
    }

    public void addAllMachinesPaper(int size, Page Type) {
        System.out.println("TIP! Load same type paper!");
        for (PrintMachine i : printMachinesList) {
            try {
                i.loadPages(size, Type, false);
            } catch (PaperOverload e) {
                System.out.println("Paper Overload: loaded size: " + size + " maximum machine size: " + i.getMaximumLoad());
            } catch (PaperMismatch e) {
                System.out.println("Different type of paper not supported! Loaded paper: " + Type.toString() + " current paper" + i.getPageType().toString());
            }
        }
    }

    public void addSpecificMachinesPaper(String name, int size, Page Type) {
        System.out.println("TIP! Load same type paper!");
        for (PrintMachine i : printMachinesList) {
            if (i.getName().toLowerCase().equals(name)) {
                try {
                    i.loadPages(size, Type, false);
                    break;
                } catch (PaperOverload e) {
                    System.out.println("Paper Overload: loaded size: " + size + " maximum machine size: " + i.getMaximumLoad());
                    break;
                } catch (PaperMismatch e) {
                    System.out.println("Different type of paper not supported! Loaded paper: " + Type.toString() + " current paper" + i.getPageType().toString());
                    break;
                }

            } else {
                System.out.println("Machine not existing.");

            }
        }
    }

    public void reloadSpecificMachinesPaper(String name, int size, Page Type) {
        for (PrintMachine i : printMachinesList) {
            if (i.getName().toLowerCase().equals(name)) {
                try {
                    i.loadPages(size, Type, true);
                    break;
                } catch (PaperOverload e) {
                    System.out.println("Paper Overload: loaded size: " + size + " maximum machine size: " + i.getMaximumLoad());
                    break;
                } catch (PaperMismatch e) {
                    System.out.println("Different type of paper not supported! Loaded paper: " + Type.toString() + " current paper" + i.getPageType().toString());
                    break;
                }
            } else {
                System.out.println("Machine not existing.");
            }
        }
    }

    public void requestPrintOnAllMachines(int size) {
        for (PrintMachine i : printMachinesList) {
            try {
                i.requestPrint(size);
            } catch (PaperOverflow e) {
                System.out.println("Paper Request bigger then current load!" + "Requested:" + size + " On Load:" + i.getCurrentLoad() + " .");
            }
        }
    }

    public void requestPrintOnSpecificMachine(String name, int size) {
        for (PrintMachine i : printMachinesList) {
            if (i.getName().toLowerCase().equals(name)) {
                try {
                    i.requestPrint(size);
                    break;
                } catch (PaperOverflow e) {
                    System.out.println("Paper Request bigger then current load!" + "Requested:" + size + " On Load:" + i.getCurrentLoad() + " .");
                    break;
                }
            }
        }
    }


    public void executePrintOnAllMachines(CountDownLatch latch) {
        System.out.println("Starting the printing procedure");
        int counter = 1;
        for (PrintMachine i : printMachinesList) {
            incCost(calculateCosts(i));
            incIncome(calculateIncome(i));
            // Thread s = new Thread(i,i.getName());
            i.printing(counter * 1000, latch, i.getName());
        }
        recalculateEarnings();


    }

    public void executePrintOnSpecificMachine(String name, CountDownLatch latch) {
        System.out.println("Starting the printing procedure on specific machine");
        PrintMachine meho = getSpecificMachine(name);
        incCost(calculateCosts(meho));
        incIncome(calculateIncome(meho));
        recalculateEarnings();
        //meho.printing();
        //Thread s = new Thread(meho,meho.getName());
        meho.printing(1000, latch, name);
    }


    public void printFactoryFullReport(String destinationWrite) //print info to file
    {
        String toWrite;
        if (destinationWrite.equals("1")) {
            toWrite = new String("file1.txt");
        } else {
            toWrite = new String(destinationWrite);
        }
        try (FileWriter fo = new FileWriter(toWrite)) {
            fo.write(this.toString());
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }

    }

    public void serializeMe(String destinationWrite) {
        String toWrite;
        if (destinationWrite.equals("1")) {
            toWrite = new String("file2.ser");
        } else {
            toWrite = new String(destinationWrite);
        }
        try (FileOutputStream fos = new FileOutputStream(toWrite); ObjectOutputStream outputStream
                = new ObjectOutputStream(fos)) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println(e);
        }

    }


    //
    void recalculateEarnings() {
        setEarnings(getIncome() - getCost());
    }


    //overrides


    @Override
    public double calculateCosts(PrintMachine i) {
        double sum;
        sum = i.getPageType().getPrintType().getValue();
        sum *= i.getPageType().getPaper().getValue();
        sum *= i.getCurrentLoad();
        return sum;
    }

    @Override
    public double calculateIncome(PrintMachine i) {
        double sum = this.calculateCosts(i);
        sum *= ((sum / 100) * 20);
        return sum;
    }

    @Override
    public String toString() {
        return "PrintFactory{" + '\n' +
                "name='" + name + '\'' + '\n' +
                "printMachinesList=" + allPrintMachinesReport() + '\n' +
                "employeeList=" + allEmployeesReport() + '\n' +
                "earnings=" + earnings + '\n' +
                "income=" + income + '\n' +
                "cost=" + cost + '\n' +
                "salaries=" + salaries + '\n' +
                "earningsIncomeBonus=" + earningsIncomeBonus + '\n' +
                '}';
    }
}
