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

}