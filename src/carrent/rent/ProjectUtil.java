package carrent.rent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Lukasz on 18.10.2017.
 */
public class ProjectUtil {
    private final static String zoneId = "Europe/Warsaw";

    public static ZonedDateTime parseDateFromCalendar(String date){
        DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateFormatter).atStartOfDay(ZoneId.of(zoneId));

    }
}
