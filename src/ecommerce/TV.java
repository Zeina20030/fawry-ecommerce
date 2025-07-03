package ecommerce;

public class TV extends Product  {
    private double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }



    @Override
    public String getName() {
        return super.getName();
    }
}
