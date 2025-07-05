import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        if (quantity > product.getQuantity())
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        if (product.isExpired())
            throw new IllegalStateException(product.getName() + " is expired.");
        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<CartItem>getItems() {
        return items;
    }

    public double subtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public List<Shippable> collectShippables() {
        List<Shippable> result = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    result.add(item.getProduct());
                }
            }
        }
        return result;
    }

    public void clear() {
        items.clear();
    }
}
