package encryptdecrypt;

import encryptdecrypt.algorithms.*;
import encryptdecrypt.file.*;
import encryptdecrypt.parsing.ArgParser;

/**
 * Program allows the user to encrypt or decrypt a message, and write it to a file. The program must be run from the
 * command line.
 *
 * @author tknops
 */

public class Main {

    /**
     * @param args  command line arguments, used throughout the entire program.
     */
    public static void main(String[] args) {
        // Parses the command line arguments.
        ArgParser parser = new ArgParser(args);
        if (parser.getIn() != null) {
            ReadFile file = new ReadFile(parser.getIn());
            parser.setData(file.getFileContent());
        }

        // Selects an algorithm depending on whether there is an -alg argument given.
        Algorithm algorithm = parser.getAlg().equals("shift") ?
                new AlgorithmShift(parser.getData(), parser.getKey()) :
                new AlgorithmUnicode(parser.getData(), parser.getKey());

        // Encrypts or decrypts depending on whether there is an -enc argument given.
        String encryptedMessage = parser.getMode().equals("enc") ?
                algorithm.encrypt() : algorithm.decrypt();

        // Writes to the file specified, else prints to the console.
        if (parser.getOut() != null) {
            new WriteFile(parser.getOut(), encryptedMessage);
        } else {
            System.out.println(encryptedMessage);
        }
    }
}
