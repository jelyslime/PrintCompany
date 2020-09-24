package util.inputControl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ControlledInput {
    public ControlledInput(){}



    public int start(int checkValue)
    {
        Scanner scen = new Scanner(System.in);
        boolean correctInputFlag = true;
        int input = 0;
        while (correctInputFlag) {
            try {

                input = scen.nextInt();
                correctInputFlag = false;

            } catch (InputMismatchException e) {
                System.out.println("Value haves to be of integer type");
                scen.next();
            }
        }
        return input;
    }

    public double start(double checkValue)
    {
        Scanner scen = new Scanner(System.in);
        boolean correctInputFlag = true;
        int input = 0;
        while (correctInputFlag) {
            try {

                input = scen.nextInt();
                correctInputFlag = false;

            } catch (InputMismatchException e) {
                System.out.println("Value haves to be of double type");
                scen.next();
            }
        }
        return input;
    }
}