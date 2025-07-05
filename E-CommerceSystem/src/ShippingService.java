import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static double ship(List<Shippable> items) {
        if (items.isEmpty()) return 0;

        Map<String, Integer> counts = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            counts.put(item.getName(), counts.getOrDefault(item.getName(), 0) + 1);
            totalWeight += item.getWeight();
        }

        System.out.println("** Shipment notice **");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey());
        }
        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
        
        return totalWeight;
    }
    public static double calculateTotalWeight(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
}