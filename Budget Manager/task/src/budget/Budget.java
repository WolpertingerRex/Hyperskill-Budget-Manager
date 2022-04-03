package budget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static budget.ConsoleHelper.df;
import static budget.ConsoleHelper.printMessage;

public class Budget implements Serializable {
    private double balance;
    private final List<Purchase> purchases;

    public Budget() {
        balance = 0d;
        purchases = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase p){
        purchases.add(p);
    }

    public void addMoney(double amount){
        balance += amount;
    }

    public boolean isListEmpty(){
        return  purchases.isEmpty();
    }

    public List<Purchase> getSubListByType(PurchaseType type){
       if (type == PurchaseType.ALL) return purchases;
       return purchases.stream()
               .filter(x-> x.getType()==type)
               .collect(Collectors.toList());
    }

    public double getTotalSpending(){
        double total = 0d;
        for (Purchase purchase : purchases) {
            total = total + purchase.getPrice();
        }
        return total;
    }

    public void printPurchases(List<Purchase> list) {
        double total = 0d;

        for (Purchase p : list) {
            printMessage(p.getName() + " $"
                    + df.format(p.getPrice()));
            total += p.getPrice();
        }

        if (total == 0) {
            printMessage("\nThe purchase list is empty\n");
            //continue;
        }
        else printMessage("Total sum: $" + df.format(total) + "\n");
    }
}

enum SortingStrategy implements Selectable{
    SORT_ALL,
    BY_TYPE,
    CERTAIN_TYPE
}
