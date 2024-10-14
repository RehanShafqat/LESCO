package Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author rehan
 */
public class Date {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Method to get today's date in the specified format
    public static String todaysDate() {
        return LocalDate.now().format(formatter);
    }

    // Method to get a date one year from now
    public static String dateYearLater() {
        LocalDate oneYearLater = LocalDate.now().plus(1, ChronoUnit.YEARS);
        return oneYearLater.format(formatter);
    }

    // Method to get a date one week from now
    public static String dateWeekLater() {
        LocalDate oneWeekLater = LocalDate.now().plus(1, ChronoUnit.WEEKS);
        return oneWeekLater.format(formatter);
    }

    // Method to check CNIC expiry status
    public static int checkCNICExpiry(String expiryDate) {
        LocalDate expiration = LocalDate.parse(expiryDate, formatter);
        LocalDate current = LocalDate.now();

        if (current.isAfter(expiration)) {
            return -1; // CNIC expired
        }

        int remainingDays = (int) ChronoUnit.DAYS.between(current, expiration);
        return remainingDays <= 30 ? 1 : 0;
    }
}
