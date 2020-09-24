package menus;

import PrintFactory.factory.PrintFactory;
import PrintFactory.Employee.Employee;
import PrintFactory.Machine.PrintMachine;
import util.inputControl.ControlledInput;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

//entry menu where is assked to load an .ser file or create a new one
public class menu1 {
    public menu1() {

    }

    public static PrintFactory start() {
        ControlledInput inputControl = new ControlledInput();
        System.out.println("Welcome to your printing firm managment software!" + System.lineSeparator());
        System.out.println("1.Create new printing firm");
        System.out.println("2.Load an existing printing firm");
        System.out.println("3.Exit program");
        Scanner scen = new Scanner(System.in);
        int input = 0;
        boolean flager = true;
        while (flager) {

            input = inputControl.start(input);
            if (input == 1) {
                break;
            }
            if (input == 2) {
                break;
            }
            if (input == 3) {
                return null;
            } else {
                continue;
            }
        }
        if (input == 1) {
            System.out.println(System.lineSeparator() + "Enter your firm name");
            String name = new String(scen.nextLine());
            System.out.println("Enter your printing machines");
            ArrayList<PrintMachine> printMachineArrayList = machineListSubmenu.start();
            System.out.println("Enter your employees");
            ArrayList<Employee> employeeArrayList = empolyeeListSubmenu.start();
            System.out.println("Enter the bonus gap (point at which employees will recive bonus)");
            double max = 0;
            max = inputControl.start(max);
            return new PrintFactory(name, printMachineArrayList, employeeArrayList, max);
        }
        if (input == 2) {
            System.out.println("Enter filepath location");
            String location = scen.nextLine();
            PrintFactory meho = null;
            try (FileInputStream fis = new FileInputStream(location);
                 ObjectInputStream inputStream = new ObjectInputStream(fis);) {

                meho = (PrintFactory) inputStream.readObject();


            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found: " + ex);
            } catch (IOException ex) {
                System.err.println("IO error: " + ex);
            }
            return meho;
        } else {
            return null;
        }
    }

}

