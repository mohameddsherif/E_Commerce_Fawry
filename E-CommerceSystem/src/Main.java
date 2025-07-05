import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese 400g", 100, 10, LocalDate.now().plusDays(5), true, 0.4);
        Product biscuits = new Product("Biscuits 700g", 150, 6, LocalDate.now().plusDays(15), true, 0.7);
        Product tv = new Product("TV", 5000, 2, null, true, 7.5);
        Product scratchCard = new Product("Mobile scratch card", 50, 100, null, false, 0);

        Customer customer = new Customer("Mohamed Sherif", 1000);
        Cart cart = new Cart();

        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);

            checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty())
            throw new IllegalStateException("Cart is empty.");

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            if (item.getQuantity() > p.getQuantity())
                throw new IllegalStateException("Insufficient stock for " + p.getName());
            if (p.isExpired())
                throw new IllegalStateException(p.getName() + " is expired.");
        }

        double subtotal = cart.subtotal();
        double shipping = cart.collectShippables().isEmpty() ? 0 : 30;
        double total = subtotal + shipping;

        if (!customer.canPay(total))
            throw new IllegalStateException("Customer has insufficient balance.");

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        List<Shippable>shippables = cart.collectShippables();
        ShippingService.ship(shippables);

        customer.pay(total);

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x"
                    + item.getProduct().getName() + " " + (int)item.getTotal());
        }
        double totalWeight = ShippingService.calculateTotalWeight(shippables);
        System.out.println("Total weight of order: " + totalWeight + "kg");
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shipping);
        System.out.println("Amount " + (int)total);
        System.out.println("Customer remaining balance: " + customer.getBalance());

        cart.clear();
    }
}