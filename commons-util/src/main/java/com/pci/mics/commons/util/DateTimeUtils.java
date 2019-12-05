package com.pci.mics.commons.util;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.pci.mics.commons.contants.DateTimePattern;
import org.apache.commons.lang3.StringUtils;

import static com.pci.mics.commons.contants.DateTimePattern.LONG_DATETIME_PATTERN;
import static com.pci.mics.commons.contants.DateTimePattern.LONG_DATE_PATTERN;
import static com.pci.mics.commons.contants.DateTimePattern.SHORT_DATETIME_PATTERN;
import static com.pci.mics.commons.contants.DateTimePattern.SHORT_DATETIME_SECOND_PATTERN;
import static com.pci.mics.commons.contants.DateTimePattern.SHORT_DATE_PATTERN;


public class DateTimeUtils {
    /**
     * 判断是否为5分钟的余数
     */
    private static final Integer FIVE_MINUTE = 5;
    /**
     * 判断是否为15分钟的余数
     */
    private static final Integer QUARTER_MINUTE = 15;
    /**
     * 判断是否为30分钟的余数
     */
    private static final Integer HALF_MINUTE = 30;
    /**
     * 年模式长度
     */
    private static final Integer YYYY_LENGTH = 4;
    /**
     * 默认每月第一天
     */
    private static final Integer DEFAULT_DAY_OF_MONTH = 1;

    private final static ZoneId zoneId = ZoneId.systemDefault();

    private DateTimeUtils() {
    }


    /**
     * 返回当前日期 ，默认"yyyy-MM-dd"模式
     *
     * @return String型日期
     */
    public static String getCurLocalDateStr() {
        return getCurLocalDateStr(LONG_DATE_PATTERN);
    }

    /**
     * 返回当前日期，自定义模式
     *
     * @param pattern 模式
     * @return String型日期
     */
    public static String getCurLocalDateStr(String pattern) {
        return toString(LocalDate.now(), pattern);
    }

    /**
     * 日期转String，默认"yyyy-MM-dd"模式
     *
     * @param localDate 日期
     * @return String型日期
     */
    public static String toString(LocalDate localDate) {
        return toString(localDate, DateTimePattern.LONG_DATE_PATTERN);
    }

    /**
     * 日期转String，自定义日期&模式
     *
     * @param localDate 日期
     * @param pattern   模式
     * @return String型日期
     */
    public static String toString(LocalDate localDate, String pattern) {
        return toString(localDate, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期转String，自定义日期&格式器
     *
     * @param localDate 日期
     * @param formatter 格式器
     * @return String型日期
     */
    public static String toString(LocalDate localDate, DateTimeFormatter formatter) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate must not be null");
        }
        return localDate.format(formatter);
    }



    /**
     * 返回当前日期时间 ，默认"yyyy-MM-dd HH:mm:ss.SSS"
     *
     * @return String型日期时间
     */
    public static String getCurLocalDateTimeStr() {
        return getCurLocalDateTimeStr(LONG_DATETIME_PATTERN);
    }

    /**
     * 以指定模式返回当前日期时间
     *
     * @param pattern 模式
     * @return String型日期时间
     */
    public static String getCurLocalDateTimeStr(String pattern) {
        return toString(LocalDateTime.now(), pattern);
    }

    /**
     * 日期时间转String,默认"yyyy-MM-dd HH:mm:ss.SSS"模式
     *
     * @param localDateTime 日期时间
     * @return String型日期时间
     */
    public static String toString(LocalDateTime localDateTime) {
        return toString(localDateTime, LONG_DATETIME_PATTERN);
    }

    /**
     * 日期时间转String,自定义日期时间&模式
     *
     * @param dateTime LocalDateTime
     * @param pattern  模式
     * @return String
     */
    public static String toString(LocalDateTime dateTime, String pattern) {
        return toString(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间转String，自定义日期时间&格式器
     *
     * @param dateTime  日期时间
     * @param formatter 格式器
     * @return String型日期时间
     */
    public static String toString(LocalDateTime dateTime, DateTimeFormatter formatter) {
        if (dateTime == null) {
            throw new IllegalArgumentException("localDateTime must not be null");
        }
        if (formatter == null) {
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        }

        return dateTime.format(formatter);
    }

    /**
     * String转日期，默认"yyyy-MM-dd"模式
     *
     * @param dateStr 日期字符串
     */
    public static LocalDate toLocalDate(String dateStr) {
        return toLocalDate(dateStr, LONG_DATE_PATTERN);
    }

    /**
     * String转日期，自定义日期&模式
     *
     * @param dateStr 日期字符串
     * @param pattern 模式
     */
    public static LocalDate toLocalDate(String dateStr, String pattern) {
        return toLocalDate(dateStr, new DateTimeFormatterBuilder().appendPattern(pattern)
                                                                  .parseDefaulting(ChronoField.DAY_OF_MONTH, DEFAULT_DAY_OF_MONTH)
                                                                  .toFormatter());
    }

    /**
     * String转日期，自定义日期&格式器
     *
     * @param dateStr   日期字符串
     * @param formatter 格式器
     */
    public static LocalDate toLocalDate(String dateStr, DateTimeFormatter formatter) {
        if (StringUtils.isEmpty(dateStr)) {
            throw new IllegalArgumentException("dateStr must not be empty");
        }
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * String转日期时间，默认"yyyy-MM-dd HH:mm:ss.SSS"模式
     *
     * @param dateStr 日期时间字符串
     */
    public static LocalDateTime toLocalDateTime(String dateStr) {
        return toLocalDateTime(dateStr, LONG_DATETIME_PATTERN);
    }

    /**
     * String转日期时间，自定义日期时间&模式
     *
     * @param dateStr 日期时间字符串
     * @param pattern 模式
     */
    public static LocalDateTime toLocalDateTime(String dateStr, String pattern) {
        if (pattern.equals(SHORT_DATETIME_PATTERN)) {
            pattern = LONG_DATETIME_PATTERN;
            dateStr = shortPatternToLongPattern(dateStr);
        }
        return toLocalDateTime(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * string转日期时间，自定义日期时间&格式器
     *
     * @param dateStr   string 日期时间
     * @param formatter 格式器
     */
    public static LocalDateTime toLocalDateTime(String dateStr, DateTimeFormatter formatter) {
        if (StringUtils.isEmpty(dateStr)) {
            throw new IllegalArgumentException("dateStr must not be empty");
        }
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 将Short_..._Pattern字符串转为Long_..._Pattern
     * 将yyyyMMddHHmmssSSS模式String转为yyyy-MM-dd HH:mm:ss.SSS,避免解析时遇到JDK8 BUG
     */
    public static String shortPatternToLongPattern(String dateStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dateStr.length(); i++) {
            sb.append(dateStr.charAt(i));
            if ((i & 1) == 1&&i!=dateStr.length() -1) {
                if (i >= YYYY_LENGTH - 1 && i < SHORT_DATE_PATTERN.length() - 1) {
                    sb.append("-");
                } else if (i == SHORT_DATE_PATTERN.length() - 1) {
                    sb.append(" ");
                } else if (i > SHORT_DATE_PATTERN.length() - 1 && i < SHORT_DATETIME_SECOND_PATTERN.length() - 1) {
                    sb.append(":");
                } else if (i == SHORT_DATETIME_SECOND_PATTERN.length() - 1) {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }

    /**
     * LocalDateTime转date
     *
     * @param localDateTime 日期时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * date转LocalDateTime
     *
     * @param date 日期
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    /**
     * localDateTime转Timestamp
     *
     * @param localDateTime 日期时间
     */
    public static Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Timestamp转localDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    /**
     * 日期相隔毫秒
     */
    public static long periodMillis(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime).toMillis();
    }

    /**
     * 日期相隔秒
     */
    public static long periodSeconds(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 日期相隔天数
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 日期相隔周数
     */
    public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 日期相隔月数
     */
    public static long periodMonths(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.MONTHS);
    }

    /**
     * 日期相隔年数
     */
    public static long periodYears(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.YEARS);
    }

    /**
     * 是否为当天
     */
    public static boolean isToday(LocalDate date) {
        return LocalDate.now().equals(date);
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 是否同一天
     */
    public static boolean isSameDay(LocalDateTime t1, LocalDateTime t2) {
        if (t1 == null || t2 == null) {
            return false;
        }
        return isSameDay(t1.toLocalDate(), t2.toLocalDate());
    }

    /**
     * 是否同一天
     */
    public static boolean isSameDay(LocalDate d1, LocalDate d2) {
        if (d1 == null || d2 == null) {
            return false;
        }
        return d1.equals(d2);
    }

    /**
     * 是否为5分钟
     */
    public static boolean isFiveMinute(LocalDateTime time) {
        return (time.getMinute() % FIVE_MINUTE) == 0;
    }

    /**
     * 是否为一刻钟
     */
    public static boolean isQuarter(LocalDateTime time) {
        return (time.getMinute() % QUARTER_MINUTE) == 0;
    }

    /**
     * 是否为半个小时
     */
    public static boolean isHalfHour(LocalDateTime time) {
        return (time.getMinute() % HALF_MINUTE) == 0;
    }

    /**
     * 是否为整点
     */
    public static boolean isFullClock(LocalDateTime time) {
        return time.getMinute() == 0;
    }
}
