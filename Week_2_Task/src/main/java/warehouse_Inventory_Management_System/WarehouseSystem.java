package warehouse_Inventory_Management_System;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Product Class
class Product {
    private String productID;
    private String name;
    private int quantity;
    private Location location;

    public Product(String productID, String name, int quantity, Location location) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductID() { 
    	return productID; 
    }
    public String getName() { 
    	return name; 
    }
    public int getQuantity() { 
    	return quantity; 
    }
    public Location getLocation() { 
    	return location; 
    }
    public void reduceQuantity(int amount) throws OutOfStockException {
        if (quantity < amount) throw new OutOfStockException("Product " + name + " is out of stock!");
        quantity -= amount;
    }
}

// Location Class
class Location {
    private int aisle, shelf, bin;
    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }
    @Override
    public String toString() {
        return "Aisle: " + aisle + ", Shelf: " + shelf + ", Bin: " + bin;
    }
}

// Order Class
class Order {
    private String orderID;
    private List<String> productIDs;
    private Priority priority;
    
    public enum Priority { STANDARD, EXPEDITED }
    
    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public List<String> getProductIDs() { 
    	return productIDs;
    }
    public Priority getPriority() { 
    	return priority; 
    }
}


class OutOfStockException extends Exception {
    public OutOfStockException(String message) { super(message); }
}
class InvalidLocationException extends Exception {
    public InvalidLocationException(String message) { super(message); }
}


class OrderComparator implements Comparator<Order> {
    public int compare(Order o1, Order o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}

// InventoryManager Class
class InventoryManager {
    private Map<String, Product> products;
    private PriorityQueue<Order> orderQueue;
    private ExecutorService executorService;

    public InventoryManager() {
        products = new ConcurrentHashMap<>();
        orderQueue = new PriorityQueue<>(new OrderComparator());
        executorService = Executors.newFixedThreadPool(3); // Three threads for processing
    }

    public synchronized void addProduct(Product product) {
        products.put(product.getProductID(), product);
    }

    public void placeOrder(Order order) {
        orderQueue.add(order);
        executorService.submit(this::processOrders);
    }

    private void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            if (order != null) {
                for (String productId : order.getProductIDs()) {
                    try {
                        Product product = products.get(productId);
                        if (product != null) {
                            product.reduceQuantity(1);
                            System.out.println("Order processed: " + product.getName());
                        } else {
                            throw new OutOfStockException("Product ID " + productId + " not found.");
                        }
                    } catch (OutOfStockException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }
    }
}

// Main Class
public class WarehouseSystem {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Initializing Products
        inventoryManager.addProduct(new Product("P1", "Laptop", 10, new Location(1, 2, 3)));
        inventoryManager.addProduct(new Product("P2", "Mouse", 20, new Location(1, 3, 5)));

        // Creating Orders
        Order order1 = new Order("O1", Arrays.asList("P1", "P2"), Order.Priority.EXPEDITED);
        Order order2 = new Order("O2", Arrays.asList("P1"), Order.Priority.STANDARD);

        // Placing Orders
        inventoryManager.placeOrder(order1);
        inventoryManager.placeOrder(order2);
    }
}

