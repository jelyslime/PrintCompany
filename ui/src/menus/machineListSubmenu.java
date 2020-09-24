package menus;

import PrintFactory.Machine.PrintMachine;
import util.inputControl.ControlledInput;

import java.util.ArrayList;
import java.util.Scanner;

public class machineListSubmenu {
    public machineListSubmenu() {

    }

    public static ArrayList<PrintMachine> start() {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        System.out.println(System.lineSeparator() + "Enter the number of machines you wish to add");
        input = inputControl.start(input);
       machineCreator machCr = new machineCreator();
        ArrayList<PrintMachine> arr = new ArrayList<PrintMachine>();
        for (int i = 0; i < input; i++) {
            PrintMachine toAdd = new PrintMachine(machineCreator.start());
            arr.add(toAdd);
        }
        return arr;
    }
}
