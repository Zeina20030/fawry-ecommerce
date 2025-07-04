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
        List<Shippable> shipmentList = new ArrayList<>();

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

                double weight = ((Shippab
