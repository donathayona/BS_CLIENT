package assignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelperFunctions {
    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }
}
