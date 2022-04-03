package budget.strategies;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseType;

import java.util.Collections;
import java.util.List;

import static budget.ConsoleHelper.printMessage;

public class SortCertainTypeStrategy implements Strategy{
    private final PurchaseType type;

    public SortCertainTypeStrategy(PurchaseType type) {
        this.type = type;
    }

    @Override
    public void sort(Budget budget) {
        List<Purchase> result = budget.getSubListByType(type);
        Collections.sort(result);
        if(!result.isEmpty()) {
            printMessage("\n");
            printMessage(type.toString() + ":");
        }

        budget.printPurchases(result);
    }
}
