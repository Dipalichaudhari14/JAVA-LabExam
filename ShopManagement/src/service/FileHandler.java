package service;

import entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void saveProducts(List<Product> products, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    public static List<Product> loadProducts(String filename) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(Product.fromString(line));
            }
        }
        return products;
    }
}
