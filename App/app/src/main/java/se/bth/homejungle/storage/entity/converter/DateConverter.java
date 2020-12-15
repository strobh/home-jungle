package se.bth.homejungle.storage.entity.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This DateConverter is used by the Android Room Persistence Library in order
 * to convert objects of type LocalDate to a String that can be stored in the SQLite database.
 */
public class DateConverter {

    /**
     * Formatter to store the date in a standardized ISO format that is understood by the database.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Converts the String representation of a date in the database back to a LocalDate object.
     *
     * @param dateString String representation of a date in the database.
     * @return LocalDate object for the given date.
     */
    @TypeConverter
    public static LocalDate toLocalDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return formatter.parse(dateString, LocalDate::from);
        }
    }

    /**
     * Converts a LocalDate object to its String representation in the database.
     *
     * @param date LocalDate object to convert.
     * @return String representation of the given date for the database.
     */
    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return date.format(formatter);
        }
    }
}
