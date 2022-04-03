package budget;

import budget.strategies.SortAllStrategy;
import budget.strategies.SortByTypeStrategy;
import budget.strategies.SortCertainTypeStrategy;
import budget.strategies.StrategyContext;

import java.io.*;

import static budget.ConsoleHelper.*;


public class BudgetManager {
    private Budget budget;
    private final String file;

    public BudgetManager() {
        budget = new Budget();
        file = "purchases.txt";
    }

    public void execute(Operation operation) {

        switch (operation) {
            case ADD_INCOME:
                addIncome();
                break;
            case ADD_PURCHASE:
                addPurchase();
                break;
            case SHOW_PURCHASES:
                showPurchases();
                break;
            case BALANCE:
                showBalance();
                break;
            case SAVE:
                save();
                break;
            case LOAD:
                load();
                break;
            case ANALYZE:
                analyze();
                break;
        }
    }

    private void analyze() {
       StrategyContext context = null;
        while (true) {
            selectSortMessage();
            Selectable op = selectSortingMethod();

            if (op == Operation.BACK) {
                printMessage("\n");
                break;
            }
            if (op == SortingStrategy.SORT_ALL) {

                context = new StrategyContext(new SortAllStrategy());
            }

            else if (op == SortingStrategy.CERTAIN_TYPE) {
                selectTypeMessage(op);
                PurchaseType type = (PurchaseType) selectType(op);
                context = new StrategyContext(new SortCertainTypeStrategy(type));
              }
            else if (op == SortingStrategy.BY_TYPE){
                context = new StrategyContext(new SortByTypeStrategy());
            }
            context.sort(budget);
        }
    }

    private void save() {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(budget);
            printMessage("\nPurchases were saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            budget = (Budget) ois.readObject();
            printMessage("\nPurchases were loaded!\n");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showBalance() {

        printMessage("\nBalance: $" +
                df.format(budget.getBalance()) + "\n");
    }

    private void showPurchases() {
        while (true) {
            selectTypeMessage(Operation.SHOW_PURCHASES);
            Selectable type = selectType(Operation.SHOW_PURCHASES);
            printMessage("\n");

            if (type == Operation.BACK) {
                printMessage("\n");
                break;
            }
            if (budget.isListEmpty()) {
                printMessage("\nThe purchase list is empty\n");
                continue;
            }
            budget.printPurchases(budget.getSubListByType((PurchaseType) type));
        }
    }


    private void addPurchase() {
        while (true) {
            selectTypeMessage(Operation.ADD_PURCHASE);
            Selectable type = selectType(Operation.ADD_PURCHASE);
            if (type == Operation.BACK) {
                printMessage("\n");
                break;
            }

            printMessage("\nEnter purchase name:");

            String name = "";
            while (name.isEmpty()) {
                name = readString();
            }

            printMessage("Enter its price:");
            double price = readDouble();

            budget.addMoney(-price);
            if (budget.getBalance() < 0) budget.setBalance(0);
            Purchase p = new Purchase(name, price);
            p.setType((PurchaseType) type);
            budget.addPurchase(p);
            printMessage("Purchase was added!\n");
        }
    }

    private void addIncome() {
        printMessage("\nEnter income:");
        double income = readDouble();
        budget.addMoney(income);
        printMessage("Income was added!\n");
    }

    public void init() {
        while (true) {
            startMessage();
            Operation op = selectOperation();

            if (op == Operation.EXIT) {
                exitMessage();
                break;
            }
            execute(op);
        }
    }
}
