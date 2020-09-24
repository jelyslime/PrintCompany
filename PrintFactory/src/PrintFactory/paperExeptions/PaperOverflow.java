package PrintFactory.paperExeptions;

import java.io.Serializable;

//This exception is throwed in scenario when the executor is
//trying to print more pages then the current page load in the machine

public class PaperOverflow extends Exception implements Serializable {
    private int loadedPages;

    public PaperOverflow(int input) {
        loadedPages = input;
    }

    @Override
    public String toString() {
        return "PaperOverflow{" +
                "loadedPages=" + loadedPages +
                '}';
    }
}
