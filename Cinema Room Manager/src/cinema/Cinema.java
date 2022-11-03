package cinema;

import java.util.Scanner;

/**
 * Program allows user to generate a 2d cinema map, then pick a spot in the cinema and book it. The user can also see
 * the statistics of the cinema that they generated.
 *
 * @author tknops
 */

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] seating = generateSeating(rows, seats);

        int userInput, ticketsPurchased = 0, totalTickets = rows * seats, currentIncome = 0;
        do {
            printMenu();
            userInput = scanner.nextInt();

            switch (userInput) {
                case (1) -> printSeating(seats, seating);
                case (2) -> {
                    currentIncome += updateSeating(seating, rows, seats);
                    ticketsPurchased++;
                }
                case (3) -> {
                    int totalIncome = calculateTotalIncome(rows, seats);
                    printStatistics(ticketsPurchased, totalIncome, totalTickets, currentIncome);
                }
            }
        } while (userInput != 0);
    }

    /**
     * Prints out the menu that the user is selecting from.
     */
    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    /**
     * Prints out the twoDimArray when 1. is selected from the menu.
     *
     * @param seats Amount of seats in the cinema.
     * @param twoDimArr Contains the full cinema seating map.
     */
    public static void printSeating(int seats, char[][] twoDimArr) {
        System.out.println("\nCinema:");

        // Prints the top numbers row.
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.printf("%d ", i);
        }

        System.out.println();

        // Prints out the array.
        for (char[] chars : twoDimArr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    /**
     * Generates the two-dimensional array that is used throughout the array.
     * Size of the array depends on the user input.
     *
     * @param rows Amount of rows in the cinema.
     * @param seats Amount of seats in the cinema.
     *
     * @return The array generated.
     */
    public static char[][] generateSeating(int rows, int seats) {
        // Creates the two-dimensional array from rows and seats variables.
        seats = (seats * 2) + 1;
        char[][] twoDimArr = new char[rows][seats];
        char rowCounter = '1';
        for (int i = 0; i < twoDimArr.length; i++) {
            for (int j = 0; j < twoDimArr[i].length; j++) {
                if (j == 0) {
                    twoDimArr[i][j] = rowCounter;
                    rowCounter++;
                } else if (j % 2 == 0){
                    twoDimArr[i][j] = 'S';
                } else {
                    twoDimArr[i][j] = ' ';
                }
            }
        }

        return twoDimArr;
    }

    /**
     * Calculates ticket price based on the user input and prints it out.
     *
     * @param rows Amount of rows in the cinema.
     * @param seats Amount of seats in the cinema.
     * @param rowNumber Row number that was selected by the user.
     *
     * @return Price of the ticket that the user selected.
     */
    public static int buyTicket(int rows, int seats, int rowNumber) {
        final int REG_SEAT_PRICE = 10, LOW_SEAT_PRICE = 8;
        int totalSeats = rows * seats;

        int ticketPrice, firstHalf = rows / 2;
        System.out.println(firstHalf);

        // Checks what the ticket price should be based on the rowNumber that is decided by the user.
        if (totalSeats <= 60) {
            ticketPrice = REG_SEAT_PRICE;
        } else if (rowNumber < firstHalf) {
            ticketPrice = REG_SEAT_PRICE;
        } else {
            ticketPrice = LOW_SEAT_PRICE;
        }

        System.out.printf("\nTicket price: $%d\n", ticketPrice);
        return ticketPrice;
    }

    /**
     * Lets user buy a ticket when selecting 2. in the menu, and updates the array.
     *
     * @param twoDimArr Contains the full cinema seating map.
     * @param rows Amount of rows in the cinema.
     * @param seats Amount of seats in the cinema.
     *
     * @return The price of the ticket that was purchased.
     */
    public static int updateSeating(char[][] twoDimArr, int rows, int seats) {
        int rowNumber;

        while (true) {
            System.out.println("\nEnter a row number:");
            rowNumber = scanner.nextInt() - 1;

            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            // Checks if the seat chosen lies within the cinema limits.
            if (rowNumber + 1 > rows || seatNumber > seats ||
                rowNumber < 0 || seatNumber < 1) {
                System.out.println("Wrong input!");
                continue;
            }

            // Checks to see if the spot has already been booked.
            char index = twoDimArr[rowNumber][seatNumber * 2];
            if (index == 'B') {
                System.out.println("\nThat ticket has already been purchased!");
                continue;
            }

            twoDimArr[rowNumber][seatNumber * 2] = 'B'; // Replaces S with B depending on rowNumber and seatNumber.
            break;
        }

        return buyTicket(rows, seats, rowNumber);
    }

    /**
     * Prints out the statistics menu when user input selects 3.
     *
     * @param ticketsPurchased Current amount of tickets purchased.
     * @param totalIncome Total amount of income that would be achievable.
     * @param totalTickets Total amount of tickets that are available.
     * @param currentIncome Current income, based on how many tickets are purchased.
     */
    public static void printStatistics(int ticketsPurchased, int totalIncome, int totalTickets, int currentIncome) {
        System.out.printf("\nNumber of purchased tickets: %d\n", ticketsPurchased);

        double percentage = (double) ticketsPurchased * 100 / totalTickets;
        System.out.printf("Percentage: %.2f%%\n", percentage);

        System.out.printf("Current Income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    /**
     * Calculates the total income that is displayed when printing the statistics.
     *
     * @param rows Amount of rows in the cinema.
     * @param seats Amount of seats in the cinema.
     *
     * @return The maximum amount of income that would be achievable.
     */
    public static int calculateTotalIncome(int rows, int seats) {
        final int REG_SEAT_PRICE = 10, LOW_SEAT_PRICE = 8;
        int totalSeats = rows * seats;

        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * REG_SEAT_PRICE;
        } else {
            int firstHalf = rows / 2;
            int secondHalf = rows - firstHalf;

            totalIncome = ((firstHalf * seats) * REG_SEAT_PRICE) + ((secondHalf * seats) * LOW_SEAT_PRICE);
        }

        return totalIncome;
    }
}