package ecommerce;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 100, 5, LocalDate.of(2025, 12, 31), 0.2);
        TV tv = new TV("TV", 300, 3, 0.7);
        ScratchCard scratchCard = new ScratchCard("Scratch Card", 50, 10);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 2, LocalDate.of(2025, 8, 1), 0.7);
        Customer customer = new Customer("Ali", 1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);
        cart.add(biscuits, 1);
        CheckoutService.checkout(customer, cart);
    }
}
