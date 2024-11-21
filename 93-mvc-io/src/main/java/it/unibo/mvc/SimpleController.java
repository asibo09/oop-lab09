package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> historyList = new ArrayList<>();
    private String nextString;

    @Override
    public void setNextStringToPrint(final String nextString) {
        if (nextString == null) {
            throw new IllegalStateException("String can't be null");
        }
        historyList.add(nextString);
        this.nextString = nextString;
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getHistoryPrintedStrings() {
        return List.copyOf(historyList);
    }

    @Override
    public void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException("No string is set");
        }
        historyList.add(nextString);
        System.out.println(nextString); // NOPMD: allowed as this is just an exercise
    }
}
