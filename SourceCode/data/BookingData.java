package data;

import entities.Booking;
import entities.BookingStatus;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class BookingData {
    private static final String BOOKING_FILE_NAME = "bookings.txt";


    //Doc danh sach dat lich tu tep
    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("Du lieu khong hop le: " + line);
                    continue;
                }
                String bookingId = parts[0];
                String customerId = parts[1];
                String serviceId = parts[2];
                LocalDateTime bookingDateTime = LocalDateTime.parse(parts[3],formatter);
                BookingStatus status = BookingStatus.valueOf(parts[4]);
                Booking booking = new Booking(customerId,serviceId);
                booking.setBookingId(bookingId);
                booking.setBookingDateTime(bookingDateTime);
                booking.setStatus(status);
                bookings.add(booking);
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
