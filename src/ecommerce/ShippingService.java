package ecommerce;

import java.util.*;

public class ShippingService {

    public static void shipItems(List<Shippable> items) {
        System.out.println("** Shipment notice **");

        Map<String, ItemInfo> itemMap = new LinkedHashMap<>();
        double totalWeight = 0;
        List<String> weightLabels = new ArrayList<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            itemMap.putIfAbsent(name, new ItemInfo(0, weight));
            itemMap.get(name).count++;
            totalWeight += weight;

            weightLabels.add((int)(weight * 1000) + "g");
        }

        for (Map.Entry<String, ItemInfo> entry : itemMap.entrySet()) {
            System.out.printf("%dx %-13s", entry.getValue().count, entry.getKey());
        }

        System.out.println(String.join(" ", weightLabels));
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
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
