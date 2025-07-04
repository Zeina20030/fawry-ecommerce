package ecommerce;

import java.util.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        List<CartItem> items = cart.getItems();
        double subtotal = 0;
        double totalWeight = 0;

        Map<String, Integer> shipItems = new LinkedHashMap<>();
        List<String> weightLabels = new ArrayList<>();

        for (CartItem item : items) {
            Product product = item.getProduct();
            int qty = item.getQuantity();

            if (product.isExpired()) {
                System.out.println("Error: " + product.getName() + " is expired.");
                return;
            }

            if (product.getQuantity() < qty) {
                System.out.println("Error: Not enough stock for " + product.getName());
                return;
            }

            product.DecreaseQuantity(qty);

            if (product instanceof Shippable) {
                double itemTotal = product.getPrice() * qty;
                subtotal += itemTotal;

                shipItems.put(product.getName(), qty);
                double weight = ((Shippable) product).getWeight() * qty;
                totalWeight += weight;
                weightLabels.add((int)(weight * 1000) + "g"); 
            }
        }

        double shippingFee = shipItems.isEmpty() ? 0 : 30;
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        customer.deductBalance(totalAmount);

        if (!shipItems.isEmpty()) {
            System.out.println("** Shipment notice **");
            for (Map.Entry<String, Integer> entry : shipItems.entrySet()) {
                System.out.printf("%dx %-13s", entry.getValue(), entry.getKey());
            }
            System.out.println(String.join(" ", weightLabels));
            System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<String, Integer> entry : shipItems.entrySet()) {
            String name = entry.getKey();
            int qty = entry.getValue();
            double price = 0;
            for (CartItem item : items) {
                if (item.getProduct().getName().equals(name)) {
                    price = item.getProduct().getPrice();
                    break;
                }
            }
            System.out.printf("%dx %-13s%.0f\n", qty, name, price * qty);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shippingFee);
        System.out.printf("Amount           %.0f\n", totalAmount);
    }
}
