package service;

import data.CustomerData;
import data.ServiceData;
import entities.Customer;
import entities.Service;
import utils.Validation;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private Scanner scanner;
    private List<Service> services;
    private List<Customer> customers;

    public CustomerService() {
        this.scanner = new Scanner(System.in);
        this.services = ServiceData.loadServices();
        this.customers = CustomerData.loadCustomer();

    }
    //Phuong thuc them thong tin khach hang moi
    public void addCustomerInfo() {
        System.out.print("Nhập họ tên khách hàng: ");
        String nameCustomer = scanner.nextLine();
        if (nameCustomer.trim().isEmpty()) {
            System.out.println("Tên khách hàng không được để trống.");
            return;
        }
        System.out.print("Nhập số điện thoại khách hàng: ");
        String phone = scanner.nextLine();
        if (!Validation.isValidPhoneNumber(phone)){
            System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
            return;
        }
        // Kiểm tra trùng lap khach hang bang số điện thoại
        for (Customer existingCustomer : customers) {
            if (existingCustomer.getPhone().equals(phone)) {
                System.out.println("Khách hàng đã tồn tại. Không thể thêm mới.");
                return;
            }
        }
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        if (address.trim().isEmpty()) {
            System.out.println("Địa chỉ không được để trống.");
            return;
        }

        Customer customer = new Customer(nameCustomer,phone,address);
        customers.add(customer);
        CustomerData.saveCustomer(customers);
        System.out.println("Thêm thông tin khách hàng thành công.");
    }


    //Phuong thuc cap nhat thong tin khach hang
    public void updateCustomerInfo(){
        System.out.print("Nhập số điện thoại của khách hàng: ");
        String phone = scanner.nextLine();

        //Tim khach hang dua tren so dien thoai
        Customer targetCustomer = null;
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)){
                targetCustomer = customer;
                break;
            }
        }
        if (targetCustomer == null) {
            System.out.println("Không tìm thấy khách hàng với số điện thoại: " + phone);
        }

        //Hien thi thong tin khach hang hie tai
        System.out.println("Thông tin khách hàng hiện tại:");
        System.out.println("1. ID khách hàng: " + targetCustomer.getCustomerId());
        System.out.println("2. Họ tên khách hàng: " + targetCustomer.getNameCustomer());
        System.out.println("3. Số điện thoại khách hàng: " + targetCustomer.getPhone());
        System.out.println("4. Địa chỉ khách hàng: " + targetCustomer.getAddress());

        //Lua chon muc can chinh sua
        System.out.println("Chon muc can chinh sua");
        System.out.println("1. Ho ten");
        System.out.println("2. So dien thoai");
        System.out.println("3. Dia chi");
        System.out.println("0. Huy");
        System.out.print("Mời bạn nhập lựa chọn: ");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                System.out.print("Nhập tên khách hàng mới: ");
                String newName = scanner.nextLine();
                if (newName.trim().isEmpty()){
                    System.out.println("Tên khách hàng không được để trống.");
                    return;
                }
                targetCustomer.setNameCustomer(newName);
                break;
            case 2:
                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = scanner.nextLine();
                if (!Validation.isValidPassword(newPhone)){
                    System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
                    return;
                }
                for (Customer existingCustomer : customers) {
                    if (existingCustomer.getPhone().equals(newPhone)) {
                        System.out.println("Số điện thoại đã tồn tại. Không thể cập nhật.");
                        return;
                    }
                }
                targetCustomer.setPhone(newPhone);
                break;
            case 3:
                System.out.println("Nhập địa chỉ mới: ");
                String newAddress = scanner.nextLine();
                if (newAddress.trim().isEmpty()) {
                    System.out.println("Địa chỉ không được để trống.");
                    return;
                }
                targetCustomer.setAddress(newAddress);
                break;
            case 0:
                System.out.println("Hủy chỉnh sửa thông tin.");
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        CustomerData.saveCustomer(customers);
        System.out.println("Cập nhật thông tin khách hàng thành công!");
    }




    //Phuong thuc xem danh sach dich vu
    public void viewListService() {
        if (services.isEmpty()) {
            System.out.println("Danh sách dịch vụ trống.");
            return;
        }
        System.out.println("Danh sách dịch vụ");
        System.out.println("+------------+--------------------+---------------+");
        System.out.println("| Mã dịch vụ |     Tên dịch vụ    |    Giá tiền   |");
        System.out.println("+------------+--------------------+---------------+");
        for (Service service : services) {
            System.out.printf(
                    "| %-10s | %-18s | %13s |\n",
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getServicePrice()
                    );
        }
        System.out.println("+------------+--------------------+---------------+");
    }

    //Phuong thuc dat lich dich vu
    public void addBooking() {

    }

    private Customer findCustomerByPhone(String phone) {
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                return customer;
            }
        }
        return null;
    }
    private Service  findServiceById(String serviceId) {
        for (Service service : services) {
            if (service.getServiceId().equals(serviceId)){
                return service;
            }
        }
        return null;
    }
}
