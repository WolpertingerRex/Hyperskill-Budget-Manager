package budget.strategies;

import budget.Budget;
import budget.ConsoleHelper;
import budget.Purchase;

import java.util.Collections;
import java.util.List;

import static budget.ConsoleHelper.printMessage;


public class SortAllStrategy implements Strategy {
    @Override
    public void sort(Budget budget) {
        printMessage("\n");
        List<Purchase> all = budget.getPurchases();
        if (!all.isEmpty()) {
            ConsoleHelper.printMessage("\nAll:");
            Collections.sort(all);
        }

        budget.printPurchases(all);
    }
}
