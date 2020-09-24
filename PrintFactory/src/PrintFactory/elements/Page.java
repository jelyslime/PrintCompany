package PrintFactory.elements;

import util.enums.*;
import java.io.Serializable;


//This object defines parameters of "page" for the print machine


public class Page implements Serializable {
    private FORMAT format;
    private PAPER paper;
    private PRINT_TYPE printType;

    public Page(FORMAT format, PAPER paper, PRINT_TYPE printType) {
        this.format = format;
        this.paper = paper;
        this.printType = printType;
    }

    public Page(Page toCpy) {
        this.format = toCpy.format;
        this.paper = toCpy.paper;
        this.printType = toCpy.printType;
    }


    public FORMAT getFormat() {
        return format;
    }

    public void setFormat(FORMAT format) {
        this.format = format;
    }

    public PAPER getPaper() {
        return paper;
    }

    public void setPaper(PAPER paper) {
        this.paper = paper;
    }

    public PRINT_TYPE getPrintType() {
        return printType;
    }

    public void setPrintType(PRINT_TYPE printType) {
        this.printType = printType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;
        Page page = (Page) o;
        return format == page.format &&
                paper == page.paper &&
                printType == page.printType;
    }


    @Override
    public String toString() {
        return "Page{" +
                "format=" + format +
                ", paper=" + paper +
                ", printType=" + printType +
                '}';
    }
}
