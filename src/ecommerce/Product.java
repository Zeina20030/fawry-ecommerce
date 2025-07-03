package ecommerce;

public abstract class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public void DecreaseQuantity(int amount) {
        if (amount > quantity)
            throw new IllegalArgumentException("Sorry, there is no available stock..");
        quantity = quantity - amount;
    }


    public abstract boolean isExpired();
}
