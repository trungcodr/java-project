package service;

import data.EmployeeData;
import data.WorkScheduleData;
import entities.Employee;
import entities.WorkSchedule;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private Scanner scanner;
    private List<WorkSchedule> workSchedules;
    private List<Employee> employees;

    public EmployeeService() {
        this.scanner = new Scanner(System.in);
        this.workSchedules = WorkScheduleData.loadWorkSchedules();
        this.employees = EmployeeData.loadEmployees();
    }

    //Xem lich lam viec cua nhan vien
    public void viewWordkSchedule() {
        System.out.println("Nhâp id nhân viên của bạn: ");
        String employeeId = scanner.nextLine();
        System.out.println("Lịch làm việc của bạn");
        System.out.println("+---------------+-------------+--------------+---------------------+");
        System.out.println("| Ngày làm việc | Giờ bắt đầu | Giờ kết thúc | Ghi chú             |");
        System.out.println("+---------------+-------------+--------------+---------------------+");

        boolean hasSchedule = false;
        for (WorkSchedule workSchedule : workSchedules){
            if (workSchedule.getEmployeeId().equals(employeeId)){
                System.out.printf("| %13s | %11s | %12s | %-20s |\n",
                        workSchedule.getWordkDate(),
                        workSchedule.getStartTime(),
                        workSchedule.getEndTime(),
                        workSchedule.getNote());
                hasSchedule = true;
            }
        }
        if (!hasSchedule) {
            System.out.println("Không có lịch làm việc nào được tìm thấy.");
        }
        System.out.println("+---------------+-------------+--------------+---------------------+");
    }

    //Xem luong nhan vien
    public void viewSalary(){
        System.out.println("Nhập id nhân viên của bạn: ");
        String employeeId = scanner.nextLine();
        Employee employee = findEmployeeById(employeeId);
        if (employee !=null) {
            System.out.println("Lương của nhân viên " + employee.getFullname() + ": " + employee.getSalary() + "Vnđ");
        } else {
            System.out.println("Không tìm thấy nhân viên với ID: " + employeeId);
        }
    }

    private Employee findEmployeeById(String employeeId){
        for (Employee employee : employees){
            if (employee.getEmployeeId().equals(employeeId)){
                return employee;
            }
        }
        return null;
    }


}
