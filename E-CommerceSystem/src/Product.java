import java.time.LocalDate;


public class Product implements Shippable{
    private final String name;
    private final double price;
    private int quantity;
    private final LocalDate expiry;
    private final boolean shippable;
    private final double weightKg;

    public Product(String name, double price, int quantity, LocalDate expiry, boolean shippable, double weightKg) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiry = expiry;
        this.shippable = shippable;
        this.weightKg = weightKg;
    }

    public boolean isExpired() {
        return expiry != null && expiry.isBefore(LocalDate.now());

    }
    public void reduceStock(int ammount) {
        quantity -=ammount;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public LocalDate getExpiry() {
        return expiry;
    }
    public boolean isShippable() {
        return shippable;
    }
    @Override
    public double getWeight() {
        return weightKg;
    }
}

