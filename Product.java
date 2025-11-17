import java.time.LocalDate;

public class Product {

        // SQL COLUMNS 
            // PRIMARY KEY (productID)
            // brand VARCHAR(100)
            // quantity INT
            // price DOUBLE
            // dateAdded DATE
            // expiration DATE

    private String productID;
    private String brand;
    private int quantity;
    private double price;
    private LocalDate dateAdded;
    private LocalDate expiration;

    public Product(String productID, String brand, int quantity, double price, LocalDate dateAdded, LocalDate expiration) {
        this.productID = productID;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.dateAdded = dateAdded;
        this.expiration = expiration;
    }

    // === GETTERS AND SETTERS ===
    public String getProductID() 
        { return productID; }
    public String getBrand() 
        { return brand; }
    public int getQuantity()
        { return quantity; }
    public double getPrice()
        { return price; }
    public LocalDate getDateAdded()
        { return dateAdded; }
    public LocalDate getExpiration() 
        { return expiration; }

    public void setQuantity(int quantity) 
        { this.quantity = quantity; }
    public void setPrice(double price)
        { this.price = price; }

    // === BUSINESS METHODS ===
    public void viewStockLevels() {
        System.out.println("Product: " + brand + " | Stock: " + quantity + " | Expiration: " + expiration);
    }

    public void viewTransaction(InventoryTransaction transaction) {
        System.out.println("=== Product Transaction Details ===");
        System.out.println("Transaction ID: " + transaction.getTransactionID());
        System.out.println("Product: " + this.brand);
        System.out.println("Quantity: " + transaction.getQuantity());
        System.out.println("Type: " + transaction.getTransactionType());
        System.out.println("Status: " + transaction.getStatus());
        System.out.println("Date: " + transaction.getTransactionDate());
    }
}
