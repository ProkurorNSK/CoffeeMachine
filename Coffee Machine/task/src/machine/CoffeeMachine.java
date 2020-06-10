package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int countWater;
    private int countMilk;
    private int countCoffee;
    private int countCup;
    private int countMoney;
    private State state;

    public CoffeeMachine(int countWater, int countMilk, int countCoffee, int countCup, int countMoney) {
        this.countWater = countWater;
        this.countMilk = countMilk;
        this.countCoffee = countCoffee;
        this.countCup = countCup;
        this.countMoney = countMoney;
        this.state = State.WAIT;
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        while (coffeeMachine.state != State.OFF) {
            System.out.println(coffeeMachine.state.getInvitationText());
            String command = scanner.nextLine();
            coffeeMachine.actionProcessing(command);
        }
    }

    enum State {
        WAIT("Write action (buy, fill, take, remaining, exit):"),
        CHOOSE_COFFEE("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:"),
        FILL_WATER("Write how many ml of water do you want to add:"),
        FILL_MILK("Write how many ml of milk do you want to add:"),
        FILL_COFFEE("Write how many grams of coffee beans do you want to add:"),
        FILL_CUP("Write how many disposable cups of coffee do you want to add:"),
        OFF("");

        private final String invitationText;

        State(String invitationText) {
            this.invitationText = invitationText;
        }

        public String getInvitationText() {
            return invitationText;
        }
    }

    public void actionProcessing(String command) {
        switch (state) {
            case WAIT:
                switch (command) {
                    case "buy":
                        state = State.CHOOSE_COFFEE;
                        break;
                    case "fill":
                        state = State.FILL_WATER;
                        break;
                    case "take":
                        actionTake();
                        break;
                    case "remaining":
                        printState();
                        break;
                    case "exit":
                        state = State.OFF;
                        break;
                }
                break;
            case CHOOSE_COFFEE:
                actionBuy(command);
                break;
            case FILL_WATER:
            case FILL_MILK:
            case FILL_COFFEE:
            case FILL_CUP:
                actionFill(command);
        }
    }

    private void actionFill(String amount) {
        switch (state) {
            case FILL_WATER:
                countWater += Integer.parseInt(amount);
                state = State.FILL_MILK;
                break;
            case FILL_MILK:
                countMilk += Integer.parseInt(amount);
                state = State.FILL_COFFEE;
                break;
            case FILL_COFFEE:
                countCoffee += Integer.parseInt(amount);
                state = State.FILL_CUP;
                break;
            case FILL_CUP:
                countCup += Integer.parseInt(amount);
                state = State.WAIT;
                break;
        }

    }

    private void actionTake() {
        System.out.println("I gave you $" + countMoney);
        countMoney = 0;
    }

    private void actionBuy(String typeOfCoffee) {
        int needWater = 0;
        int needMilk = 0;
        int needCoffee = 0;
        int cost = 0;
        int needCap = 1;

        switch (typeOfCoffee) {
            case "1":
                needWater = 250;
                needMilk = 0;
                needCoffee = 16;
                cost = 4;
                break;
            case "2":
                needWater = 350;
                needMilk = 75;
                needCoffee = 20;
                cost = 7;
                break;
            case "3":
                needWater = 200;
                needMilk = 100;
                needCoffee = 12;
                cost = 6;
                break;
            case "back":
                state = State.WAIT;
                return;
        }

        if (needWater <= countWater && needMilk <= countMilk && needCoffee <= countCoffee && needCap <= countCup) {
            countWater -= needWater;
            countMilk -= needMilk;
            countCoffee -= needCoffee;
            countCup -= needCap;
            countMoney += cost;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough water!");
        }
        state = State.WAIT;
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(countWater + " of water");
        System.out.println(countMilk + " of milk");
        System.out.println(countCoffee + " of coffee beans");
        System.out.println(countCup + " of disposable cups");
        System.out.println(countMoney + " of money");
        System.out.println();
    }
}
