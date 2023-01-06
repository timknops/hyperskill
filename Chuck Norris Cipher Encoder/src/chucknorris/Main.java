package chucknorris;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program allows the user to encode a message to the chuck norris unary code, or decode the chuck norris unary
 * code.
 * @see <a href="https://www.dcode.fr/chuck-norris-code;">Chuck Norris Unary Code</a>
 *
 * @author tknops
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();

            switch (operation) {
                case "encode" -> {
                    System.out.println("Input string:");
                    String inputString = scanner.nextLine();
                    System.out.printf("Encoded string:\n%s\n\n", convertToCNUCode(convertToBinaryString(inputString)));
                }

                case "decode" -> {
                    System.out.println("Input encoded string:");
                    String encodedString = scanner.nextLine();

                    // Error prevention on the encoded string input.
                    String errorMessage = "Encoded string is not valid.\n";
                    String checkForInvalidCharacters = encodedString.replaceAll("\\s", "")
                                                                    .replaceAll("0", "");
                    if (checkForInvalidCharacters.length() != 0) {
                        System.out.println(errorMessage);
                        continue;
                    } else if (!isValidFirstBlock(encodedString)) {
                        System.out.println(errorMessage);
                        continue;
                    } else if (!isValidAmountOfBlocks(encodedString)) {
                        System.out.println(errorMessage);
                        continue;
                    } else if (!isValidBinaryStringLength(cnuCodeToBinary(encodedString))) {
                        System.out.println(errorMessage);
                        continue;
                    }

                    System.out.printf("Decoded string:\n%s\n\n", binaryToString(cnuCodeToBinary(encodedString)));
                }

                case "exit" -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }

                default -> System.out.printf("There is no '%s' operation\n\n", operation);
            }
        }
    }

    /**
     * Checks to see if the binary string that is generated from decoding the chuck norris code is of valid length.
     *
     * @param binaryString  the encoded message, in binary form
     * @return              true if length is valid, false if not
     */
    private static boolean isValidBinaryStringLength(String binaryString) {
        return binaryString.length() % 7 == 0;
    }

    /**
     * Checks to see if the chuck norris code is made up of valid amount of blocks.
     *
     * @param encodedString  an encoded chuck norris unary code
     * @return               true if the amount is valid, false if not
     */
    private static boolean isValidAmountOfBlocks(String encodedString) {
        return encodedString.trim().split(" ").length % 2 == 0;
    }

    /**
     * Checks if the first block of each sequence is not 0 or 00.
     *
     * @param encodedString  an encoded chuck norris unary code
     * @return               true if the first block of each sequence is valid, false if not
     */
    private static boolean isValidFirstBlock(String encodedString) {
        int whiteSpaceCounter = 0, startingIndex = 0;
        for (int i = 0; i < encodedString.length(); i++) {
            if (encodedString.charAt(i) == ' ') {
                whiteSpaceCounter++;
            }

            if (whiteSpaceCounter == 2 || i == encodedString.length() - 1) {
                String[] pair = encodedString.substring(startingIndex, i + 1).trim().split(" ");
                if (pair[0].length() > 2) {
                    return false;
                }

                startingIndex = i;
                whiteSpaceCounter = 0;
            }
        }

        return true;
    }

    /**
     * Converts the user input to the binary representation.
     *
     * @param input  the message that the user gave
     * @return       binary representation of the input parameter
     */
    private static String convertToBinaryString(String input) {
        StringBuilder sb = new StringBuilder();

        char[] chars = input.toCharArray();
        for (char ch : chars) {
            sb.append(
                    String.format("%7s", Integer.toBinaryString(ch)).replaceAll(" ", "0")
            );
        }

        return sb.toString();
    }

    /**
     * Converts a binary string to chuck norris unary code.
     *
     * @param binaryString  binary representation of the user input
     * @return              the chuck norris unary code, created from the binaryString
     */
    private static String convertToCNUCode(String binaryString) {
        StringBuilder sb = new StringBuilder();

        int sameCharsInARow = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            sameCharsInARow++;

            if (i + 1 < binaryString.length() && binaryString.charAt(i + 1) != binaryString.charAt(i)
                    || i + 1 == binaryString.length()) {
                String oneZero = binaryString.charAt(i) == '1' ? "0 " : "00 ";
                sb.append(oneZero);

                sb.append("0".repeat(Math.max(0, sameCharsInARow)));

                sb.append(' ');
                sameCharsInARow = 0;
            }
        }

        return sb.toString().trim();
    }

    /**
     * Converts chuck norris unary code to binary.
     *
     * @param cnuCode  chuck norris code message
     * @return         binary representation of the converted code
     */
    private static String cnuCodeToBinary(String cnuCode) {
        StringBuilder sb = new StringBuilder();

        int whiteSpaceCounter = 0, startingIndex = 0;
        for (int i = 0; i < cnuCode.length(); i++) {
            if (cnuCode.charAt(i) == ' ') {
                whiteSpaceCounter++;
            }

            if (whiteSpaceCounter == 2 || i == cnuCode.length() - 1) {
                String pair = cnuCode.substring(startingIndex, i + 1).trim();
                startingIndex = i;
                whiteSpaceCounter = 0;

                String[] pairArr = pair.split(" ");
                String oneZero = pairArr[0].equals("0") ? "1" : "0";
                sb.append(oneZero.repeat(pairArr[1].length()));
            }
        }

        return sb.toString();
    }

    /**
     * Converts binary to a string.
     *
     * @param binaryString  binary representation of the message to be converted
     * @return              the decoded message from binary
     */
    private static String binaryToString(String binaryString) {
        ArrayList<String> lettersInBinary = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // Split at 7.
        for (int i = 0; i < binaryString.length(); i += 7) {
            lettersInBinary.add(binaryString.substring(i, i + 7));
        }

        // Converts the binary to character.
        for (String letter : lettersInBinary) {
            sb.append((char) Integer.parseInt(letter, 2));
        }

        return sb.toString();
    }
}