package PrintFactory.paperExeptions;

import PrintFactory.elements.Page;
import java.io.Serializable;

/*This exception is throwed in scenario when the executor is
trying to import different page type from the current
loaded */

public class PaperMismatch extends Exception implements Serializable {
    private Page loadedPageType;

    public PaperMismatch(Page input) {
        loadedPageType = new Page(input);
    }

    @Override
    public String toString() {
        return "PaperMismatch{" +
                "loadedPageType=" + loadedPageType.toString() +
                '}';
    }
}