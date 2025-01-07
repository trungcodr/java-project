package data;

import entities.Booking;
import entities.BookingStatus;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static entities.Booking.fromString;

public class BookingData {
    private static final String BOOKING_FILE_NAME = "bookings.txt";


    //Doc danh sach dat lich tu tep
    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc tệp đặt lịch: " + e.getMessage());
        }
        return bookings;
    }

    public  static void saveBooking(List<Booking> bookings){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKING_FILE_NAME))){
            for (Booking booking : bookings) {
                bw.write(booking.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi được tệp: " + e.getMessage());
        }
    }
}
