package PrintFactory.Employee;

import org.jetbrains.annotations.NotNull;

public class Manager extends Employee {


    public Manager() {
        super();
        totalEarnings = baseEarnings;
    }


    public Manager(String name) {
        super(name);
        totalEarnings = baseEarnings;
    }

    public Manager(Employee toCpy, double totalEarnings) {
        super(toCpy);
        setTotalEarnings(baseEarnings);
    }

    public Manager(@NotNull Manager toCpy) {
        super(toCpy);
        this.setTotalEarnings(toCpy.getTotalEarnings());
    }


    @Override
    public void calculateIncome(double factor) {
        //in this scenario the factor is a % incrased of the base salary
        //based of the bonus income of the Factory

        double newSym = getBaseEarnings() + ((getBaseEarnings() / 100) * factor);
        this.setTotalEarnings(newSym);

    }

    @Override
    public String toString() {
        return super.toString() + "Manager{" +
                "totalEarnings=" + getTotalEarnings() +
                '}';
    }
}
