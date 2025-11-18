import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryDAO {
    public static void addInventory(Inventory inventory) {
        String sql = "INSERT INTO Inventory (InventoryID, ProductID, Quantity, LastUpdated) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inventory.getInventoryID());
            stmt.setString(2, inventory.getProductID());
            stmt.setInt(3, inventory.getQuantity());
            stmt.setDate(4, java.sql.Date.valueOf(inventory.getLastUpdated()));

            stmt.executeUpdate();
            System.out.println("Inventory added successfully.");

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE quantity
    public static void updateInventoryQuantity(String productID, int newQty) {
        String sql = "UPDATE Inventory SET Quantity = ?, LastUpdated = ? WHERE ProductID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQty);
            stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            stmt.setString(3, productID);

            stmt.executeUpdate();
            System.out.println("Inventory quantity updated.");

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DELETE inventory
    public static void deleteInventory(String inventoryID) {
        String sql = "DELETE FROM Inventory WHERE InventoryID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, inventoryID);
            stmt.executeUpdate();
            System.out.println("Inventory has been deleted!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
