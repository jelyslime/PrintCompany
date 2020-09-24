package PrintFactory.Machine;

import PrintFactory.paperExeptions.*;
import util.enums.*;
import PrintFactory.elements.Page;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;

public class PrintMachine extends Machine {

    private Page pageType;
    private int currentLoad;
    private final int maximumLoad;
    private int printRequest; //adding this because the requested print may not be equal to the currentLoad pages this value will help me monitor illegal print
    //executes and the current price for the print to calculate the income
    //the procedure of adding requested pages for the print have to be executed before the print

    public PrintMachine(String name, String serialNumber, String warranty, String dateOfPurchase, FORMAT format,
                        PAPER paper, PRINT_TYPE printType, int currentLoad, int maximumLoad) {
        super(name, serialNumber, warranty, dateOfPurchase);
        this.pageType = new Page(format, paper, printType);
        this.currentLoad = currentLoad;
        this.maximumLoad = maximumLoad;

        this.printRequest = 0; //supposing the machine will have no requested print pages
        // when is initialized for the first time
    }

    public PrintMachine(String name, String serialNumber, String warranty, String dateOfPurchase,
                        Page toCpy, int currentLoad, int maximumLoad) {
        super(name, serialNumber, warranty, dateOfPurchase);

        if (toCpy != null) {
            this.pageType = new Page(toCpy);
        } else {
            this.pageType = null;
        }
        this.currentLoad = currentLoad;
        this.maximumLoad = maximumLoad;
        this.printRequest = 0;
    }

    public PrintMachine(String name, String serialNumber, String warranty, String dateOfPurchase, int currentLoad, int maximumLoad)//creates machine without loading pagetype
    {
        super(name, serialNumber, warranty, dateOfPurchase);
        this.currentLoad = currentLoad;
        this.maximumLoad = maximumLoad;
        this.printRequest = 0;
    }

    public PrintMachine() {
        super();
        pageType = null;
        currentLoad = 0;
        maximumLoad = 0;
        this.printRequest = 0;
    }

    public PrintMachine(Machine toCpy, FORMAT format, PAPER paper,
                        PRINT_TYPE printType, int currentLoad, int maximumLoad) {
        super(toCpy);
        this.pageType = new Page(format, paper, printType);
        this.currentLoad = currentLoad;
        this.maximumLoad = maximumLoad;
        this.printRequest = 0;
    }

    public PrintMachine(Machine toCpy, Page copyPage, int currentLoad, int maximumLoad) {
        super(toCpy);
        if (copyPage != null) {
            this.pageType = new Page(copyPage);
        } else {
            this.pageType = null;
        }
        this.currentLoad = currentLoad;
        this.maximumLoad = maximumLoad;
        this.printRequest = 0;
    }

    public PrintMachine(@NotNull PrintMachine toCpy) {
        super(toCpy);
        this.maximumLoad = toCpy.maximumLoad;
        this.currentLoad = toCpy.currentLoad;
        if (toCpy.pageType != null) {
            this.pageType = new Page(toCpy.pageType);
        } else {
            pageType = null;
        }
        this.printRequest = toCpy.printRequest;
    }

    public Page getPageType() {
        return pageType;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public int getMaximumLoad() {
        return maximumLoad;
    }

    public int getPrintRequest() {
        return printRequest;
    }

    public void setPageType(Page pageType) {
        this.pageType = new Page(pageType);
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    protected void setPrintRequest(int printRequest) {
        this.printRequest = printRequest;
    }

    public void loadPages(int pageSize, Page PageType, boolean replaceCurrentLoad) throws PaperOverload, PaperMismatch {
        //this function haves the option to add pages to the current load (with identical page type)
        //or replace the load
        if (pageSize > this.getMaximumLoad() || pageSize < 0) {
            throw new PaperOverload(pageSize);
        }
        if (replaceCurrentLoad == true) {
            this.setCurrentLoad(pageSize);
            this.setPageType(PageType);
        } else {
            if (!this.pageType.equals(PageType)) {
                throw new PaperMismatch(PageType);
            }
            if (this.getCurrentLoad() + pageSize > this.getMaximumLoad()) {
                throw new PaperOverload(pageSize + this.getCurrentLoad());
            } else {
                this.setCurrentLoad(getCurrentLoad() + pageSize);
            }
        }
    }

    public void requestPrint(int size) throws PaperOverflow {
        if (size > this.currentLoad || size < 0) {
            throw new PaperOverflow(size);
        }
        this.setPrintRequest(size);
    }

/*
    //have to be in printFactory class
    @Override
    public double calculateCosts() {

        double sum = 0;
        sum += pageType.getPaper().getValue();
        sum *= pageType.getPrintType().getValue();
        sum *= printRequest;
        return sum;
    }

    //have to be in printFactoryClass
    @Override
    public double calculateIncome() {

        //20%of total production costs.
        double sum = this.calculateCosts();
        sum += ((sum / 100) * 20);
        return sum;
    }
*/

    @Override
    public String toString() {
        return super.toString() + "PrintMachine{" +

                ", pageType=" + pageType.toString() +
                ", currentLoad=" + currentLoad +
                ", maximumLoad=" + maximumLoad +
                ", printRequest=" + printRequest +
                '}';
    }

    public void printing(int delay, CountDownLatch latch, String name) {
        class printer extends Thread {
            private int delay;
            private CountDownLatch latch;

            public printer(int delay, CountDownLatch latch, String name) {
                super(name);
                this.delay = delay;
                this.latch = latch;
            }

            @Override
            public void run() { //tested and working properly
                try {
                    if (currentLoad <= 0 || printRequest <= 0) {
                        throw new PaperOverflow(0);
                    }
                    int size = printRequest;
                    for (int i = 1; i < size + 1; i++) {
                        System.out.println("Machine: " + getName() + " " + Thread.currentThread().getName() + ", printing page: " + i + " .");
                        --currentLoad;
                        --printRequest;
                    }
                    Thread.sleep(delay);
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName()
                            + " finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    latch.countDown();
                } catch (PaperOverflow e) {
                    System.out.println("Current load or requested print are below or equal 0" + System.lineSeparator() + "Thread is shutting down");
                    latch.countDown();

                    System.out.println(Thread.currentThread().getName()
                            + " finished");
                }
            }
        }
        printer priter = new printer(delay, latch, name);
        priter.start();
        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (currentLoad <= 0 || printRequest <= 0) {
                        throw new PaperOverflow(0);
                    }
                    int size = printRequest;
                    for (int i = 0; i < size+1; i++) {
                        System.out.println("Machine: " + getName() + " "+Thread.currentThread().getName() + ", printing page: " + i + " .");
                        --currentLoad;
                        --printRequest;
                    }
                } catch (PaperOverflow e) {
                    System.out.println("Current load or requested print are below or equal 0");
                }
            }
        };
        Thread thread = new Thread(runnable);
       try {
           thread.join();
       } catch (InterruptedException e)
       {
           System.out.println("cought");
       }
        */
    }




    /*
    @Override
    public void run(){
        try {
            if (this.currentLoad <= 0 || this.printRequest <= 0)
            {
                throw new PaperOverflow(0);
            }
            int size = printRequest;
            for (int i = 0;i <size+1;i++)
            {
                System.out.println("Machine: " + this.getName() + ", printing page: " + i + " .");
                --currentLoad;
                --printRequest;
            }

        } catch (PaperOverflow e)
        {
            System.out.println("Current load or requested print are below or equal 0");
        }
    }
     */
}
