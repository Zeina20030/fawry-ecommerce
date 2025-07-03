package ecommerce;

import java.time.LocalDate;

public class Cheese extends Product implements Shippable {
    private LocalDate expiry;
    private double weight;

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiry = expiryDate;
        this.weight = weight;
    }


    public boolean isExpired() {
        return expiry.isBefore(LocalDate.now());
    }



    public double getWeight() {
        return weight;
    }


    public String getName() {
        return super.getName();
    }
}
