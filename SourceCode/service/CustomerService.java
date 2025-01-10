package service;

import data.BookingData;
import data.CustomerData;
import data.ServiceData;
import entities.Booking;
import entities.BookingStatus;
import entities.Customer;
import entities.Service;
import utils.Validation;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private Scanner scanner;
    private List<Service> services;
    private List<Customer> customers;
    private List<Booking> bookings;

    public CustomerService() {
        this.scanner = new Scanner(System.in);
        this.services = ServiceData.loadServices();
        this.customers = CustomerData.loadCustomer();
        this.bookings = BookingData.loadBookings();
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
        System.out.println("Chọn mục cần chỉnh sửa");
        System.out.println("1. Họ tên");
        System.out.println("2. Số điện thoại");
        System.out.println("3. Địa chỉ");
        System.out.println("0. Hủy");
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
        System.out.println("Mời bạn nhập id khách hàng: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Bạn hãy kiểm tra lại mã id khách hàng.");
            return;
        }
        System.out.println("Mời bạn nhập id dịch vụ: ");
        String serviceId = scanner.nextLine();
        Service service = findServiceById(serviceId);
        if (service == null) {
            System.out.println("Bạn hãy kiểm tra lại mã id dịch vụ.");
            return;
        }
        Booking booking = new Booking(customerId,serviceId);
        bookings.add(booking);
        BookingData.saveBooking(bookings);
        System.out.println("Bạn đã đặt dịch vụ thành công.");
    }

    //Phuong thuc xem danh sach dat lich dich vu
    public void viewBooking() {
        System.out.println("Nhập Id khách hàng: ");
        String customerId = scanner.nextLine();
        System.out.println("Danh sách đặt lịch dịch vụ: ");
        for (Booking booking : bookings) {
            if (booking.getCustomerId().equals(customerId)){
                System.out.println("Mã đặt lịch dịch vụ: " + booking.getBookingId());
                System.out.println("Mã dịch vụ: " + booking.getServiceId());
                System.out.println("Thời gian đặt lịch dịch vụ: " + booking.getBookingDateTime());
                System.out.println("Trạng thái đặt dịch vụ: " + booking.getStatus());
            }
        }
    }


    //Phuong thuc huy dich vu da dat
    public void cancelBooking() {
        System.out.println("Nhập id lịch đặt bạn muốn hủy: ");
        String bookingId = scanner.nextLine();
        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookingToCancel = booking;
                break;
            }
        }
        // Kiểm tra xem đặt lịch có tồn tại không
        if (bookingToCancel == null) {
            System.out.println("Booking ID not found!");
            return;
        }
        // Kiểm tra trạng thái hiện tại của đặt lịch
        if (bookingToCancel.getStatus() != BookingStatus.BOOKED) {
            System.out.println("Bạn chỉ có thể hủy khi ở trạng thái BOOKED");
            return;
        }
        bookingToCancel.setStatus(BookingStatus.CANCELED);
        BookingData.saveBooking(bookings);
        System.out.println("Bạn đã hủy dịch vụ thành công.");

    }


    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
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
