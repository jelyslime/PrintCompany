package PrintFactory.Employee;

import org.jetbrains.annotations.NotNull;

public class Operator extends Employee {

    public Operator() {
        super();
        totalEarnings = baseEarnings;
    }


    public Operator(String name) {
        super(name);
        totalEarnings = baseEarnings;
    }

    public Operator(Employee toCpy, double totalEarnings) {
        super(toCpy);
        totalEarnings = baseEarnings;
    }

    public Operator(@NotNull Operator toCpy) {
        super(toCpy);
        this.totalEarnings = toCpy.totalEarnings;
    }


    @Override
    public void calculateIncome(double factor) {

        double newSym = baseEarnings + ((baseEarnings / 100) * factor);
        this.setTotalEarnings(newSym);
    }

    @Override
    public String toString() {
        return super.toString() + "Operator{" +
                "totalEarnings=" + totalEarnings +
                '}';
    }
}

