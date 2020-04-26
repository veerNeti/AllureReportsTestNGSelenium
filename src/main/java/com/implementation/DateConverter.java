package com.implementation;

import com.services.DateConverterService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter implements DateConverterService {
    private static Logger logger = LogManager.getLogger(DateConverter.class.getName());
    public static final String PATTERN_MM_DD_YYYY = "MM/dd/yyyy";
    public static final String PATTERN_MM_DD_YYYY_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String PATTERN_yyyyMMddhhmmss = "yyyyMMddhhmmss";

    @Override
    public String usingSimpleDateFormatyyyyMMddhhmmss() {
        logger.info("converting date to format:"+PATTERN_yyyyMMddhhmmss);
        return new SimpleDateFormat(PATTERN_yyyyMMddhhmmss).format(new Date());
    }

    @Override
    public String convertLocalDateToFormat(LocalDate localDate, String format) {
        logger.info("converting date to format:"+format);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    @Override
    public LocalDateTime convertStringDateTimeToLocalDateTime(String stringDateTime, String stringDateTimeFormat) {
        logger.info("converting date to format:"+stringDateTimeFormat);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(stringDateTimeFormat);
        return LocalDateTime.parse(stringDateTime, format);
    }

    @Override
    public String convertCurrentDateToTformat() {
        logger.info("converting date to format:"+PATTERN_MM_DD_YYYY_T_HHMMSS);
        SimpleDateFormat f = new SimpleDateFormat(PATTERN_MM_DD_YYYY_T_HHMMSS);
        return f.format(new Date());
    }

    @Override
    public String getCurrentUtcTime() throws ParseException {

        Instant instant = Instant.now();
        logger.info("getCurrentUtcTime:"+instant.toString());
        return instant.toString();
    }

    @Override
    public String convertLocalDateTimeToFormattedString(LocalDateTime localDateTime, String format) {

        if (localDateTime == null || StringUtils.isEmpty(format)) {
            logger.throwing(Level.ALL,new NullPointerException());
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        logger.info("convertLocalDateTimeToFormattedString:"+formatter);
        return localDateTime.format(formatter);
    }
}