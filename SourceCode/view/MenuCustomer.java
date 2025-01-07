package view;
import data.CustomerData;
import data.ServiceData;
import entities.Customer;
import entities.Service;

import service.CustomerService;

import java.util.List;
import java.util.Scanner;



public class MenuCustomer {
    private Scanner scanner;


    private CustomerService customerService;




    public MenuCustomer() {
        this.scanner = new Scanner(System.in);
        this.customerService = new CustomerService();


    }

    public void displayMenuCustomer(){
       while (true){
           System.out.println("----------------------------------------------------------");
           System.out.println("Chào mừng khách hàng, bạn có thể thực hiện công việc sau:");
           System.out.println("1. Quản lý thông tin cá nhân");
           System.out.println("2. Đặt lịch dịch vụ");
           System.out.println("3. Theo dõi tình trạng thú cưng ");
           System.out.println("4. Thanh toán ");
           System.out.println("5. Phản hồi dịch vụ");
           System.out.println("0. Đăng xuất.");
           System.out.print("Mời bạn nhập lựa chọn: ");
           try {
               int choose = Integer.parseInt(scanner.nextLine());
               switch (choose){
                   case 1:
                       managePersonalInfo();
                       break;
                   case 2:
                       orderServiceMenu();
                       break;
                   case 3:
                       break;
                   case 0:
                       System.out.println("Đăng xuất thành công!");
                       return;
                   default:
                       System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
               }
           } catch (NumberFormatException e) {
               System.out.println("Vui lòng nhập số hợp lệ!" +e.getMessage());
           }

       }
    }


    //Menu con quan ly thong tin ca nhan
    private void managePersonalInfo(){
        while (true) {
            System.out.println("");
            System.out.println("1. Thêm thông tin cá nhân");
            System.out.println("2. Cập nhật thông tin cá nhân");
            System.out.println("0. Quay lại");
            System.out.println("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            try {
                switch (choose){
                    case 1:
                        customerService.addCustomerInfo();
                        break;
                    case 2:
                        customerService.updateCustomerInfo();
                        break;
                    case 0:
                        System.out.println("Quay lại menu khách hàng.");
                        return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
    //Menu con dat lich dich vu
    private void orderServiceMenu() {
        while (true){
            System.out.println("");
            System.out.println("1. Xem danh sách dịch vụ");
            System.out.println("2. Đặt dịch vụ");
            System.out.println("0. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            try {
                switch (choose){
                    case 1:
                        customerService.viewListService();
                        break;
                    case 2:
                        customerService.addBooking();
                        break;
                    case 0:
                        System.out.println("Quay lại menu khách hàng");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ,vui lòng thử lại");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }



}
