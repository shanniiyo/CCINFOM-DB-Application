import java.util.List;
import java.util.ArrayList;

public class Client {
    private String clientID;
    private String name;
    private String address;
    private String contactPerson;
    private String contactInfo;
    
    private List<Order> orders = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    public Client(String clientID, String name, String address,
                  String contactPerson, String contactInfo) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
        this.contactPerson = contactPerson;
        this.contactInfo = contactInfo;
    }

    public void viewOrders(){
        if (orders.isEmpty()){
            System.out.println("No orders found");
        }
        for (Order order : orders){
            System.out.println(order.toString());
        }
    }

    public void viewPaymentHistory(){
        if (payments.isEmpty()){
            System.out.println("No payments made");
        }
        for (Payment payment : payments){
            System.out.println(payment.toString());
        }
    }
}
