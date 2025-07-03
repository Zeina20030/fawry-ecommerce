package ecommerce;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {

    public static void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");

        Map<String, ItemInfo> itemMap = new LinkedHashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            Product product = (Product) item;
            String name = product.getName();
            double weight = item.getWeight();

            itemMap.putIfAbsent(name, new ItemInfo(0, weight));
            itemMap.get(name).count++;
            totalWeight += weight;
        }

        for (Map.Entry<String, ItemInfo> entry : itemMap.entrySet()) {
            String name = entry.getKey();
            ItemInfo info = entry.getValue();
            System.out.printf("%dx %-12s %.0fg%n", info.count, name, info.weight * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    private static class ItemInfo {
        int count;
        double weight;

        ItemInfo(int count, double weight) {
            this.count = count;
            this.weight = weight;
        }
    }
}
