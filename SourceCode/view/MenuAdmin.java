package view;

import service.AdminService;

import java.util.Scanner;

public class MenuAdmin {
    private AdminService adminService;
    private Scanner scanner;
    public MenuAdmin() {
        this.adminService = new AdminService() ;
        this.scanner = new Scanner(System.in);
    }

    //Menu Admin
    public void displayAdminMenu(){
       while (true){
           System.out.println("----------------------------------------------------------");
           System.out.println("Chào mừng Admin, bạn có thể thực hiện công việc sau:");
           System.out.println("1. Quản lý tài khoản");
           System.out.println("2. Quản lý nhân viên");
           System.out.println("3. Quản lý dịch vụ");
           System.out.println("4. Quản lý lịch làm việc");



           System.out.println("0. Đăng xuất");
           System.out.print("Mời bạn nhập lựa chọn: ");
           try {
               int choose = Integer.parseInt(scanner.nextLine());
               switch (choose){
                   case 1:
                        manageAccount();
                        break;
                   case 2:
                        manageEmployeeMenu();
                        break;
                   case 3:
                       manageServiceMenu();
                       break;
                   case 4:
                        manageWordSchedule();
                        break;

                   case 0:
                       System.out.println("Đăng xuất thành công!");
                       return;
                   default:
                       System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
               }
            } catch (NumberFormatException e){
               System.out.println("Vui lòng nhập số hợp lệ!");
           }

       }
    }


    // Menu con quan ly tai khoan
    private void manageAccount(){
        while (true){
            System.out.println("------------------------------------------");
            System.out.println("1. Tạo tài khoản cho nhân viên ");
            System.out.println("2. Xem danh sách tài khoản");
            System.out.println("0. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    adminService.createAccountEmployee();
                    break;
                case 2:
                    adminService.viewAllUsers();
                    break;
                case 0:
                    System.out.println("Quay lai menu admin");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }


    // Menu con cua quan ly dich vu
    private void manageServiceMenu(){
        while (true){
            System.out.println("------------------------------------------");
            System.out.println("1. Thêm dịch vụ");
            System.out.println("2. Sửa dịch vụ");
            System.out.println("3. xóa dịch vụ");
            System.out.println("4. Xem danh sách dịch vụ");
            System.out.println("0. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    adminService.insertService();
                    break;
                case 2:
                    adminService.updateService();
                    break;
                case 3:
                    adminService.deleteService();
                case 4:
                    adminService.viewListService();
                    break;
                case 0:
                    System.out.println("Quay lai menu admin");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        }
    }

    //Menu con quan ly nhan vien
    private void manageEmployeeMenu() {
        while (true){
            System.out.println("----------------------------------------");
            System.out.println("1. Thêm mới nhân viên");
            System.out.println("2. Xem danh sách nhân viên");
            System.out.println("3. Tăng lương");
            System.out.println("4. Giảm lương");
            System.out.println("5. Hien thi lich su thay doi luong");
            System.out.println("0. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");

            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    adminService.insertEmployee();
                   break;
                case 2:
                    adminService.viewEmployees();
                    break;
                case 3:
                    adminService.modifySalary("+");
                    break;
                case 4:
                    adminService.modifySalary("-");
                    break;
                case 5:
                    adminService.viewSalaryHistory();
                    break;
                case 0:
                    System.out.println("Quay lại menu admin");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại");
            }
        }
    }

    //Menu con quan ly lich lam viec
    private void manageWordSchedule(){
        while (true){
            System.out.println("-------------------------------------");
            System.out.println("1. Xem danh sách nhân viên");
            System.out.println("2. Thêm lịch làm việc");
            System.out.println("3. Xem lịch làm việc");
            System.out.println("0. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    adminService.viewEmployees();
                    break;
                case 2:
                    adminService.inputWorkScheduleInfo();
                    break;
                case 3:
                    adminService.viewWorkSchdule();
                    break;
                case 0:
                    System.out.println("Quay lại menu admin");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại");
            }
        }
    }
}
