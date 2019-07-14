package org.firefly.provider.springboot.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    private DateTimeUtil() {
    }

    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMATTER);
    }
}
