package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static final Scanner scanner = new Scanner(System.in);
    private static int currentAmountOfWater = 400,
                       currentAmountOfMilk = 540,
                       currentAmountOfCoffee = 120,
                       currentAmountOfCups = 9,
                       currentAmountOfMoney = 550;
    private static int[] espresso = {250, 0, 16, 1, 4},
                         latte = {350, 75, 20, 1, 7},
                         cappuccino = {200, 100, 12, 1, 6};

    public CoffeeMachine() {

    }

    public void printMenu() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", currentAmountOfWater);
        System.out.printf("%d ml of milk\n", currentAmountOfMilk);
        System.out.printf("%d g of coffee beans\n", currentAmountOfCoffee);
        System.out.printf("%d disposable cups\n", currentAmountOfCups);
        System.out.printf("$%d of money\n\n", currentAmountOfMoney);
    }

    public void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1" -> {
                if (enoughIngredients(espresso)) {
                    decrementIngredients(espresso);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    printLackingIngredients(espresso);
                }
            }
            case "2" -> {
                if (enoughIngredients(latte)) {
                    decrementIngredients(latte);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    printLackingIngredients(latte);
                }
            }
            case "3" -> {
                if (enoughIngredients(cappuccino)) {
                    decrementIngredients(cappuccino);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    printLackingIngredients(cappuccino);
                }
            }
            case "back" -> System.out.println("Alright, back you go!");
        }

        System.out.println();
    }

    public void printLackingIngredients(int[] ingredients) {
        StringBuilder sb = new StringBuilder();
        boolean enoughWater = currentAmountOfWater >= ingredients[0];
        boolean enoughMilk = currentAmountOfMilk >= ingredients[1];
        boolean enoughCoffee = currentAmountOfCoffee >= ingredients[2];
        boolean enoughCups = currentAmountOfCups >= ingredients[3];

        sb.append("Sorry, not enough ");
        if (!enoughWater) {
            sb.append("water, ");
        }

        if (!enoughMilk) {
            sb.append("milk, ");
        }

        if (!enoughCoffee) {
            sb.append("coffee, ");
        }

        if (!enoughCups) {
            sb.append("cups, ");
        }

        sb.replace(sb.length() - 2, sb.length(), "!");
        System.out.println(sb);
    }

    public boolean enoughIngredients(int[] ingredients) {
        return  currentAmountOfWater >= ingredients[0] &&
                currentAmountOfMilk >= ingredients[1] &&
                currentAmountOfCoffee >= ingredients[2] &&
                currentAmountOfCups >= ingredients[3];
    }

    public void decrementIngredients(int[] ingredients) {
        currentAmountOfWater -= ingredients[0];
        currentAmountOfMilk -= ingredients[1];
        currentAmountOfCoffee -= ingredients[2];
        currentAmountOfCups -= ingredients[3];
        currentAmountOfMoney += ingredients[4];
    }

    public void fillMachine() {
        System.out.println("Write how many ml of water you want to add:");
        currentAmountOfWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        currentAmountOfMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        currentAmountOfCoffee += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        currentAmountOfCups += scanner.nextInt();
        scanner.nextLine();
        System.out.println();
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n\n", currentAmountOfMoney);
        currentAmountOfMoney = 0;
    }
}
