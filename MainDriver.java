import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainDriver {
    public static void main(String[] args) {

        // ============================
        // Create Product for Inventory
        // ============================
        Product paracetamol = new Product(
                "P001",
                "Paracetamol 500mg",
                100,                    // initial quantity
                5.00,                   // price per unit
                LocalDate.now(),
                LocalDate.of(2026, 5, 1)
        );

        // Supporting objects
        Supplier supplier = new Supplier("SUP01", "Mercury Pharma");
        Client Watsons = new Client("CL01", "Watsons");
        Staff emp = new Staff("ST01", "Employee 01", "Assistant Manager", true);

        System.out.println("\n=== INITIAL STOCK LEVEL ===");
        paracetamol.viewStockLevels();


        // ============================
        // RECEIVE TRANSACTION (add stock)
        // ============================
        InventoryTransaction receiveTrans = new InventoryTransaction(
                "T001",
                LocalDate.now(),
                paracetamol,
                supplier,
                null,           // no client for receiving
                emp,
                50,
                "RECEIVE",
                "PENDING"
        );

        receiveTrans.updateInventoryProducts();   // perform receive
        System.out.println("\n=== AFTER RECEIVING 50 UNITS ===");
        paracetamol.viewStockLevels();
        paracetamol.viewTransaction(receiveTrans);


        // ============================
        // DELIVERY TRANSACTION (deduct stock)
        // ============================
        InventoryTransaction deliverTrans = new InventoryTransaction(
                "T002",
                LocalDate.now(),
                paracetamol,
                null,
                Watsons,
                emp,
                100,
                "DELIVERY",
                "PENDING"
        );

        deliverTrans.updateInventoryProducts();   // perform delivery
        System.out.println("\n=== AFTER DELIVERING 30 UNITS TO WATSONS ===");
        paracetamol.viewStockLevels();
        paracetamol.viewTransaction(deliverTrans);


        // ============================
        // ATTEMPT DELIVERY MORE THAN STOCK
        // ============================
        InventoryTransaction failedDelivery = new InventoryTransaction(
                "T003",
                LocalDate.now(),
                paracetamol,
                null,
                Watsons,
                emp,
                500,            // too many!
                "DELIVERY",
                "PENDING"
        );

        failedDelivery.updateInventoryProducts(); // should fail
        System.out.println("\n=== FAILED DELIVERY ATTEMPT (500 UNITS) ===");
        paracetamol.viewTransaction(failedDelivery);
        paracetamol.viewStockLevels();

        System.out.println("\n=== END OF TEST ===");

        // REPORT
        List<Product> list = new ArrayList<>();
        list.add(paracetamol);

        InventoryReport report = new InventoryReport();
        report.generateReport(list);

        emp.viewSalesPerformance(500.00);
    }

}
