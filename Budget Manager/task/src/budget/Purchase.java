package budget;

import java.io.Serializable;

public class Purchase implements Serializable, Comparable<Purchase> {
    private final String name;
    private final double price;
    private PurchaseType type;

    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public PurchaseType getType() {
        return type;
    }

    public void setType(PurchaseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " $" + price;
    }

    @Override
    public int compareTo(Purchase other) {
        return Double.compare(other.price, price);
    }
}

