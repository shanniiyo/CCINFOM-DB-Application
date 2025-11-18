// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// public class MainDriver {
//     public static void main(String[] args) {

//         // ============================
//         // Create Product for Inventory
//         // ============================
//         Product paracetamol = new Product(
//                 "P001",
//                 "Paracetamol 500mg",
//                 100,                    // initial quantity
//                 5.00,                   // price per unit
//                 LocalDate.now(),
//                 LocalDate.of(2026, 5, 1)
//         );

//         // Supporting objects
//         Supplier supplier = new Supplier("SUP01", "Mercury Pharma");
//         Client Watsons = new Client(1, "Watsons");
//         Staff emp = new Staff(1, "Employee 01", "Assistant Manager", true);

//         System.out.println("\n=== INITIAL STOCK LEVEL ===");
//         paracetamol.viewStockLevels();


//         // ============================
//         // RECEIVE TRANSACTION (add stock)
//         // ============================
//         InventoryTransaction receiveTrans = new InventoryTransaction(
//                 "T001",
//                 LocalDate.now(),
//                 paracetamol,
//                 supplier,
//                 null,           // no client for receiving
//                 emp,
//                 50,
//                 "RECEIVE",
//                 "PENDING"
//         );

//         receiveTrans.updateInventoryProducts();   // perform receive
//         System.out.println("\n=== AFTER RECEIVING 50 UNITS ===");
//         paracetamol.viewStockLevels();
//         paracetamol.viewTransaction(receiveTrans);


//         // ============================
//         // DELIVERY TRANSACTION (deduct stock)
//         // ============================
//         InventoryTransaction deliverTrans = new InventoryTransaction(
//                 "T002",
//                 LocalDate.now(),
//                 paracetamol,
//                 null,
//                 Watsons,
//                 emp,
//                 100,
//                 "DELIVERY",
//                 "PENDING"
//         );

//         deliverTrans.updateInventoryProducts();   // perform delivery
//         System.out.println("\n=== AFTER DELIVERING 30 UNITS TO WATSONS ===");
//         paracetamol.viewStockLevels();
//         paracetamol.viewTransaction(deliverTrans);


//         // ============================
//         // ATTEMPT DELIVERY MORE THAN STOCK
//         // ============================
//         InventoryTransaction failedDelivery = new InventoryTransaction(
//                 "T003",
//                 LocalDate.now(),
//                 paracetamol,
//                 null,
//                 Watsons,
//                 emp,
//                 500,            // too many!
//                 "DELIVERY",
//                 "PENDING"
//         );

//         failedDelivery.updateInventoryProducts(); // should fail
//         System.out.println("\n=== FAILED DELIVERY ATTEMPT (500 UNITS) ===");
//         paracetamol.viewTransaction(failedDelivery);
//         paracetamol.viewStockLevels();

//         System.out.println("\n=== END OF TEST ===");

//         // REPORT
//         List<Product> list = new ArrayList<>();
//         list.add(paracetamol);

//         InventoryReport report = new InventoryReport();
//         report.generateReport(list);

//         emp.viewSalesPerformance(500.00);

//         // Example objects (assume these come from DB or user input)
//         Product Bandage = new Product
//         ("1", "Bandage", 50, 100.0, LocalDate.now(), LocalDate.of(2026, 5, 1));
//         Staff emp1 = new Staff(1, "'Alice Santos'", "Medical Supply Clerk / Technician", true);

//         // Create ProductReturn object
//         ProductReturn returnObj = new ProductReturn(
//             null,                   // returnID will be auto-generated
//             LocalDate.now(),
//             Bandage,
//             Watsons,
//             emp1,
//             "Defective",
//             6,
//             LocalDate.now().minusDays(10) // purchase date 10 days ago
//         );

//         // Validate
//         returnObj.validateReturnRequest();

//         // Update stock levels
//         returnObj.updateStockLevels();

//         // Insert into database
//         ProductReturnDAO.insertReturn(returnObj);
//     }
// }


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDriver {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Sample data (normally from DB)
        Product paracetamol = new Product(
                1,
                "Paracetamol 500mg",
                100,
                5.00,
                LocalDate.now(),
                LocalDate.of(2026, 5, 11)
        );

        Supplier supplier = new Supplier("SUP01", "Mercury Pharma");
        Client watsons = new Client(1, "Watsons", null, null, null);
        Staff emp = new Staff(1, "Employee 01", "Assistant Manager", true);

        // Storage (temporary; replace with DB later)
        List<Product> productList = new ArrayList<>();
        productList.add(paracetamol);

        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Enter choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewStockLevels(productList);
                    break;

                case 2:
                    handleProcurement(paracetamol, supplier, emp);
                    break;

                case 3:
                    handleDelivery(paracetamol, watsons, emp);
                    break;

                case 4:
                    handleInventoryTransaction(paracetamol, supplier, watsons, emp);
                    break;

                case 5:
                    handleProductReturn(watsons, emp);
                    break;

                case 0:
                    System.out.println("Exiting application...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }

    // ---------------- MENU DISPLAY ----------------
    private static void printMenu() {
        System.out.println("\n============================");
        System.out.println("  INVENTORY MANAGEMENT MENU ");
        System.out.println("============================");
        System.out.println("[1] View Stock Levels");
        System.out.println("[2] Procurement Transaction (Receive Items)");
        System.out.println("[3] Delivery Transaction");
        System.out.println("[4] Generic Inventory Transaction");
        System.out.println("[5] Product Return");
        System.out.println("[0] Exit");
        System.out.println("============================");
    }

    // ---------------- INPUT HANDLERS ----------------
    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static String getStringInput() {
        sc.nextLine(); // clear buffer
        return sc.nextLine();
    }

    // ---------------- OPTION 1: VIEW STOCK ----------------
    private static void viewStockLevels(List<Product> productList) {
        System.out.println("\n=== CURRENT STOCK LEVELS ===");
        for (Product p : productList) {
            p.viewStockLevels();
        }
    }

    // ---------------- OPTION 2: PROCUREMENT (RECEIVING) ----------------
    private static void handleProcurement(Product product, Supplier supplier, Staff staff) {
        System.out.println("\n=== PROCUREMENT TRANSACTION ===");

        System.out.print("Enter quantity received: ");
        int qty = getIntInput();

        InventoryTransaction receive = new InventoryTransaction(
                "T" + System.currentTimeMillis(),
                LocalDate.now(),
                product,
                supplier,
                null,
                staff,
                qty,
                "RECEIVE",
                "PENDING"
        );

        receive.updateInventoryProducts();
        product.viewTransaction(receive);
    }

    // ---------------- OPTION 3: DELIVERY TRANSACTION ----------------
    private static void handleDelivery(Product product, Client client, Staff staff) {
        System.out.println("\n=== DELIVERY TRANSACTION ===");

        System.out.print("Enter quantity to deliver: ");
        int qty = getIntInput();

        InventoryTransaction delivery = new InventoryTransaction(
                "T" + System.currentTimeMillis(),
                LocalDate.now(),
                product,
                null,
                client,
                staff,
                qty,
                "DELIVERY",
                "PENDING"
        );

        delivery.updateInventoryProducts();
        product.viewTransaction(delivery);
    }

    // ---------------- OPTION 4: GENERIC INVENTORY TRANSACTION ----------------
    private static void handleInventoryTransaction(Product product, Supplier supplier, Client client, Staff staff) {
        System.out.println("\n=== INVENTORY TRANSACTION ===");
        System.out.println("[1] Receive");
        System.out.println("[2] Deliver");
        System.out.print("Select transaction type: ");

        int typeChoice = getIntInput();
        String type;

        if (typeChoice == 1) type = "RECEIVE";
        else if (typeChoice == 2) type = "DELIVERY";
        else {
            System.out.println("Invalid type.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = getIntInput();

        InventoryTransaction trans = new InventoryTransaction(
                "T" + System.currentTimeMillis(),
                LocalDate.now(),
                type.equals("RECEIVE") ? product : product,
                type.equals("RECEIVE") ? supplier : null,
                type.equals("DELIVERY") ? client : null,
                staff,
                qty,
                type,
                "PENDING"
        );

        trans.updateInventoryProducts();
        product.viewTransaction(trans);
    }

    // ---------------- OPTION 5: PRODUCT RETURN ----------------
    private static void handleProductReturn(Client client, Staff staff) {
        System.out.println("\n=== PRODUCT RETURN ===");

        System.out.print("Enter Product ID: ");
        int productID = getIntInput();

        System.out.print("Enter Product Name: ");
        String brand = getStringInput();

        System.out.print("Enter Quantity Returned: ");
        int qty = getIntInput();

        System.out.print("Enter Reason (Defective/Expired): ");
        String reason = getStringInput();

        Product p = new Product(
                productID, brand, 0, 0.0,
                LocalDate.now(),
                LocalDate.now().plusMonths(3)
        );

        ProductReturn pr = new ProductReturn(
            null,
            LocalDate.now(),
            p,
            client,
            staff,
            reason,
            qty,
            LocalDate.now().minusDays(10)
        );

        pr.validateReturnRequest();
        pr.updateStockLevels();

        ProductReturnDAO.insertReturn(pr);
    }
}
