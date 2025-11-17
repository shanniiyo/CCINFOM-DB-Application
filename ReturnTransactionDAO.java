import java.sql.*;

public class ReturnTransactionDAO {

    // INSERT RETURN TRANSACTION
    public void addReturn(ReturnTransaction rt) {
        String sql = "INSERT INTO ReturnTransaction (ProductID, StaffID, ReturnDate, Quantity, Reason, RefundAmount) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, rt.getProductID());
            ps.setInt(2, rt.getStaffID());
            ps.setString(3, rt.getReturnDate());
            ps.setInt(4, rt.getQuantity());
            ps.setString(5, rt.getReason());
            ps.setDouble(6, rt.getRefundAmount());

            ps.executeUpdate();
            System.out.println("Return/Refund transaction recorded!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REPORT 1: FULL REFUND/RETURN REPORT
    public void getFullReturnReport() {
        String sql = """
            SELECT rt.ReturnID, rt.ReturnDate, p.ProductName,
                   rt.Quantity, rt.Reason, rt.RefundAmount,
                   s.Name AS StaffName
            FROM ReturnTransaction rt
            JOIN Product p ON rt.ProductID = p.ProductID
            JOIN Staff s ON rt.StaffID = s.StaffID
            ORDER BY rt.ReturnDate DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== FULL RETURN/REFUND REPORT ===");

            while (rs.next()) {
                System.out.println("Return ID: " + rs.getInt("ReturnID"));
                System.out.println("Date: " + rs.getDate("ReturnDate"));
                System.out.println("Product: " + rs.getString("ProductName"));
                System.out.println("Qty: " + rs.getInt("Quantity"));
                System.out.println("Reason: " + rs.getString("Reason"));
                System.out.println("Refund Amount: " + rs.getDouble("RefundAmount"));
                System.out.println("Staff: " + rs.getString("StaffName"));
                System.out.println("---------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REPORT 2: TOTAL REFUNDS HANDLED BY EACH STAFF
    public void getRefundsPerStaff() {
        String sql = """
            SELECT s.Name, SUM(rt.RefundAmount) AS TotalRefunds
            FROM ReturnTransaction rt
            JOIN Staff s ON rt.StaffID = s.StaffID
            GROUP BY s.Name
            ORDER BY TotalRefunds DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== TOTAL REFUNDS PER STAFF ===");

            while (rs.next()) {
                System.out.println("Staff: " + rs.getString("Name") +
                                   " | Total Refund: " + rs.getDouble("TotalRefunds"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REPORT 3: MOST RETURNED PRODUCTS
    public void getMostReturnedProducts() {
        String sql = """
            SELECT p.ProductName, SUM(rt.Quantity) AS TotalReturned
            FROM ReturnTransaction rt
            JOIN Product p ON rt.ProductID = p.ProductID
            GROUP BY p.ProductName
            ORDER BY TotalReturned DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== MOST RETURNED PRODUCTS ===");

            while (rs.next()) {
                System.out.println("Product: " + rs.getString("ProductName") +
                                   " | Returned Qty: " + rs.getInt("TotalReturned"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
