package entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalaryHistory {
    private String employeeId;
    private BigDecimal oldSalary;
    private BigDecimal newSalary;
    private String changeType;
    private LocalDateTime  changeTime;

    public SalaryHistory(String employeeId, BigDecimal oldSalary, BigDecimal newSalary, String changeType) {
        this.employeeId = employeeId;
        this.oldSalary = oldSalary;
        this.newSalary = newSalary;
        this.changeType = changeType;
        this.changeTime = LocalDateTime.now();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public BigDecimal getOldSalary() {
        return oldSalary;
    }

    public BigDecimal getNewSalary() {
        return newSalary;
    }

    public String getChangeType() {
        return changeType;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return employeeId + "," + oldSalary + "," + newSalary + "," + changeType + "," + changeTime.format(formatter);
    }

    // Chuyển đổi chuỗi thành đối tượng SalaryHistory
    public static SalaryHistory fromString(String historyString) {
        String[] parts = historyString.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid history string: " + historyString);
        }
        String employeeId = parts[0];
        BigDecimal oldSalary = new BigDecimal(parts[1]);
        BigDecimal newSalary = new BigDecimal(parts[2]);
        String changeType = parts[3];
        LocalDateTime changeTime = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        SalaryHistory history = new SalaryHistory(employeeId, oldSalary, newSalary, changeType);
        return history;
    }
}
