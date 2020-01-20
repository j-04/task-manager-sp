package ru.dragosh.tm.util;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public final static String DEFAULT_DATE = "2001-01-01";
    private final static String DATE_FORMAT = "yyyy-mm-dd";

    public static String formatDate(@Nullable final Date date) {
        if (date == null) return "Дата не установлена";
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDataFormat.format(date);
    }
}
