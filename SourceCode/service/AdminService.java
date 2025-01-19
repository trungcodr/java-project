package service;

import data.*;
import entities.*;
import utils.Validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AdminService  {
    private List<User> users;
    private Scanner scanner;
    private List<Service> services;
    private List<Employee> employees;
    private List<SalaryHistory> salaryHistories;
    private List<WorkSchedule> workSchedules;
    public AdminService() {
        this.users = UserData.loadUsers();
        this.scanner = new Scanner(System.in);
        this.services = ServiceData.loadServices();
        this.employees = EmployeeData.loadEmployees();
        this.salaryHistories = SalaryHistoryData.loadSalaryHistory();
        this.workSchedules = WorkScheduleData.loadWorkSchedules();
    }

    //Ham them tai khoan nhan vien moi
    public void createAccountEmployee() {
        System.out.print("Nhập username của nhân viên mới: ");
        String username = scanner.nextLine();
        User existingUser = UserData.findUserByUsername(username);
        if (existingUser != null) {
            System.out.println("Username đã tồn tại");
            return;
        }

        System.out.println("Nhập email của nhân viên : ");
        String email = scanner.nextLine();
        if (!Validation.isValidEmail(email)){
            System.out.println("Email không hợp lệ");
            return;
        }

        System.out.println("Nhập password của nhân viên: ");
        String password = scanner.nextLine();
        if (!Validation.isValidPassword(password)){
            System.out.println("Password không hợp lệ");
            return;
        }
        User user = new User(username,email,password,"employee");
        users.add(user);
        UserData.saveUsers(users);
        System.out.println("Tạo tài khoản nhân viên thành công.");
    }

    //Quan ly nhan vien
        //Them nhan vien moi
    public void insertEmployee() {
        Employee employee = inputEmployeeInfo();
        employees.add(employee);
        EmployeeData.saveEmployees(employees);
        System.out.println("Thêm nhân viên mới thành công");
    }

    public Employee inputEmployeeInfo() {
        System.out.print("Nhập tên nhân viên: ");
        String fullname = scanner.nextLine();
        System.out.print("Nhập tuổi nhân viên: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập lương nhân viên: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        return new Employee(fullname,age,salary);
    }

        //Chuc nang thay doi luong nhan vien
    public void modifySalary(String type){
        System.out.print("Nhập id nhân viên: ");
        String employeeId = scanner.nextLine();
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Không tìm thấy nhân viên có id:" + employeeId);
            return;
        }
        try {
            BigDecimal oldSalary = employee.getSalary(); // Luu luong cu
            if (type.equals("+")){
                increaseSalary(employee);
            } else if (type.equals("-")){
                decreaseSalary(employee);
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }
            //Luu lich su thay doi luong
            SalaryHistory history = new SalaryHistory(employeeId,oldSalary,employee.getSalary(),type);
            SalaryHistoryData.saveSalaryHistory(history);
            //Luu lai danh sach nhan vien sau khi thay doi
            EmployeeData.saveEmployees(employees);
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
        //Phuong thuc tang luong nhan vien
        private void increaseSalary(Employee employee){
            System.out.println("Nhập số tiền bạn muốn tăng: ");
            BigDecimal money = new BigDecimal(scanner.nextLine());
            if (money.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Số tiền lương tăng phải lớn hơn 0.");
            }
            BigDecimal newSalary = employee.getSalary().add(money);
            employee.setSalary(newSalary);
            System.out.println("Tăng lương thành công!");
        }
        //Phuong thuc giam luong nhan vien
        private void decreaseSalary(Employee employee) {
            System.out.println("Nhập số tiền muốn giảm: ");
            BigDecimal money = new BigDecimal(scanner.nextLine());

            if (money.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Số tiền giảm lương phải lớn hơn 0.");
            }

            if (employee.getSalary().compareTo(money) < 0) {
                throw new IllegalArgumentException("Số tiền giảm lương không được lớn hơn lương hiện tại.");
            }

            BigDecimal newSalary = employee.getSalary().subtract(money);
            employee.setSalary(newSalary);
            System.out.println("Giảm lương thành công! Lương mới: " + newSalary);
        }

        //Phuong thuc xem lich su thay doi luong
   public  void viewSalaryHistory() {
       if (salaryHistories.isEmpty()) {
           System.out.println("Không có lịch sử thay đổi lương.");
           return;
       }
       System.out.println("--------------------------Lịch sử thay đổi lương-----------------------------");
       System.out.println("+------------+------------+------------+---------------+---------------------+");
       System.out.println("| ID Nhân viên | Lương cũ  | Lương mới  | Loại thay đổi |  Thời gian         |");
       System.out.println("+------------+------------+------------+---------------+---------------------+");
       for (SalaryHistory salaryHistory : salaryHistories){
           System.out.printf( "| %-10s | %10s | %10s | %-13s | %19s |\n",
                   salaryHistory.getEmployeeId(),
                   salaryHistory.getOldSalary(),
                   salaryHistory.getNewSalary(),
                   salaryHistory.getChangeType().equals("+") ? "Tang" : "Giam",
                   salaryHistory.getChangeTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
       }
       System.out.println("+------------+------------+------------+---------------+---------------------+");
   }
    // Phuong thuc xem danh sach nhan vien
    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Khong co nhan vien nao trong danh sach.");
        }
        else {
            System.out.println("Danh sach nhan vien: ");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }


    //Phuong thuc xem danh sach tai khoan
    public void viewAllUsers(){
        if (users.isEmpty())  {
            System.out.println("Danh sách tài khoản trống.");
            return;
        }
        System.out.println("----------------------Danh sách tài khoản---------------------");
        System.out.printf("%-15s %-20s %-15s\n", "Username", "Email", "Role");
        System.out.println("--------------------------------------------------------------");
        for (User user : users){
            System.out.printf("%-15s %-20s %-15s\n",user.getUsername(),user.getEmail(),user.getRole());
        }
    }

    //Phuong thuc them sua xoa dich vu
    public void insertService() {
        Service newService = inputServiceInfo();
        services.add(newService);
        ServiceData.saveServices(services);
        System.out.println("Them dich vu thanh cong.");
    }

    public Service inputServiceInfo(){
        System.out.print("Nhap ten dich vu: ");
        String serviceName = scanner.nextLine();
        System.out.print("Nhap gia dich vu: ");
        BigDecimal servicePrice = new BigDecimal(scanner.nextLine());

        return new Service(serviceName,servicePrice);
    }

    public void updateService(){
        System.out.println("Nhap ma dich vu can sua: ");
        String serviceId = scanner.nextLine();
        Service service = findServiceById(serviceId);
        if (service != null) {
            System.out.println("Nhap ten dich vu moi: ");
            String newServiceName = scanner.nextLine();
            System.out.println("Nhap gia dich vu moi: ");
            BigDecimal newServicePrice = new BigDecimal(scanner.nextLine());
            service.setServiceName(newServiceName);
            service.setServicePrice(newServicePrice);
            ServiceData.saveServices(services);
            System.out.println("Cap nhat dich vu thanh cong.");
        }
        else {
            System.out.println("Dich vu khong ton tai.");
        }
    }

    public void deleteService(){
        System.out.println("Nhap ma dich vu can xoa: ");
        String serviceId = scanner.nextLine();
        Service service = findServiceById(serviceId);
        if (service != null){
            services.remove(service);
            ServiceData.saveServices(services);
            System.out.println("Xoa dich vu thanh cong.");
        }
        else {
            System.out.println("Dich vu khong ton tai.");
        }
    }
    // Xem danh sach dich vu
    public void viewListService() {
        if (services.isEmpty()) {
            System.out.println("Danh sách dịch vụ trống");
            return;
        }
        System.out.println("----------------------Danh sách dịch vụ---------------------");
        System.out.printf("%-15s %-20s %-15s\n", "ID", "Tên dịch vụ", "Giá dịch vụ");
        System.out.println("--------------------------------------------------------------");
        for (Service service : services){
            System.out.printf("%-15s %-20s %-15s\n",service.getServiceId(),service.getServiceName(),service.getServicePrice());
        }
    }


    //Them lich lam viec nhan vien
    public void inputWorkScheduleInfo() {
        System.out.println("Nhập thông tin lịch làm việc");
        System.out.println("Nhập ID nhân viên: ");
        String employeeId = scanner.nextLine();
        System.out.println("Ngày làm việc (yyyy-MM-dd): ");
        LocalDate workDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Giờ bắt đầu(HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());
        System.out.println("Giờ kết thúc(HH:mm): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());
        System.out.println("Ghi chú: ");
        String note = scanner.nextLine();
        WorkSchedule schedule = new WorkSchedule(employeeId,workDate,startTime,endTime,note);
        WorkScheduleData.saveWorkSchedule(schedule);
        System.out.println("Thêm lịch làm việc thành công");
    }
     //Xem lich lam viec
    public void viewWorkSchdule(){
        if (workSchedules.isEmpty()){
            System.out.println("Không có lịch làm việc nào.");
            return;
        }
        System.out.println("Lịch làm việc của nhân viên:");
        System.out.println("+--------------+---------------+-------------+--------------+---------------------+");
        System.out.println("| ID Nhân viên | Ngày làm việc | Giờ bắt đầu | Giờ kết thúc |  Ghi chú            |");
        System.out.println("+--------------+---------------+-------------+--------------+---------------------+");
        for (WorkSchedule schedule : workSchedules){
            System.out.printf(
                    "| %-12s | %-13s | %-11s | %-12s | %-19s |\n",
                    schedule.getEmployeeId(),
                    schedule.getWordkDate(),
                    schedule.getStartTime(),
                    schedule.getEndTime(),
                    schedule.getNote()
            );
        }
        System.out.println("+--------------+---------------+-------------+--------------+---------------------+");
    }







    private Service findServiceById(String serviceId) {
        for (Service service : services) {
            if (service.getServiceId().equals(serviceId)){
                return service;
            }
        }
        return null;
    }

    private Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees){
            if (employee.getEmployeeId().equals(employeeId)){
                return employee;
            }
        }
        return null;
    }


}
