package menus;

import PrintFactory.factory.PrintFactory;
import PrintFactory.elements.Page;
import PrintFactory.Machine.PrintMachine;
import util.inputControl.ControlledInput;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class menu22 {
    public menu22() {
    }

    public static void start(PrintFactory factory, CountDownLatch latch) {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        boolean alive = true;
        int sizeControl = 0;
        Page page = null;
        while (alive) {
            System.out.println(System.lineSeparator());
            System.out.println("1.Print all machines");
            System.out.println("2.Print information for specific machine");
            System.out.println("3.Add new machine");
            System.out.println("4.Add new machines");
            System.out.println("5.Remove machine");
            System.out.println("6.Reload all machines paper");
            System.out.println("7.Add all machines paper");
            System.out.println("**Loads the machine with same paper type**");
            System.out.println("8.Reload specific machine paper");
            System.out.println("9.Add specific machine paper");
            System.out.println("**Loads the machines with the same paper type**");
            System.out.println("10.Request print on all machine");
            System.out.println("**For this command all machines have to be loaded with paper**");
            System.out.println("11.Request print on specific machine");
            System.out.println("**For this command the machine have to be loaded with paper");
            System.out.println("12.Execute print on all machine");
            System.out.println("**This task uses multithreading");
            System.out.println("***All machines have to be loaded with paper and requested amount to print(operation 10 and 11).*** ");
            System.out.println("13.Execute print on specific machine");
            System.out.println("***The machine have to be loaded with paper and requested amount to print(operation 10 and 11)***");
            System.out.println("14.Go back");


            input = inputControl.start(input);
            PrintMachine res = null;
            switch (input) {
                case 1:
                    System.out.println(System.lineSeparator() + factory.allPrintMachinesReport());
                    break;
                case 2:
                    System.out.println(System.lineSeparator() + "Enter machine name.");
                    String name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                    } else {
                        System.out.println(res.toString());
                    }
                    res = null;
                    name = null;
                    break;
                case 3:
                    res = new PrintMachine(machineCreator.start());
                    factory.addPrintMachine(res);
                    System.out.println("Machine added.");
                    res = null;
                    break;
                case 4:
                    ArrayList<PrintMachine> arr = new ArrayList<PrintMachine>(machineListSubmenu.start());
                    factory.addPrintMachine(arr);
                    System.out.println("Machines added.");
                    arr = null;
                    break;
                case 5:
                    System.out.println(System.lineSeparator() + "Enter machine name.");
                    name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                    } else {
                        factory.removePrintMachine(res);
                        System.out.println("Machine removed.");
                        res = null;
                    }
                    break;
                case 6:
                    System.out.println("Enter amount of pages you want to reload.");
                    sizeControl = inputControl.start(sizeControl);
                    System.out.println("Enter pages type.");
                    page = paperCreator.start();
                    factory.reloadAllMachinesPaper(sizeControl, page);
                    System.out.println("Pages reloaded.");
                    sizeControl = 0;
                    page = null;
                    break;
                case 7:
                    System.out.println("Enter amount of pages you want to load.");
                    sizeControl = inputControl.start(sizeControl);
                    System.out.println("Enter pages type.");
                    page = paperCreator.start();
                    factory.addAllMachinesPaper(sizeControl, page);
                    System.out.println("Pages added.");
                    sizeControl = 0;
                    page = null;
                    break;
                case 8:
                    System.out.println(System.lineSeparator() + "Enter machine name.");
                    name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                        name = null;
                    } else {
                        System.out.println("Enter amount of pages you want to reload.");
                        sizeControl = inputControl.start(sizeControl);
                        System.out.println("Enter pages type.");
                        page = paperCreator.start();
                        factory.reloadSpecificMachinesPaper(name, sizeControl, page);
                        System.out.println("Pager reloaded.");
                        sizeControl = 0;
                        page = null;
                        name = null;
                    }
                    break;
                case 9:
                    System.out.println(System.lineSeparator() + "Enter machine name");
                    name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                        name = null;
                    } else {
                        System.out.println("Enter amount of pages you want to load.");
                        sizeControl = inputControl.start(sizeControl);
                        System.out.println("Enter pages type");
                        page = paperCreator.start();
                        factory.addSpecificMachinesPaper(name, sizeControl, page);
                        System.out.println("Pages added.");
                        sizeControl = 0;
                        page = null;
                        name = null;
                    }
                    break;
                case 10:
                    System.out.println("Enter amount of pages you want to print");
                    sizeControl = inputControl.start(sizeControl);
                    factory.requestPrintOnAllMachines(sizeControl);
                    System.out.println("Procedure executed");
                    sizeControl = 0;
                    break;
                case 11:
                    System.out.println(System.lineSeparator() + "Enter machine name.");
                    name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                    } else {
                        System.out.println("Enter amount of pages you want to print");
                        sizeControl = inputControl.start(sizeControl);
                        factory.requestPrintOnSpecificMachine(name, sizeControl);
                        System.out.println("Procedure executed");
                        sizeControl = 0;
                        res = null;
                    }
                    break;
                case 12:
                    latch = new CountDownLatch(factory.getPrintMachinesList().size());
                    factory.executePrintOnAllMachines(latch);
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Print executed");
                    break;
                case 13:
                    System.out.println(System.lineSeparator() + "Enter machine name.");
                    name = scen.nextLine();
                    res = factory.getSpecificMachine(name);
                    if (res == null) {
                        System.out.println("Machine don't exist.");
                    } else {
                        latch = new CountDownLatch(1);
                        factory.executePrintOnSpecificMachine(name, latch);
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Print executed");
                        res = null;
                    }
                    break;
                case 14:
                    alive = false;
                    break;
                default:
                    break;
            }


        }
    }
}