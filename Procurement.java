public class Procurement {
    private int procurementID;
    private int productID;
    private int supplierID;
    private int quantity;
    private String transactionDate;

    public Procurement(int procurementID, int productID, int supplierID, int quantity, String transactionDate) {
        this.procurementID = procurementID;
        this.productID = productID;
        this.supplierID = supplierID;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }
    
    
    public int getProcurementID() { return procurementID; }
    public int getProductID() { return productID; }
    public int getSupplierID() { return supplierID; }
    public int getQuantity() { return quantity; }
    public LocalDate getTransactionDate() { return transactionDate; }

}
