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

        List<Shippable> shippableList = new ArrayList<>();

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

            subtotal += product.getPrice() * qty;

            if (product instanceof Shippable) {
                double weight = ((Shippable) product).getWeight();
                for (int i = 0; i < qty; i++) {
                    shippableList.add((Shippable) product);
                    totalWeight += weight;
                }
            }
        }

        double shippingFee = shippableList.isEmpty() ? 0 : 30;
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        customer.deductBalance(totalAmount);

        if (!shippableList.isEmpty()) {
            ShippingService.shipItems(shippableList);
        }

        System.out.println("\n** Checkout receipt **");
        for (CartItem item : items) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            double totalPrice = product.getPrice() * qty;
            System.out.printf("%dx %-13s%.0f\n", qty, product.getName(), totalPrice);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shippingFee);
        System.out.printf("Amount           %.0f\n", totalAmount);
    }
}
