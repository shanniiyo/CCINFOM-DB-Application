public class SalesTransaction {
    private int salesID;
    private int staffID;
    private int productID;
    private int clientID;
    private int quantity;
    private double totalPrice;
    private String salesDate;

    public SalesTransaction(int salesID, int staffID, int productID, int clientID, int quantity, double totalPrice, String salesDate) {
        this.salesID = salesID;
        this.staffID = staffID;
        this.productID = productID;
        this.clientID = clientID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.salesDate = salesDate;
    }

    public int getSalesID() {
        return salesID;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getProductID() {
        return productID;
    }

    public int getClientID() {
        return clientID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getSalesDate() {
        return salesDate;
    }
}