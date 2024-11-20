package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME_STRING = System.getProperty("user.home");
    private static final String DEF_STRING = "output.txt";

    private File destFile = new File(HOME_STRING + File.separator + DEF_STRING);

    /**
     * @return current file
     */
    public File getCurrentFile() {
        return destFile;
    }

    /**
     * @return the path of current file
     */
    public Path getPathFile() {
        return destFile.toPath();
    }

    /**
     * Set new destination file
     * 
     * @param file
     *      file where to write
     */
    public void setDestination(final File file) {
        final File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            destFile = file;
        } else {
            throw new IllegalArgumentException("Can't save a no existing folder");
        }
    }

    /**
     * Set new destination file
     * 
     * @param destFile
     *          file where to write
     */
    public void setDestFile(final String destFile) {
        setDestination(new File(destFile));
    }

    /**
     * Saves its content on the current file
     * 
     * @param textString
     *          text to save
     * @throws IOException
     *          if writing fail
     */
    public void save(final String textString) throws IOException {
        try (PrintStream out = new PrintStream(destFile, StandardCharsets.UTF_8)) {
            out.println(textString);
        }
    }  
}
