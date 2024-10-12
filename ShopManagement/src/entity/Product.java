package entity;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return productId + "," + name + "," + price + "," + quantity;
    }

    public static Product fromString(String productString) {
        String[] parts = productString.split(",");
        return new Product(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }
}
