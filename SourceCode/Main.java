import data.CustomerData;
import data.UserData;
import entities.Customer;
import entities.Employee;
import entities.User;
import view.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = CustomerData.loadCustomer();

        if (customers.isEmpty()) {
            System.out.println("");
        }

        Menu menu = new Menu();
        menu.displayMainMenu();

//  Test ghi them truong luong nhan vien vao file users.txt
//    List<User> users = UserData.loadUsers();
//    BigDecimal salary = new BigDecimal("5000000");
//    users.add(new Employee("domixi","domixi@gmail.com","Domixi102.",salary));
//    UserData.saveUsers(users);
    }
}
