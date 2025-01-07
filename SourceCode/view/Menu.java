package view;

import service.UserService;


import java.util.Scanner;

public class Menu {
    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public void displayMainMenu(){
        while (true){
            System.out.println("Cửa hàng chăm sóc thú cưng");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Thoát chương trinh");
            System.out.print("Mời bạn nhập lựa chọn: ");
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose){
                    case 1:
                        userService.login();
                        break;
                    case 2:
                        userService.register();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }



}
