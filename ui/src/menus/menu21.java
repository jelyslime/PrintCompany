package menus;

import PrintFactory.factory.PrintFactory;
import PrintFactory.Employee.Employee;
import util.inputControl.ControlledInput;

import java.util.ArrayList;
import java.util.Scanner;

public class menu21 {
    public menu21() {
    }

    public static void start(PrintFactory factory) {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        boolean alive = true;
        while (alive) {
            System.out.println(System.lineSeparator());
            System.out.println("1.Print all employees");
            System.out.println("2.Print information for specific employee");
            System.out.println("3.Hire new employee");
            System.out.println("4.Hire new employees");
            System.out.println("5.Fire employee");
            System.out.println("6.Revaluate managers salaries");
            System.out.println("7.Go back");

            input = inputControl.start(input);
            Employee res = null;
            switch (input) {
                case 1:
                    System.out.println(System.lineSeparator() + factory.allEmployeesReport());
                    break;
                case 2:
                    System.out.println(System.lineSeparator() + "Enter employee name");
                    String name = scen.nextLine();
                    res = factory.getSpecificEmployee(name);
                    if (res == null) {
                        System.out.println("Employee don't exist.");
                    } else {
                        System.out.println(res.toString());
                    }
                    res = null;
                    name = null;
                    break;
                case 3:
                    res = employeeCreator.start();
                    factory.hireEmployee(res);
                    System.out.println("Employee added.");
                    res = null;
                    break;
                case 4:
                    ArrayList<Employee> list = new ArrayList<Employee>(empolyeeListSubmenu.start());
                    factory.hireEmployee(list);
                    System.out.println("Employees added.");
                    list = null;
                    break;
                case 5:
                    System.out.println(System.lineSeparator() + "Enter employee name");
                    name = scen.nextLine();
                    res = factory.getSpecificEmployee(name);
                    if (res == null) {
                        System.out.println("Employee don't exist.");
                    } else {
                        factory.fireEmployee(res);
                        System.out.println("Employee fired.");
                    }
                    name = null;
                    res = null;
                    break;
                case 6:
                    factory.incManagersSalary();
                    System.out.println("Revaluation done.");
                    break;
                case 7:
                    alive = false;
                    break;
                default:
                    break;
            }


        }
    }

}

