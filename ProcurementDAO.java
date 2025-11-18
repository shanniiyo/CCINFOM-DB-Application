import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProcurementDAO {

    public static void addProcurement(Procurement procurement, int addedQty) {
        String sql = "INSERT INTO Procurement (ProcurementID, ProductID, SupplierID, Quantity, DateProcured) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, procurement.getProcurementID());
                stmt.setString(2, procurement.getProductID());
                stmt.setString(3, procurement.getSupplierID());
                stmt.setInt(4, addedQty);
                stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                stmt.executeUpdate();
            }
            
            //Update QTY in DB
            Product product = ProductDAO.getProductByID(procurement.getProductID());
            if (product != null) {
                int newQty = product.getQuantity() + addedQty;
                ProductDAO.updateProductQuantity(procurement.getProductID(), newQty);
            }

            conn.commit(); 
            System.out.println("Procurement transaction completed");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!conn.isClosed()) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

