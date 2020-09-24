package menus;

import PrintFactory.elements.Page;
import util.enums.*;
import util.inputControl.ControlledInput;

import java.util.Scanner;

public class paperCreator {
    paperCreator() {
    }

    public static Page start() {
        ControlledInput inputControl = new ControlledInput();
        Scanner scen = new Scanner(System.in);
        int input = 0;
        boolean flager = true;
        System.out.println(System.lineSeparator() + "Choose Paper format:" + System.lineSeparator() + FORMAT.A1 + System.lineSeparator()
                + FORMAT.A2 + System.lineSeparator() + FORMAT.A3 + System.lineSeparator()
                + FORMAT.A4 + System.lineSeparator() + FORMAT.A5 + System.lineSeparator());
        FORMAT form = FORMAT.A4;
        while (flager) {
            input = inputControl.start(input);
            switch (input) {
                case 1:
                    form = FORMAT.A1;
                    flager = false;
                    break;
                case 2:
                    form = FORMAT.A2;
                    flager = false;
                    break;
                case 3:
                    form = FORMAT.A3;
                    flager = false;
                    break;
                case 4:
                    form = FORMAT.A4;
                    flager = false;
                    break;
                case 5:
                    form = FORMAT.A5;
                    flager = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println(System.lineSeparator() + "Choose Paper Type:" + System.lineSeparator() + PAPER.NEWSPAPER + System.lineSeparator()
                + PAPER.NORMAL + System.lineSeparator() + PAPER.GLOSSY + System.lineSeparator());
        PAPER pap = PAPER.NORMAL; //def value
        flager = true;
        while (flager) {
            input = inputControl.start(input);
            switch (input) {
                case 1:
                    pap = PAPER.NEWSPAPER;
                    flager = false;
                    break;
                case 2:
                    pap = PAPER.NORMAL;
                    flager = false;
                    break;
                case 3:
                    pap = PAPER.GLOSSY;
                    flager = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println(System.lineSeparator() + "Choose Print Type:" + System.lineSeparator() + PRINT_TYPE.MONOCHROME + System.lineSeparator()
                + System.lineSeparator() + PRINT_TYPE.COLORED + System.lineSeparator());
        PRINT_TYPE meho = PRINT_TYPE.MONOCHROME;//def value
        flager = true;
        while (flager) {
            input = inputControl.start(input);
            switch (input) {
                case 1:
                    meho = PRINT_TYPE.MONOCHROME;
                    flager = false;
                    break;
                case 2:
                    meho = PRINT_TYPE.COLORED;
                    flager = false;
                    break;
                default:
                    break;
            }
        }

        Page res = new Page(form, pap, meho);
        return res;
    }
}
