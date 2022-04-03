package budget.strategies;

import budget.Budget;
import budget.Purchase;
import budget.PurchaseType;

import java.util.*;

import static budget.ConsoleHelper.*;

public class SortByTypeStrategy implements Strategy{

    @Override
    public void sort(Budget budget) {
        Map<PurchaseType, Double> map = new TreeMap<>();

        List<Purchase> all = budget.getSubListByType(PurchaseType.ALL);

        if(all.isEmpty()){
            map.put(PurchaseType.FOOD, 0d);
            map.put(PurchaseType.ENTERTAINMENT, 0d);
            map.put(PurchaseType.CLOTHES, 0d);
            map.put(PurchaseType.OTHER, 0d);
        }

        all.forEach(p->
                map.merge(p.getType(), p.getPrice(), Double::sum));

        List<Map.Entry<PurchaseType, Double>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        LinkedHashMap<PurchaseType, Double> result = new LinkedHashMap<>();
        for (Map.Entry<PurchaseType, Double> entry : entries){
            result.put(entry.getKey(), entry.getValue());
        }

        printMessage("\nTypes:");
        result.forEach((k,v) -> printMessage(k.toString()
                + " - $" + df.format(v)));

        printMessage("Total sum: $" + df.format(budget.getTotalSpending()) + "\n");

    }
}
