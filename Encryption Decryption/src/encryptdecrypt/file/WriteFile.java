package encryptdecrypt.file;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes to the file that is specified in the directory.
 *
 * @author tknops
 */

public class WriteFile {

    private final String DIRECTORY;
    private final String ENC_MESSAGE;

    /**
     * Constructor.
     *
     * @param directory   path of the file to write to
     * @param encMessage  encrypted message that will be written in the file
     */
    public WriteFile(String directory, String encMessage) {
        this.DIRECTORY = directory;
        this.ENC_MESSAGE = encMessage;
        write();
    }

    /** Writes to the file */
    public void write() {
        try {
            FileWriter writer = new FileWriter(DIRECTORY);
            writer.write(ENC_MESSAGE);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error! Something went wrong:");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
