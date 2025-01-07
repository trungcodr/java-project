package entities;

import java.math.BigDecimal;

public class Employee {
    private static int autoId;
    private String employeeId;
    private String fullname;
    private int age;
    private BigDecimal salary;

    public Employee( String fullname, int age, BigDecimal salary) {
        this.employeeId = "E" + ++autoId;
        this.fullname = fullname;
        this.age = age;
        this.salary = salary;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Employee.autoId = autoId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return employeeId + "," + fullname + "," + age + "," +  salary;
    }


    //Hien thi thong tin cua nhan vien khong bao gom luong va tuoi
    public String toSimpleString() {
        return employeeId + "," + fullname;
    }
    //Chuyen doi chuoi thanh doi tuong employee
    public static Employee fromString(String employeeString) {
        String[] parts = employeeString.split(",");
        if (parts.length !=  4){
            throw new IllegalArgumentException("Invalid employee string: " + employeeString);
        }
        String fullName = parts[1];
        int age = Integer.parseInt(parts[2]);
        BigDecimal salary = new BigDecimal(parts[3]);
        return new Employee(fullName,age,salary);
    }
}
