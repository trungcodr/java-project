package entities;

public class Customer  {
    private static int autoId;
    private String customerId;
    private String nameCustomer;
    private String phone;
    private String address;

    public Customer( String nameCustomer, String phone, String address) {
        this.customerId = "C" + ++autoId;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return customerId + "," + nameCustomer + "," + phone + "," + address;
    }

    public static Customer fromString(String customerString) {
        String[] parts = customerString.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Dữ liệu không hợp lệ: " + customerString);
        }
        String nameCustomer = parts[1];
        String phone = parts[2];
        String address = parts[3];
        return new Customer(nameCustomer,phone,address);
    }
}
