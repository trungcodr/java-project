package data;

import entities.SalaryHistory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entities.SalaryHistory.fromString;

public class SalaryHistoryData {
    private static final String HISTORY_FILE_NAME = "salary_history.txt";

    public static List<SalaryHistory> loadSalaryHistory() {
        List<SalaryHistory> historyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                historyList.add(fromString(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return historyList;
    }


    public static void saveSalaryHistory(SalaryHistory history) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE_NAME, true))) {
            bw.write(history.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Không thể ghi file lịch sử lương: " + e.getMessage());
        }
    }


}
