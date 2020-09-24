package util.enums;

import java.io.Serializable;

public enum PAPER implements Serializable {
    NORMAL(0.5),
    GLOSSY(0.8),
    NEWSPAPER(0.3),
    NONE(0.0);

    private final double value;

    PAPER(final double newValue) {
        value = newValue;
    }

    public double getValue() {
        return value;
    }
}