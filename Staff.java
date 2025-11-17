public class Staff {

    private String staffID;          // Primary Key
    private String name;
    private String credentials;      // e.g., Manager, Pharmacist, Admin
    private boolean activeStatus;    // true = active, false = inactive
    private double salesQuota;       // Default: 2000 per month

    // Constructor
    public Staff(String staffID, String name, String credentials, boolean activeStatus) {
        this.staffID = staffID;
        this.name = name;
        this.credentials = credentials;
        this.activeStatus = activeStatus;
        this.salesQuota = 2000.0;    // Default monthly quota
    }

    // Getters
    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getCredentials() {
        return credentials;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public double getSalesQuota() {
        return salesQuota;
    }

    // Setters (optional)
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setSalesQuota(double salesQuota) {
        this.salesQuota = salesQuota;
    }

    // ============================================
    // Database Linked Methods
    // ============================================

    /* // Placeholder — connect this to StaffDAO / TransactionDAO
    public void viewSalesPerformance() {
        System.out.println("\n=== SALES PERFORMANCE REPORT ===");
        System.out.println("Staff ID: " + staffID);
        System.out.println("Name: " + name);
        System.out.println("Monthly Quota: " + salesQuota);

        // will calculate:
        // totalSales = SUM(transaction.quantity * product.price)
        // retrieved via InventoryTransactionDAO

        System.out.println("Total Sales: [Retrieve from DB]");
        System.out.println("Status: [Meeting Quota / Below Quota]");
  }  */  

  public void viewSalesPerformance(double monthlySales) {

        System.out.println("\n===== SALES PERFORMANCE =====");
        System.out.println("Staff: " + name + " (" + staffID + ")");
        System.out.println("Credentials: " + credentials);
        System.out.println("Monthly Sales: ₱" + monthlySales);
        System.out.println("Sales Quota: ₱" + salesQuota);

        if (monthlySales >= salesQuota) {
            System.out.println("STATUS: Quota Achieved!");
        } else {
            double lacking = salesQuota - monthlySales;
            System.out.println("STATUS: Quota NOT met. Lacking: ₱" + lacking);
        }
    }

    // Placeholder — to be linked to InventoryTransactionDAO
    public void viewBranchTransactions() {
        System.out.println("\n=== BRANCH TRANSACTIONS HANDLED BY STAFF ===");
        System.out.println("Staff ID: " + staffID);
        System.out.println("Name: " + name);

        // Later:
        // SELECT * FROM InventoryTransaction WHERE staffID = ?
        System.out.println("Transactions: [Retrieve from DB]");
    }
}
