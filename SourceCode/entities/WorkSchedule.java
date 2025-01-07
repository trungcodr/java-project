package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkSchedule {
    private String employeeId;
    private LocalDate wordkDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String note;

    public WorkSchedule(String employeeId, LocalDate wordkDate, LocalTime startTime, LocalTime endTime, String note) {
        this.employeeId = employeeId;
        this.wordkDate = wordkDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWordkDate() {
        return wordkDate;
    }

    public void setWordkDate(LocalDate wordkDate) {
        this.wordkDate = wordkDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return employeeId + "," + wordkDate + "," + startTime + "," + endTime + "," + note;
    }

    //Chuyen doi chuoi thanh doi tuong WorkSchedule
    public static WorkSchedule fromString(String scheduleString){
        String parts[] = scheduleString.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid schedule string: " + scheduleString);
        }
        String employeeId = parts[0];
        LocalDate workDate = LocalDate.parse(parts[1]);
        LocalTime startTime = LocalTime.parse(parts[2]);
        LocalTime endTime = LocalTime.parse(parts[3]);
        String note = parts[4];
        return new WorkSchedule(employeeId,workDate,startTime,endTime,note);
    }
}
