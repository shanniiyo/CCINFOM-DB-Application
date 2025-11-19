import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProcurementDAO {

    public static void addProcurement(Procurement procurement, int addedQty) {

        String sql = "INSERT INTO Procurement (ProcurementID, ProductID, SupplierID, Quantity, DateProcured) "
                   + "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;

        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, procurement.getProcurementID());
                stmt.setInt(2, procurement.getProductID());
                stmt.setInt(3, procurement.getSupplierID());
                stmt.setInt(4, addedQty);
                stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                stmt.executeUpdate();
            }

            // Update Product Quantity
            Product product = InventoryDAO.getProductByID(procurement.getProductID());
            if (product != null) {
                int newQty = product.getQuantity() + addedQty;
                InventoryDAO.updateProductQuantity(procurement.getProductID(), newQty);
            }

            conn.commit();
            System.out.println("Procurement transaction completed.");

        } catch (SQLException e) {
            e.printStackTrace();

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
