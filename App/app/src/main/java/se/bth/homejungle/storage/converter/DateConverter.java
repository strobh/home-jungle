package se.bth.homejungle.storage.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @TypeConverter
    public static LocalDate toLocalDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return formatter.parse(dateString, LocalDate::from);
        }
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return date.format(formatter);
        }
    }
}
