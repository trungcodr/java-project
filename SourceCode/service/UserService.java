package service;

import data.UserData;
import entities.User;
import utils.Validation;
import view.MenuAdmin;
import view.MenuCustomer;
import view.MenuEmployee;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private List<User> users;
    private Scanner scanner;

    public UserService() {
        this.users = UserData.loadUsers();
        this.scanner = new Scanner(System.in);
    }


    //Ham dang nhap
    public void login(){
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        User user = findUserByUserName(username);
        if (user == null){
            System.out.println("Kiểm tra lại username.");
            return;
        }

        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        if (!user.getPassword().equals(password)){
            System.out.println("1. Đăng nhập lại.");
            System.out.println("2. Quên mật khẩu.");
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1){
                login();
            }else if (choose == 2) {
                forgotPassword();
            }
            return;
        }

        System.out.println("Đăng nhập thành công.");

        switch (user.getRole()){
            case "admin":
                MenuAdmin menuAdmin = new MenuAdmin();
                menuAdmin.displayAdminMenu();
                break;
            case "customer":
                MenuCustomer menuCustomer = new MenuCustomer();
                menuCustomer.displayMenuCustomer();
                break;
            case "employee":
                MenuEmployee menuEmployee = new MenuEmployee();
                menuEmployee.displayEmployeeMenu();
                break;
            default:
                System.out.println("Vai trò không hợp lệ!");
                break;
        }

    }



    //Ham dang ky tai khoan
    public void register(){
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        if(findUserByUserName(username) != null){
            System.out.println("Username đã tồn tại.");
            return;
        }

        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        if (!Validation.isValidEmail(email) || findUserByEmail(email) != null){
           System.out.println("Email không hợp lệ hoặc đã tồn tại");
           return;
       }

        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        if (!Validation.isValidPassword(password)){
            System.out.println("Password không hợp lệ.");
            return;
        }


          String role = "customer";
        User newUser = new User(username, email, password,role);
        users.add(newUser);
        UserData.saveUsers(users);
        System.out.println("Tạo tài khoản thành công!");
        System.out.println("------------------------------------");
    }


    //Ham quen mat khau
    private void forgotPassword(){
        System.out.print("Nhập email:");
        String email = scanner.nextLine();
        User user = findUserByEmail(email);
        if (user == null) {
            System.out.println("Email chưa tồn tại.");
            return;
        }

        System.out.print("Nhập mật khẩu mới:");
        String newPassword = scanner.nextLine();
        if (!Validation.isValidPassword(newPassword)) {
            System.out.println("Mật khẩu không hợp lệ.");
            return;
        }

        user = new User(user.getUsername(), user.getEmail(), newPassword, user.getRole());
        UserData.saveUsers(users);
        System.out.println("Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
    }



    //Tim user bang username
    private User findUserByUserName(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    //Tim user bang email
    private User findUserByEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }



}
