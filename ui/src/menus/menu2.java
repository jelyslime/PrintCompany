package menus;

import PrintFactory.factory.PrintFactory;
import util.inputControl.ControlledInput;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class menu2 {
    public menu2() {
    }

    public static void start(PrintFactory factory, CountDownLatch latch) {
        boolean alive = true;
        while (alive) {
            ControlledInput inputControl = new ControlledInput();
            Scanner scen = new Scanner(System.in);
            int input = 0;
            System.out.println(System.lineSeparator() + "1.Operations involving employees");
            System.out.println("2.Operation involving print machines");
            System.out.println("3.Factory operations");
            System.out.println("4.Write full report on a txt file");
            System.out.println("5.Serialise the factory");
            System.out.println("6.Factory console print");
            System.out.println("7.Exit");
            input = inputControl.start(input);
            switch (input) {
                case 1:
                    menu21.start(factory);
                    break;
                case 2:
                    menu22.start(factory, latch);
                    break;
                case 3:
                    menu23.start(factory);
                    break;
                case 4:
                    System.out.println(System.lineSeparator() + "Enter filepath");
                    System.out.println("*If you enter 1 default filepath will be chosen*");
                    String txtFile = scen.nextLine();
                    factory.printFactoryFullReport(txtFile);
                    System.out.println("Report done.");
                    break;
                case 5:
                    System.out.println(System.lineSeparator() + "Enter filepath");
                    System.out.println("*If you enter 1 default filepath will be chosen*");
                    String serFile = scen.nextLine();
                    factory.serializeMe(serFile);
                    System.out.println("Serialising done");
                    break;
                case 6:
                    System.out.println(factory.toString());
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

