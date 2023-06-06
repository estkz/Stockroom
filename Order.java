public class Order {
    private final int orderID;
    private final String customerName;
    private final String deliveryAddress;

    Database db = new Database();

    public Order(int orderID) {
        this.orderID = orderID;
        customerName = db.getDBString("orders", "CustomerName", "order_id = " + orderID);
        deliveryAddress = db.getDBString("orders", "Adress", "order_id = " + orderID);
        System.out.println(customerName);
        System.out.println(deliveryAddress);
    }

    public String getCustomerName(){
        return this.customerName;
    }
    public String getDeliveryAddress(){
        return this.deliveryAddress;
    }
}