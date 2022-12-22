package bullscows;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int MAX_LENGTH = 36;

        int numLength = getSecretCodeLength(MAX_LENGTH);
        int amountOfSymbols = getPossibleSymbols(MAX_LENGTH, numLength);



        printPreparationMessage(numLength, amountOfSymbols);

        String secretRandomCode = generateSecretCode(numLength, amountOfSymbols);

        System.out.println("Okay, let's start a game!");

        String guess;
        int counter = 1;
        do {
            System.out.printf("Turn %d:\n", counter);
            guess = scanner.nextLine();
            counter++;
        } while (!gradeGuess(secretRandomCode, guess));
    }

    private static void printPreparationMessage(int codeLength, int amountOfSymbols) {
        StringBuilder str = new StringBuilder();

        String dots = "";
        for (int i = 0; i < codeLength; i++) {
            dots += '*';
        }

        if (amountOfSymbols <= 10) {
            str.append(String.format("0-%d", amountOfSymbols - 1));
        } else {
            amountOfSymbols -= 11; // Minus the 0-9 range and the starting character.

            char endingCh = (char) ('a' + amountOfSymbols);
            str.append(String.format("0-9, a-%c", endingCh));
        }

        System.out.printf("The secret is prepared: %s (%s).\n\n", dots, str);
    }

    private static int getSecretCodeLength(final int MAX_LENGTH) {
        System.out.println("Please enter the secret code's length:");

        int length = 0;
        String lengthStr = "";
        try {
            lengthStr = scanner.nextLine();
            length = Integer.parseInt(lengthStr);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", lengthStr);
            System.exit(0);
        }

        if (length < 1) {
            System.out.println("Error: number can't be lower than 1");
            System.exit(0);
        } else if (length > MAX_LENGTH) {
            System.out.println("Error: can't generate a secret code longer than 36 because there are no more" +
                    " unique characters");
            System.exit(0);
        }

        return length;
    }

    private static int getPossibleSymbols(final int MAX_LENGTH, int numLength) {
        System.out.println("Input the number of possible symbols in the code:");

        int length = 0;
        String lengthStr = "";
        try {
            lengthStr = scanner.nextLine();
            length = Integer.parseInt(lengthStr);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", lengthStr);
            System.exit(0);
        }

        if (length < 1) {
            System.out.println("Error: number can't be lower than 1");
            System.exit(0);
        } else if (length > MAX_LENGTH) {
            System.out.println("Error: can't generate a secret code longer than 36 because there are no more" +
                    "unique characters");
            System.exit(0);
        } else if (numLength > length) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
                    numLength, length);
            System.exit(0);
        }

        return length;
    }


    private static String generateSecretCode(int numLength, int amountOfSymbols) {
        StringBuilder str = new StringBuilder();
        Random rand = new Random();

        // Picks a random number to choose a character for the secret code.
        int count = 1;
        while (true) {
            int randomCh = rand.nextInt(amountOfSymbols);
            if (!uniqueChars(str.toString(), randomCh)) {
                continue;
            }

            if (randomCh < 10) {
                str.append(randomCh);
            } else {
                randomCh -= 10;
                char ch = (char) ('a' + randomCh);
                str.append(ch);
            }

            if (count == numLength) {
                break;
            }

            count++;
        }

        return str.toString();
    }

    private static boolean uniqueChars(String str, int randomCh) {
        if (str.equals("")) {
            return true;
        }

        char ch;
        if (randomCh < 10) {
            ch = (char) (randomCh + '0');
        } else {
            randomCh -= 10;
            ch = (char) ('a' + randomCh);
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                return false;
            }
        }

        return true;
    }

    private static boolean gradeGuess(String secretRandomCode, String guess) {
        int cows = 0, bulls = 0;

        for (int i = 0; i < secretRandomCode.length(); i++) {
            // Checks for bulls.
            if (secretRandomCode.charAt(i) == guess.charAt(i)) {
                bulls++;
                cows--; // If there is a bull found, there will also be a cow found in the same position, therefore --.
            }

            // Checks for cows.
            for (int j = 0; j < guess.length(); j++) {
                if (secretRandomCode.charAt(i) == guess.charAt(j)) {
                    cows++;
                }
            }
        }

        String gradeMessage = bulls == 0 ? String.format("%d cow(s).", cows) :
                String.format("%d bull(s) and %d cows(s).", bulls, cows);

        if (bulls == secretRandomCode.length()) {
            System.out.printf("Grade : %s\n", gradeMessage);
            System.out.println("Congratulations! You guessed the code.");
            return true; // Will end the game.
        } else if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        } else {
            System.out.printf("Grade : %s\n", gradeMessage);
        }

        return false;
    }
}