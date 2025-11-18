import java.sql.*;
import java.time.LocalDate;

public class ProductDAO {

    // Load record from DB
    public static Product getProductByID(int productID) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                    rs.getInt("ProductID"),
                    rs.getString("Brand"),
                    rs.getInt("Quantity"),
                    rs.getDouble("Price"),
                    rs.getDate("DateAdded").toLocalDate(),
                    rs.getDate("Expiration").toLocalDate()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update stock in DB
    public static void updateProductQuantity(int productID, int newQty) {
        String sql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQty);
            stmt.setInt(2, productID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update price in DB
    public static void updateProductPrice(int productID, double newPrice) {
        String sql = "UPDATE Product SET Price = ? WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, productID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
