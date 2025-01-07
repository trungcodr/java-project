package data;

import entities.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entities.Customer.fromString;

public class CustomerData {
    private static final String FILE_NAME = "customers.txt";

    //Doc danh sach khach hang tu tep
    public static List<Customer> loadCustomer() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = br.readLine()) != null){
                customers.add(fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file khách hàng: " + e.getMessage());
        }
        return customers;
    }

    //Ghi danh sach khach hang vao tep
    public static void saveCustomer(List<Customer>customers){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Customer customer : customers) {
                bw.write(customer.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi tệp khách hàng: " + e.getMessage());
        }
    }





}
