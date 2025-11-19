
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {

    // Add a new product to the DB
    public static void addProduct(Product product) {
        String sql = "INSERT INTO Product (ProductID, Brand, Quantity, Price, DateAdded, Expiration) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getBrand());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setDate(5, java.sql.Date.valueOf(product.getDateAdded()));
            stmt.setDate(6, java.sql.Date.valueOf(product.getExpiration()));

            stmt.executeUpdate();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load record from DB
    public static Product getProductByID(int productID) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        Product product = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Brand"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDate("ManufactureDate").toLocalDate(),
                        rs.getDate("Expiration").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Update stock in DB
    public static void updateProductQuantity(int productID, int newQty) {
        String sql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQty);
            stmt.setInt(2, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product quantity updated in database.");
            } else {
                System.out.println("Product with ID " + productID + " not found for quantity update.");
            }

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
            System.out.println("Product price updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product from the DB
    public static void deleteProduct(int productID) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            stmt.executeUpdate();
            System.out.println("Product has been deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
