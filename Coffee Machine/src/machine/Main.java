package machine;

import static machine.CoffeeMachine.scanner;

public class Main {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        String userInput;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "buy" ->  coffeeMachine.buyCoffee();
                case "fill" -> coffeeMachine.fillMachine();
                case "take" -> coffeeMachine.takeMoney();
                case "remaining" -> {
                    System.out.println();
                    coffeeMachine.printMenu();
                }
            }
        } while (!userInput.equals("exit"));
    }
}
