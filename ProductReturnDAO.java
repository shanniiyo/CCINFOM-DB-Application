import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductReturnDAO {

    public static void insertReturn(ProductReturn returnObj) {
        String sql = "INSERT INTO ProductReturn (ProductID, ClientID, StaffID, Reason, Quantity, ReturnDate, PurchaseDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
String updateProductSql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement updateStmt = conn.prepareStatement(updateProductSql)) {

            pstmt.setInt(1, returnObj.getProduct().getProductID());
            pstmt.setInt(2, returnObj.getClient().getClientID());
            pstmt.setInt(3, returnObj.getStaff().getStaffID());
            pstmt.setString(4, returnObj.getReason());
            pstmt.setInt(5, returnObj.getQuantity());
            pstmt.setTimestamp(6, Timestamp.valueOf(returnObj.getTransactionDate().atStartOfDay()));
            pstmt.setDate(7, java.sql.Date.valueOf(returnObj.getPurchaseDate()));

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) inserted into ProductReturn table.");

        // Update inventory if product can be restocked
            long daysUntilExpiry = java.time.temporal.ChronoUnit.DAYS.between(
                    java.time.LocalDate.now(), returnObj.getProduct().getExpiration());

            if (daysUntilExpiry >= 30) {
                int newQuantity = returnObj.getProduct().getQuantity() + returnObj.getQuantity();

                updateStmt.setInt(1, newQuantity);
                updateStmt.setInt(2, returnObj.getProduct().getProductID());  // match your parameter style
                int rowsUpdated = updateStmt.executeUpdate();

                System.out.println("Product inventory updated. New quantity: " + newQuantity +
                                   " (" + rowsUpdated + " row(s) affected).");
            } else {
                System.out.println("Returned product cannot be restocked due to expiration or near-expiry.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
