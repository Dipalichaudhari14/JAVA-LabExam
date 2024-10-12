package operation;

import entity.Product;
import customexception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void sellProduct(String productId, int quantity) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    System.out.println("Product sold successfully.");
                    return;
                } else {
                    throw new ProductNotFoundException("Insufficient quantity in stock.");
                }
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String productId) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }
}
