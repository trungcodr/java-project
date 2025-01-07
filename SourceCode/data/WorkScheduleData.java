package data;

import entities.WorkSchedule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entities.WorkSchedule.fromString;

public class WorkScheduleData {
    private static final String SCHEDULE_FILE_NAME = "work_schedule.txt";

    //Doc lich lam viec vao file

    public static List<WorkSchedule> loadWorkSchedules() {
        List<WorkSchedule> schedules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SCHEDULE_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                schedules.add(fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file lịch làm việc." + e.getMessage());
        }
        return schedules;
    }

    //Ghi lich lam viec vao file
    public static void saveWorkSchedule(WorkSchedule schedule) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCHEDULE_FILE_NAME, true))) {
            bw.write(schedule.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Không thể ghi file lịch làm việc: " + e.getMessage());
        }
    }
}
