package DatabaseLogics;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateManipulation {
    public static Date today() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateTime.format(dateTimeFormatter);
        return Date.valueOf(
                Date.valueOf(formattedDate)
                        .toLocalDate()
        );
    }

    private static int getYear(Date date) {
        String stringDate = date.toString();
        return Integer.parseInt(stringDate.substring(0, stringDate.indexOf('-')));
    }

    public static boolean isSafeYear(Date expDate) {
        return getYear(expDate) - getYear(today()) >= 4;
    }

    public static Date getDateAfterYears(int years) {
        return Date.valueOf(today().toLocalDate().plusYears(years));
    }
}
