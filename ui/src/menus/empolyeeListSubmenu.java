package menus;

import PrintFactory.Employee.Employee;
import util.inputControl.ControlledInput;

import java.util.ArrayList;
import java.util.Scanner;

public class empolyeeListSubmenu {
    public empolyeeListSubmenu() {
    }

    public static ArrayList<Employee> start() {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        System.out.println(System.lineSeparator() + "Enter the number of employees you wish to add");
        input = inputControl.start(input);
        ArrayList<Employee> arr = new ArrayList<Employee>();
        for (int i = 0; i < input; i++) {
            arr.add(employeeCreator.start());
        }
        return arr;

    }
}
