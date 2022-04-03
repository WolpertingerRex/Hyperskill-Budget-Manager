package budget;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in)
            .useLocale(Locale.ENGLISH);
    public static final DecimalFormat df;
    static{
        df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        df.applyPattern("#0.00");
    }

    public static void startMessage() {
        String message = "Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit";

        System.out.println(message);
    }

    public static void exitMessage() {
        System.out.println("\nBye!");
    }

    public static double readDouble() {
        return scanner.nextDouble();
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static Operation selectOperation() {
        int num = scanner.nextInt();
        switch (num) {
            case 0:
                return Operation.EXIT;
            case 1:
                return Operation.ADD_INCOME;
            case 2:
                return Operation.ADD_PURCHASE;
            case 3:
                return Operation.SHOW_PURCHASES;
            case 4:
                return Operation.BALANCE;
            case 5:
                return Operation.SAVE;
            case 6:
                return Operation.LOAD;
            case 7:
                return Operation.ANALYZE;
            default:
                return null;
        }
    }

    public static void selectSortMessage(){
        String message = "\n1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back";
        System.out.println(message);
    }

    public static Selectable selectSortingMethod(){
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                return SortingStrategy.SORT_ALL;
            case 2:
                return SortingStrategy.BY_TYPE;
            case 3:
                return SortingStrategy.CERTAIN_TYPE;
            case 4: return Operation.BACK;
            default:return null;
        }
    }

    public static void selectTypeMessage(Selectable op) {
        String message = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n";
        if (op == Operation.ADD_PURCHASE)
            message += "5) Back";
        else if (op == Operation.SHOW_PURCHASES)
            message += "5) All\n" +
                    "6) Back";

        System.out.println(message);
    }

    public static Selectable selectType(Selectable op) {
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                return PurchaseType.FOOD;
            case 2:
                return PurchaseType.CLOTHES;
            case 3:
                return PurchaseType.ENTERTAINMENT;
            case 4:
                return PurchaseType.OTHER;
            case 5: {
                if (op == Operation.ADD_PURCHASE)
                    return Operation.BACK;
                else return PurchaseType.ALL;
            }
            case 6:
                if (op == Operation.SHOW_PURCHASES) return Operation.BACK;
            default:
                return null;
        }
    }
}
