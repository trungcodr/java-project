package data;

import entities.Employee;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entities.Employee.fromString;

public class EmployeeData {
    private static final String EMPLOYEE_FILE_NAME = "employee.txt";

    //Doc du lieu tu file
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_FILE_NAME))){
            String line;
            while ((line=br.readLine()) != null){
                employees.add(fromString(line));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Khong the doc file nhan vien" + e.getMessage());
        }
        return employees;
    }

    //Ghi du lieu vao file
    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_FILE_NAME))){
            for (Employee employee : employees) {
                bw.write(employee.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Khong the ghi file nhan vien" + e.getMessage());
        }
    }


}
