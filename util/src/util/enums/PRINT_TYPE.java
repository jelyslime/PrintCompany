package util.enums;

import java.io.Serializable;

public enum PRINT_TYPE implements Serializable {
    MONOCHROME(0.5),
    COLORED(0.8),
    NONE(0.0);

    private final double value;

    PRINT_TYPE(final double newValue) {
        value = newValue;
    }

    public double getValue() {
        return value;
    }
}
