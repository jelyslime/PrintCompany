package menus;

import PrintFactory.Employee.Employee;
import util.inputControl.ControlledInput;
import PrintFactory.Employee.Manager;
import PrintFactory.Employee.Operator;

import java.util.Scanner;

public class employeeCreator {
    public employeeCreator() {
    }

    public static Employee start() {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        System.out.println(System.lineSeparator() + "Enter employee name");
        String name = new String(scen.nextLine());
        System.out.println("1.Hire employee as manager");
        System.out.println("2.Hire employee as operator");
        boolean flager = true;
        while (flager) {
            input = inputControl.start(input);
            if (input == 1) {
                break;
            }
            if (input == 2) {
                break;
            } else {
                continue;
            }
        }
        if (input == 1) {
            return new Manager(name);
        } else {
            return new Operator(name);
        }
    }
}
