package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {


    void setNextStringToPrint(String nextString);

    String getNextStringToPrint();

    List<String> getHistoryPrintedStrings();

    void printCurrentString();

}
