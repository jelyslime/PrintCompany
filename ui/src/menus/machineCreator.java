package menus;

import PrintFactory.Machine.PrintMachine;
import PrintFactory.elements.Page;
import util.enums.*;
import util.inputControl.ControlledInput;

import java.util.Scanner;

public class machineCreator {
    public machineCreator() {
    }

    public static PrintMachine start() {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        System.out.println(System.lineSeparator() + "Enter machine name");
        String name = new String(scen.nextLine());
        System.out.println("Enter machine serial number");
        String serialNumber = new String(scen.nextLine());
        System.out.println("Enter machine date of purchase");
        String dateOfPurchase = new String(scen.nextLine());
        System.out.println("Enter machine warranty date");
        String warrantyDate = new String(scen.nextLine());
        System.out.println("Enter maximum load capasity on machine");
        boolean correctInputFlag = true;
        int maxCap = 0;
        maxCap = inputControl.start(input);
        System.out.println(System.lineSeparator() + "Press 1 if you want to load the machine with paper now Or 2 to skip");
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
            paperCreator pep = new paperCreator();
            Page page = new Page(pep.start());
            System.out.println("Enter load size.");
            input = inputControl.start(input);
            return new PrintMachine(name, serialNumber, warrantyDate, dateOfPurchase, page, input, maxCap);
        } else {
            int currentLoad = 0;
            return new PrintMachine(name, serialNumber, warrantyDate, dateOfPurchase, new Page(FORMAT.NONE, PAPER.NORMAL, PRINT_TYPE.NONE), 0, maxCap);
        }
    }
}
