package service;

import data.BookingData;
import data.EmployeeData;
import data.WorkScheduleData;
import entities.Booking;
import entities.BookingStatus;
import entities.Employee;
import entities.WorkSchedule;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private Scanner scanner;
    private List<WorkSchedule> workSchedules;
    private List<Employee> employees;
    private List<Booking> bookings;
    public EmployeeService() {
        this.scanner = new Scanner(System.in);
        this.workSchedules = WorkScheduleData.loadWorkSchedules();
        this.employees = EmployeeData.loadEmployees();
        this.bookings = BookingData.loadBookings();
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

    public void confirmBooking() {
        if (bookings.isEmpty()) {
            System.out.println("Danh sách đặt lịch trống.");
        }
        System.out.println("Danh sách đặt lịch của khách hàng:");
        for (Booking booking : bookings) {
            if (booking.getStatus() == BookingStatus.BOOKED) {
                System.out.println(booking);
            }
        }
        System.out.print("Nhập id đặt lịch để xác nhận: ");
        String bookingId = scanner.nextLine();
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            System.out.println("Id đặt lịch không hợp lệ.");
            return;
        } else {
            booking.setStatus(BookingStatus.CONFIRMED);
            BookingData.saveBooking(bookings);
            System.out.println("Xác nhận lịch đặt dịch vụ của khách hàng thành công!");
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
    private Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

}
