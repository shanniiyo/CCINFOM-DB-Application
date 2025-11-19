public void SalesTransactionperform(Staff staff, int productID, int clientID, int quantity) {

    if (!staff.isActive()) {
        System.out.println("Staff is inactive");
        return;
    }

    try (Connection conn = DatabaseConnector.getConnection()) {

        Product product = ProductDAO.getProductByID(productID);

        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Not enough stock. Available: " + product.getQuantity());
            return;
        }

        double totalPrice = product.getPrice() * quantity;

        String insertSQL =
            "INSERT INTO SalesTransaction (StaffID, ProductID, ClientID, Quantity, TotalPrice, SalesDate) " +
            "VALUES (?, ?, ?, ?, ?, NOW())";

        PreparedStatement ps = conn.prepareStatement(insertSQL);
        ps.setInt(1, staff.getStaffID());
        ps.setInt(2, productID);
        ps.setInt(3, clientID);
        ps.setInt(4, quantity);
        ps.setDouble(5, totalPrice);

        ps.executeUpdate();

        int newQty = product.getQuantity() - quantity;
        ProductDAO.updateProductQuantity(productID, newQty);

        staff.addSales(totalPrice);

        System.out.println("SALE COMPLETED");
        System.out.println("Product: " + product.getBrand());
        System.out.println("Quantity Sold: " + quantity);
        System.out.println("Total Price: ₱" + totalPrice);
        System.out.println("Remaining Stock: " + newQty);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
