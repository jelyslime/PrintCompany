package PrintFactory.paperExeptions;

import java.io.Serializable;

//this exception is when an printing machine is  loaded with
//more pages then it's maximum capability
public class PaperOverload extends Exception implements Serializable {
    private int loadedPages;

    public PaperOverload(int input) {
        loadedPages = input;
    }

    @Override
    public String toString() {
        return "PaperOverload{" +
                "loadedPages=" + loadedPages +
                '}';
    }
}

