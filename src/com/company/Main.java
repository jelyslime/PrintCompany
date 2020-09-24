package com.company;

/*
import com.company.elements.Page;
import com.company.employees.Employee;
import com.company.employees.Operator;
import com.company.exeptions.*;
import com.company.enums.*;
import com.company.machines.PrintMachine;
import org.jetbrains.annotations.NotNull;
import com.company.employees.Manager;
import com.company.enums.*;

import java.util.ArrayList;
 */

import menus.*;
import PrintFactory.factory.PrintFactory;
import java.util.concurrent.CountDownLatch;


public class Main {

    public static void main(String[] args) {

        PrintFactory factory = menu1.start();
        if (factory != null) {
            CountDownLatch latch = new CountDownLatch(factory.getPrintMachinesList().size());
            menu2.start(factory, latch);
        } else {
            System.out.println("Exiting the program.");
        }

   





































    /* THOSE ARE TEST VALUES UNCOMENT THEM IF YOU WANT DIRECT TESTING WITHOUT USING THE MENU
            NOTE:SOME METHODS MIGHT NOT BEEN TESTED HERE.
    public static void main(String[] args) {
        System.out.println("Full factory test");
        String nameFac = new String("mehoLend"); //1
        ArrayList<Employee> employees = new ArrayList<Employee>(); //2
        String sad;
        for(int i = 0; i < 4;i++)
        {
             sad = String.valueOf(i);
             employees.add(new Manager(sad));
        }
        Page testPaper = new Page(FORMAT.A4,PAPER.GLOSSY,PRINT_TYPE.COLORED);
        int curLod = 2;
        int maxLod = 15; //Adding diffrent values on each machine so i can see later how they work with the exeptions
        ArrayList<PrintMachine> printMachines = new ArrayList<PrintMachine>(); //3
        for(int i = 0; i < 4;i++)
        {
            String nam = String.valueOf(i);
            String serNam = String.valueOf(i+2);
            String warranty = String.valueOf(i+3);
            String datOfPurch = String.valueOf(i+4);

            printMachines.add(new PrintMachine(nam,serNam,warranty,datOfPurch,testPaper,curLod*i,maxLod*i));
        }

        double earn = 20000; // 4

        PrintFactory testFactory = new PrintFactory(nameFac,printMachines,employees,earn);
        System.out.println("Factory created successful");
        System.out.println("Printing to console full factory");
        System.out.println(testFactory.toString());
        System.out.println(testFactory.getSpecificMachine("1").toString());
        System.out.println(testFactory.getSpecificEmployee("1").toString());
        System.out.println("Adding paper");
        testFactory.addAllMachinesPaper(14,testPaper);
        System.out.println(testFactory.printMachinesList);
        System.out.println("Starting print on machine name:1");
        testFactory.addAllMachinesPaper(13,testPaper);
        System.out.println(testFactory.printMachinesList);
        testFactory.requestPrintOnSpecificMachine("1",6);
        System.out.println(testFactory.getSpecificMachine("1").toString());
       // testFactory.executePrintOnSpecificMachine("1");
        System.out.println(testFactory.getSpecificMachine("1").toString());
        System.out.println("testing the exeption");
        testFactory.requestPrintOnSpecificMachine("1",10);
        testFactory.addAllMachinesPaper(12,testPaper);
        System.out.println("Starting print on all machines");
        testFactory.requestPrintOnAllMachines(8);
        CountDownLatch latch = new CountDownLatch(4);
        testFactory.executePrintOnAllMachines(latch);
        try {
            latch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        System.out.println(System.lineSeparator()+ "Printing the factory again" + System.lineSeparator());
        System.out.println(testFactory.toString());
        System.out.println(System.lineSeparator()+ "Printing an report to a defult derectory file" + System.lineSeparator());
        testFactory.printFactoryFullReport("1");
        System.out.println(System.lineSeparator()+ "Serialising the file" + System.lineSeparator());
        testFactory.serializeMe("1");
        System.out.println(System.lineSeparator()+ "testing the Manager salary increase to do so i will increase income manualy" + System.lineSeparator());
        testFactory.incEarnings(30000);
        System.out.println(testFactory.getEarnings());
        testFactory.incManagersSalary();
        System.out.println(System.lineSeparator()+ "Printing Employee list" + System.lineSeparator() + testFactory.allEmployeesReport() + System.lineSeparator());
        testFactory.fireEmployee("2");
        System.out.println(System.lineSeparator()+ "Printing Employee list" + System.lineSeparator() + testFactory.allEmployeesReport() + System.lineSeparator());
        for(int i = 0; i < 4;i++)
        {
            sad = String.valueOf(i);
            Operator blin = new Operator(sad);
            testFactory.hireEmployee(blin);
        }
        System.out.println(System.lineSeparator()+ "Printing Employee list" + System.lineSeparator() + testFactory.allEmployeesReport() + System.lineSeparator());
        System.out.println(System.lineSeparator()+ "Printing the factory again" + System.lineSeparator());
        System.out.println(testFactory.toString());


        /*
        System.out.println("Testing employee group");
        Manager man1 = new Manager("name");
        System.out.println("Manager created.");
        System.out.println(man1.toString());
        System.out.println("Testing reavuluation method");
        man1.calculateIncome(23);
        System.out.println(man1.toString());
        System.out.println("Employee group created!");
        System.out.println("Creating print machine");
        FORMAT format= FORMAT.A1;
        PAPER paper = PAPER.NORMAL;
        PRINT_TYPE printType = PRINT_TYPE.COLORED;

        Page pag = new Page(format,paper,printType);
        PrintMachine mach1 = new PrintMachine("gope","1212121","12.12.2019","22323",pag,5,100);
        System.out.println(mach1.toString());
        System.out.println("testing all functions one by one");
        System.out.println(mach1.getCurrentLoad());
        System.out.println(mach1.getMaximumLoad());
        System.out.println(mach1.getPrintRequest());
        System.out.println(mach1.getPageType());
        System.out.println(mach1.getDateOfPurchase());
        System.out.println(mach1.getSerialNumber());
        System.out.println(mach1.getName());
        System.out.println(mach1.getWarranty());
        System.out.println("all getters tested!");
        System.out.println("testing equals method:");
        System.out.println(mach1.equals(mach1));
        try {
            System.out.println("testing page loading");
            mach1.loadPages(30,pag,true);
        } catch (Exception e)
        {
            System.out.println("exeption cought");
        }
        try {
            System.out.println("testing pagePrint request");
            mach1.requestPrint(15);
        } catch (Exception e)
        {
            System.out.println("exeption cought");
        }
        System.out.println(mach1.toString());
        System.out.println("testing printing function (runnable)*exeption works");

         */

    }
}
