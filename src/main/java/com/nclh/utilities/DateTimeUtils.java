package com.nclh.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date & time-related operations.
 */
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    /**
     * Returns the current date formatted as "M/d/yyyy".
     *
     * @return formatted current date string
     */
    public static String getDate() {
        return LocalDate.now().format(FORMATTER);
    }

    /**
     * Returns the date after adding the specified number of months.
     *
     * @param months number of months to add
     * @return formatted date string after adding months
     */
    public static String getDate(int months) {
        return LocalDate.now().plusMonths(months).format(FORMATTER);
    }

    /**
     * Returns the date after adding the specified number of days.
     *
     * @param days number of days to add
     * @return formatted date string after adding days
     */
    public static String getDateByDays(int days) {
        return LocalDate.now().plusDays(days).format(FORMATTER);
    }

    /**
     * Returns a formatted date string representing a date in the past
     * by subtracting the specified number of years from the current date.
     *
     * @param years the number of years to subtract from the current date
     * @return formatted date string based on the predefined FORMATTER
     */
    public static String getDateYearsAgo(int years) {
        return LocalDate.now().minusYears(years).format(FORMATTER);
    }
}
