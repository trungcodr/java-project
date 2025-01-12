package view;

import service.EmployeeService;

import java.util.Scanner;

public class MenuEmployee {
    private Scanner scanner;
    private EmployeeService employeeService;

    public MenuEmployee() {
        this.scanner = new Scanner(System.in) ;
        this.employeeService = new EmployeeService();
    }

    //Menu nhan vien
    public void displayEmployeeMenu() {
        while (true){
            System.out.println("Chào mừng nhân viên, bạn có thể thực hiện công việc sau:");
            System.out.println("1. Xem lịch làm việc ");
            System.out.println("2. Xem lương");
            System.out.println("3. Xác nhận lịch đặt của khách hàng");

            System.out.println("0. Đăng xuất");
            System.out.print("Mời bạn nhập lựa chọn: ");
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1:
                        employeeService.viewWordkSchedule();
                        break;
                    case 2:
                        employeeService.viewSalary();
                        break;
                    case 3:
                        employeeService.confirmBooking();
                        break;
                    case 0:
                        System.out.println("Đăng xuất thành công.");
                        return;
                    default:
                        System.out.println("Nhập không hợp lệ, mời bạn nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
