package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private static int autoId;
    private String bookingId;
    private String customerId;
    private String serviceId;
    private LocalDateTime bookingDateTime;
    private BookingStatus status;

    public Booking( String customerId, String serviceId) {
        this.bookingId = "B" + ++autoId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.bookingDateTime = LocalDateTime.now();
        this.status = BookingStatus.BOOKED;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return bookingId + "," + customerId + "," + serviceId + "," + bookingDateTime.format(formatter) + "," + status;
    }

//    public static Booking fromString(String bookingString) {
//        String[]parts = bookingString.split(",");
//        if (parts.length != 5) {
//            throw new IllegalArgumentException("Invalid booking string: " + bookingString);
//        }
//        Booking booking = new Booking(parts[1], parts[2]);
//        booking.bookingId = parts[0];
//        booking.bookingDateTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        booking.status = BookingStatus.valueOf(parts[4]);
//        return booking;
//    }

}
