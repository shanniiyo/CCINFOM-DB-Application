public class Client {
    private String clientID;
    private String name;
    private String address;
    private String contactPerson;
    private String contactInfo;
    
    private List<Order> orders = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Client(String clientID, String name, String address,
                  String contactPerson, String contactInfo) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
        this.contactPerson = contactPerson;
        this.contactInfo = contactInfo;
    }

    public void viewOrders(){
        
    }

    public void viewPaymentHistory(){

    }
}
