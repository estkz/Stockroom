public class Order {
    private final String customerName;
    // private String[] itemIDs;
    private String deliveryAddress;

    Database db = new Database();

    public Order(int orderID) {
        customerName = db.getDBString("orders", "CustomerName", "order_id = " + orderID);

        deliveryAddress = db.getDBString("orders", "Adress", "order_id = " + orderID);
        System.out.println(customerName);
        System.out.println(deliveryAddress);
    }

    public String getCustomerName(){
        return this.customerName;
    }
}
