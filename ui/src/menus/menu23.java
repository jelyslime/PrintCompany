package menus;

import PrintFactory.factory.PrintFactory;
import util.inputControl.ControlledInput;
import PrintFactory.Employee.Employee;

import java.util.Scanner;

public class menu23 {
    public menu23() {
    }

    public static void start(PrintFactory factory) {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        boolean alive = true;
        while (alive) {
            System.out.println(System.lineSeparator());
            System.out.println("1.Print all employees");
            System.out.println("2.Print all machines");
            System.out.println("3.Print earnings");
            System.out.println("4.Print costs");
            System.out.println("5.Print employee salaries");
            System.out.println("6.Print income");
            System.out.println("7.Print earnings bonus level");
            System.out.println("8.Change earnings bonus level");
            System.out.println("9.Go back");

            input = inputControl.start(input);
            Employee res = null;
            switch (input) {
                case 1:
                    System.out.println(System.lineSeparator() + factory.allEmployeesReport());
                    break;
                case 2:
                    System.out.println(System.lineSeparator() + factory.allPrintMachinesReport());
                    break;
                case 3:
                    System.out.println("Earnings:" + factory.getEarnings());
                    break;
                case 4:
                    System.out.println("Costs:" + factory.getCost());
                    break;
                case 5:
                    System.out.println("Employee salaries:" + factory.getSalaries());
                    break;
                case 6:
                    System.out.println("Income" + factory.getIncome());
                    break;
                case 7:
                    System.out.println("Earnings bonus level:" + factory.getEarningsIncomeBonus());
                    break;
                case 8:
                    double incomeBonus = 0;
                    incomeBonus = inputControl.start(input);
                    factory.setIncomeBonus(incomeBonus);
                    System.out.println("Procedure executed.");
                    break;
                case 9:
                    alive = false;
                    break;
                default:
                    break;
            }


        }
    }
}