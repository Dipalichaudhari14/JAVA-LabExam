package ui;

import entity.Product;
import operation.ShopService;
import service.FileHandler;
import customexception.ProductNotFoundException;

import java.util.Scanner;

public class ShopUI {
    private static final String FILE_NAME = "products.txt";

    public static void main(String[] args) {
        ShopService service = new ShopService();

        // Load products from file if they exist
        try {
            service.getAllProducts().addAll(FileHandler.loadProducts(FILE_NAME));
            System.out.println("Products loaded from file.");
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nShop Management System:");
            System.out.println("1. Add Product");
            System.out.println("2. Sell Product");
            System.out.println("3. Display All Products");
            System.out.println("4. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    service.addProduct(new Product(productId, name, price, quantity));
                    System.out.println("Product added.");
                    break;
                case 2:
                    System.out.print("Enter Product ID to Sell: ");
                    productId = scanner.nextLine();
                    System.out.print("Enter Quantity to Sell: ");
                    quantity = scanner.nextInt();
                    try {
                        service.sellProduct(productId, quantity);
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("All Products:");
                    for (Product product : service.getAllProducts()) {
                        System.out.println(product);
                    }
                    break;
                case 4:
                    try {
                        FileHandler.saveProducts(service.getAllProducts(), FILE_NAME);
                        System.out.println("Data saved. Exiting...");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
