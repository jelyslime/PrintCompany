package PrintFactory.Machine;

import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

/*
Base machine class. Base information of an machine implemented
 */

public abstract class Machine implements Serializable {
    private String name; //will be used as an identifier for the user
    private final String serialNumber;
    private final String warranty; //is a date. Extending warranty after purchase is not an option
    private final String dateOfPurchase;

    public Machine(String name, String serialNumber, String warranty, String dateOfPurchase) {
        this.name = new String(name);
        this.serialNumber = new String(serialNumber);
        this.warranty = new String(warranty);
        this.dateOfPurchase = new String(dateOfPurchase);
    }

    public Machine() {
        this.name = new String("null");
        this.serialNumber = new String("null");
        this.dateOfPurchase = new String("null");
        this.warranty = new String("null");
    }

    public Machine(@NotNull Machine toCpy) {
        this.name = new String(toCpy.name);
        this.serialNumber = new String(toCpy.serialNumber);
        this.warranty = new String(toCpy.warranty);
        this.dateOfPurchase = new String(toCpy.dateOfPurchase);
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        //measured by serial numbers because
        //machine may have identical names for simplified
        //work with them
        if (this == obj) return true;
        if (!(obj instanceof Machine)) return false;
        Machine toCmp = (Machine) obj;
        return this.serialNumber.equals(toCmp.serialNumber);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "name='" + name + '\'' +
                ", serialNumber=" + serialNumber +
                ", warranty=" + warranty +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }

}