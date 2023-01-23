package encryptdecrypt.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads the content of the file that is given in the directory.
 *
 * @author tknops
 */

public class ReadFile {

    private Scanner scanner;
    private final StringBuilder FILE_CONTENT;
    private final File FILE;

    /**
     * Constructor.
     *
     * @param directory  path of the file
     */
    public ReadFile(String directory) {
        this.scanner = new Scanner(System.in);
        this.FILE_CONTENT = new StringBuilder();
        this.FILE = new File(directory);
        read();
    }

    /** Reads the content of the file. */
    public void read() {
        try {
            scanner = new Scanner(FILE);
            while (scanner.hasNextLine()) {
                FILE_CONTENT.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE.getAbsolutePath());
            System.exit(0);
        }
    }

    /** Returns the content of the file that was read. */
    public String getFileContent() {
        return FILE_CONTENT.toString();
    }
}
