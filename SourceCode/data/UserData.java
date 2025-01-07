package data;

import entities.Customer;
import entities.Employee;
import entities.User;

import java.io.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class UserData {
    private static final String USER_FILE_NAME = "users.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_NAME))){
            String line;
            while ((line = br.readLine()) != null){
                users.add(User.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file người dùng." + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User>users){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_NAME))){
            for (User user : users){
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi file người dùng." + e.getMessage());
        }
    }

    public static User findUserByUsername(String username) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Trả về null nếu không tìm thấy


    }

}
