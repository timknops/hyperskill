package readability;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This program must be run from the command line! Allows the user to read a file, and see the amount of words,
 * sentences, characters, syllable and polysyllables. Then it can calculate the readability based upon those statistics.
 *
 * @author tknops
 */

public class Main {

    private static int totalPolySyllables = 0;

    /**
     * @param args  command line arguments,
     *              in this case it will be the path of the file that the user wished to be read
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = readFile(args[0]); // Reads the file

        int totalWords = amountOfWordsInText(text);
        int totalSentences = amountOfSentencesInText(text);
        int totalCharacters = amountOfCharactersInText(text);
        int totalSyllables = amountOfSyllablesInText(text);

        printTextInformation(text, totalWords, totalSentences, totalCharacters, totalSyllables, totalPolySyllables);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String userInput = scanner.next();
        switch (userInput) {
            case "ARI" -> {
                double ariScore = calculateARIScore(totalCharacters, totalWords, totalSentences);
                System.out.printf("\nAutomated Readability Index: %.2f (about %s-year-olds).\n",
                        ariScore, calculateUnderstandingAges(ariScore));
            }

            case "FK" -> {
                double fkScore = calculateFKScore(totalWords, totalSentences, totalSyllables);
                System.out.printf("\nFlesch-Kincaid readability tests: %.2f (about %s-year-olds).\n",
                        fkScore, calculateUnderstandingAges(fkScore));
            }

            case "SMOG" -> {
                double smogScore = calculateSMOGScore(totalPolySyllables, totalSentences);
                System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %s-year-olds).\n",
                        smogScore, calculateUnderstandingAges(smogScore));
            }

            case "CL" -> {
                double clScore = calculateCLScore(totalCharacters, totalWords, totalSentences);
                System.out.printf("\nColeman-Liau Index: %.2f (about %s-year-olds).\n",
                        clScore, calculateUnderstandingAges(clScore));
            }

            case "all" -> {
                double ariScore = calculateARIScore(totalCharacters, totalWords, totalSentences);
                double fkScore = calculateFKScore(totalWords, totalSentences, totalSyllables);
                double smogScore = calculateSMOGScore(totalPolySyllables, totalSentences);
                double clScore = calculateCLScore(totalCharacters, totalWords, totalSentences);

                int ariAge = Integer.parseInt(calculateUnderstandingAges(ariScore));
                int fkAge = Integer.parseInt(calculateUnderstandingAges(ariScore));
                int smogAge = Integer.parseInt(calculateUnderstandingAges(smogScore));
                int clAge = Integer.parseInt(calculateUnderstandingAges(clScore));

                System.out.printf("\nAutomated Readability Index: %.2f (about %d-year-olds).\n",
                        ariScore, ariAge);
                System.out.printf("Flesch-Kincaid readability tests: %.2f (about %d-year-olds).\n",
                        fkScore, fkAge);
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).\n",
                        smogScore, smogAge);
                System.out.printf("Coleman-Liau Index: %.2f (about %d-year-olds).\n",
                        clScore, clAge);

                // Calculates the average age based on all scores.
                double averageAge = (double) (ariAge + fkAge + smogAge + clAge) / 4;
                System.out.printf("\nThis text should be understood in average by %.2f-year-olds.\n", averageAge);
            }

            default -> System.out.printf("\n%s is not a valid option!\n", userInput);
        }
    }

    /**
     * Reads the file that is given as directory.
     *
     * @param directory  path of the file
     * @return           value of the file content
     */
    private static String readFile(String directory) {
        File file = new File(directory);

        String fileContent = null;
        try  {
            byte[] bytes = Files.readAllBytes(Paths.get(directory)); // Reads the file
            fileContent = new String (bytes);
        } catch (IOException | NullPointerException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
            System.exit(0);
        }

        return fileContent;
    }

    /**
     * Prints out all the information of the text that is given.
     *
     * @param text                text that is in the file that the user selected
     * @param totalWords          total amount of words that are in the text
     * @param totalSentences      total amount of sentences that are in the text
     * @param totalCharacters     total amount of characters that are in the text
     * @param totalSyllables      total amount of syllables that are in the text
     * @param totalPolySyllables  total amount of polysyllables that are in the text
     */
    private static void printTextInformation(String text, int totalWords, int totalSentences, int totalCharacters,
                                             int totalSyllables, int totalPolySyllables) {
        System.out.printf("The text is:\n%s\n\n", text.trim());
        System.out.printf("Words: %d\n", totalWords);
        System.out.printf("Sentences: %d\n", totalSentences);
        System.out.printf("Characters: %d\n", totalCharacters);
        System.out.printf("Syllables: %d\n", totalSyllables);
        System.out.printf("Polysyllables: %d\n", totalPolySyllables);
    }

    /**
     * Calculates the amount of sentences that are in the text.
     *
     * @param text  text that is in the file that the user selected
     * @return      amount of sentences that are in the text
     */
    private static int amountOfSentencesInText(String text) {
        return text.trim().split("[!?.]").length;
    }

    /**
     * Calculates the total amount of words in the text.
     *
     * @param text   text that is in the file that the user selected
     * @return       amount of words that are in the text
     */
    private static int amountOfWordsInText(String text) {
        return text.split(" ").length;
    }

    /**
     * Calculates the total amount of character in the text.
     *
     * @param text  text that is in the file that the user selected
     * @return      amount of characters that are in the text excluding whitespace
     */
    private static int amountOfCharactersInText(String text) {
        return text.replaceAll("\\s", "").length();
    }

    /**
     * Calculates the total amount of syllables in the text.
     *
     * @param text  text that is in the file that the user selected
     * @return      amount of syllables that are in the text
     */
    private static int amountOfSyllablesInText(String text) {
        String[] words = text.split(" ");

        int counter = 0;
        for (String word : words) {
            // If no syllables are found, default to 1, else use the amount of syllables found in the word.
            int amount = Math.max(1, amountOfSyllablesInWord(word));
            counter += amount;

            if (amount > 2) {
                totalPolySyllables++; // A polysyllable is a word that contains more than 2 syllables.
            }
        }

        return counter;
    }

    /**
     * Calculates the total amount of syllables that are in a word.
     *
     * @param word  a single word from the text
     * @return      total amount of syllables that are within that word
     */
    private static int amountOfSyllablesInWord(String word) {
        return word
                .replaceAll("[aeiouy]{2,}", "a") // Replace double vowels.
                .replaceAll("e$", "") // Remove ending if e, these don't count as a syllable.
                .replaceAll("[^aeiouy]", "") // Remove all non-vowels.
                .length();
    }

    /**
     * Calculates the ARI of the text.
     *
     * @param chars      total amount of characters in the text
     * @param words      total amount of words in the text
     * @param sentences  total amount of sentences in the text
     * @return           automated readability index
     * @see              <a href="https://en.wikipedia.org/wiki/Automated_readability_index">ARI</a>
     */
    private static double calculateARIScore(int chars, int words, int sentences) {
        return (4.71 * chars / words) + (0.5 * words / sentences) - 21.43;
    }

    /**
     * Calculates the Flesch-Kincaid readability test score.
     *
     * @param words      total amount of words in the text
     * @param sentences  total amount of sentences in the text
     * @param syllables  total amount of syllables in the text
     * @return           Flesch-Kincaid readability test score
     * @see              <a href="https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests">FK Index</a>
     */
    private static double calculateFKScore(int words, int sentences, int syllables) {
        return (0.39 * words / sentences) + (11.8 * syllables / words) - 15.59;
    }

    /**
     * Calculates the SMOG (Simple Measure of Gobbledygook) grade.
     *
     * @param polySyllables  total amount of polysyllables in the text
     * @param sentences      total amount of sentences in the text
     * @return               SMOG grade
     * @see                  <a href=" https://en.wikipedia.org/wiki/SMO">SMOG Grade</a>
     */
    private static double calculateSMOGScore(int polySyllables, int sentences) {
        return 1.043 * Math.sqrt((double) polySyllables * 30 / sentences) + 3.1291;
    }

    /**
     * Calculates the Coleman-Liau Index.
     *
     * @param chars      total amount of characters in the text
     * @param words      total amount of words in the text
     * @param sentences  total amount of sentences in the text
     * @return           the Coleman-Liau index
     * @see              <a href="https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index">CL Index</a>
     */
    private static double calculateCLScore(int chars, int words, int sentences) {
        return 0.0588 *  (chars * 100 / (double) words) - 0.296 *  (sentences * 100 / (double) words) - 15.8;
    }

    /**
     * Calculates the understanding ages of the text, depending on the score.
     *
     * @param score  score that was calculated using either the ARI, FK, SMOG or CL methods
     * @return       String with the top range of the understanding age depending on the score
     * @see          #calculateARIScore(int chars, int words, int sentences)
     * @see          #calculateFKScore(int words, int sentences, int syllables)
     * @see          #calculateSMOGScore(int polySyllables, int sentences)
     * @see          #calculateCLScore(int chars, int words, int sentences)
     */
    private static String calculateUnderstandingAges(double score) {
        int roundScore = (int) Math.ceil(score);

        String ages;
        switch (roundScore) {
            case 1 -> ages = "6";
            case 2 -> ages = "7";
            case 3 -> ages = "8";
            case 4 -> ages = "9";
            case 5 -> ages = "10";
            case 6 -> ages = "11";
            case 7 -> ages = "12";
            case 8 -> ages = "13";
            case 9 -> ages = "14";
            case 10 -> ages = "15";
            case 11 -> ages = "16";
            case 12 -> ages = "17";
            case 13 -> ages = "18";
            default -> ages = "22";
        }

        return ages;
    }
}
