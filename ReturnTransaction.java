public class ReturnTransaction {
    private int returnID;
    private int productID;
    private int staffID;
    private String returnDate;
    private int quantity;
    private String reason;
    private double refundAmount;

    public ReturnTransaction(int productID, int staffID, String returnDate,
                             int quantity, String reason, double refundAmount) {
        this.productID = productID;
        this.staffID = staffID;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.reason = reason;
        this.refundAmount = refundAmount;
    }

    // Getters
    public int getProductID() { return productID; }
    public int getStaffID() { return staffID; }
    public String getReturnDate() { return returnDate; }
    public int getQuantity() { return quantity; }
    public String getReason() { return reason; }
    public double getRefundAmount() { return refundAmount; }
}
