package data;

import entities.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServiceData {
    private static final String FILE_NAME = "services.txt";

    public static List<Service> loadServices() {
        List<Service> services = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Service service = new Service(parts[1],new BigDecimal(parts[2]) );
                services.add(service);
            }
        } catch (IOException e) {
            System.out.println("Khong the doc file dich vu.");
        }
        return services;
    }

    public static void saveServices(List<Service> services){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Service service : services){
                bw.write(service.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Khong the ghi file dich vu.");
        }
    }
}
