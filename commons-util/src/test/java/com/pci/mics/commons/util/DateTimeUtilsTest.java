package com.pci.mics.commons.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.pci.mics.commons.contants.DateTimePattern;
import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilsTest {

    /**
     * 日期转String，
     */
    @Test
    public void testLocalDateToString() {
        LocalDate localDate = LocalDate.of(2019, 1, 1);
        System.out.println("--LocalDate转String--");
        Assert.assertTrue("2019-01-01".contentEquals(DateTimeUtils.toString(localDate)));
        Assert.assertTrue("2019/01/01".contentEquals(DateTimeUtils.toString(localDate, DateTimePattern.EN_DATE_PATTERN)));
        Assert.assertTrue("20190101".contentEquals(DateTimeUtils.toString(localDate, DateTimePattern.SHORT_DATE_PATTERN)));
        Assert.assertTrue("2019-01".contentEquals(DateTimeUtils.toString(localDate, DateTimePattern.LONG_DATE_MONTH_PATTERN)));
        Assert.assertTrue("2019/01".contentEquals(DateTimeUtils.toString(localDate, DateTimePattern.EN_DATE_MONTH_PATTERN)));
        Assert.assertTrue("201901".contentEquals(DateTimeUtils.toString(localDate, DateTimePattern.SHORT_DATE_MONTH_PATTERN)));
        System.out.println("-----获取当前日期-----");
        System.out.println("默认模式:    " + DateTimeUtils.getCurLocalDateStr());
        System.out.println("yyyy-MM-dd: " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.LONG_DATE_PATTERN));
        System.out.println("yyyy/MM/dd: " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.EN_DATE_PATTERN));
        System.out.println("yyyyMMdd:   " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.SHORT_DATE_PATTERN));
        System.out.println("yyyy-MM:    " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.LONG_DATE_MONTH_PATTERN));
        System.out.println("yyyy/MM:    " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.EN_DATE_MONTH_PATTERN));
        System.out.println("yyyyMM:     " + DateTimeUtils.getCurLocalDateStr(DateTimePattern.SHORT_DATE_MONTH_PATTERN));
    }

    /**
     * 日期时间转DateTime
     */
    @Test
    public void testLocalDateTimeToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0, 666 * 1000000);
        System.out.println("--LocalDateTime转String测试--");
        Assert.assertTrue("2019-01-01 00:00:00.666".contentEquals(DateTimeUtils.toString(localDateTime)));
        Assert.assertTrue("2019/01/01 00:00:00".contentEquals(DateTimeUtils.toString(localDateTime, DateTimePattern.EN_DATETIME_SECOND_PATTERN)));
        Assert.assertTrue("20190101000000".contentEquals(DateTimeUtils.toString(localDateTime, DateTimePattern.SHORT_DATETIME_SECOND_PATTERN)));
        Assert.assertTrue("2019-01-01 00:00:00.666".contentEquals(DateTimeUtils.toString(localDateTime, DateTimePattern.LONG_DATETIME_PATTERN)));
        Assert.assertTrue("2019/01/01 00:00:00.666".contentEquals(DateTimeUtils.toString(localDateTime, DateTimePattern.EN_DATETIME_PATTERN)));
        Assert.assertTrue("20190101000000666".contentEquals(DateTimeUtils.toString(localDateTime, DateTimePattern.SHORT_DATETIME_PATTERN)));
        System.out.println("-----获取当前日期时间测试-----");
        System.out.println("默认模式:                  " + DateTimeUtils.getCurLocalDateTimeStr());
        System.out.println("yyyy-MM-dd HH:mm:ss:      " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.LONG_DATETIME_SECOND_PATTERN));
        System.out.println("yyyy/MM/dd HH:mm:ss:      " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.EN_DATETIME_SECOND_PATTERN));
        System.out.println("yyyyMMdd:                 " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.SHORT_DATE_PATTERN));
        System.out.println("yyyy-MM-dd HH:mm:ss.SSS:  " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.LONG_DATETIME_PATTERN));
        System.out.println("yyyy/MM/dd HH:mm:ss.SSS:  " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.EN_DATETIME_PATTERN));
        System.out.println("yyyyMMddHHmmssSSS:        " + DateTimeUtils.getCurLocalDateTimeStr(DateTimePattern.SHORT_DATETIME_PATTERN));
    }

    /**
     * String转日期
     */
    @Test
    public void testStringToLocalDate() {
        Assert.assertEquals(LocalDate.of(2019, 1, 2), DateTimeUtils.toLocalDate("2019-01-02"));
        Assert.assertEquals(LocalDate.of(2019, 1, 2), DateTimeUtils.toLocalDate("2019/01/02", DateTimePattern.EN_DATE_PATTERN));
        Assert.assertEquals(LocalDate.of(2019, 1, 1), DateTimeUtils.toLocalDate("201901", DateTimePattern.SHORT_DATE_MONTH_PATTERN));
    }

    /**
     * String转日期时间
     */
    @Test
    public void testStringToLocalDateTime() {
        Assert.assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), DateTimeUtils.toLocalDateTime("2019-01-01 00:00:00.000", DateTimePattern.LONG_DATETIME_PATTERN));
        Assert.assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), DateTimeUtils.toLocalDateTime("2019/01/01 00:00:00.000", DateTimePattern.EN_DATETIME_PATTERN));
        Assert.assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0,12), DateTimeUtils.toLocalDateTime("20190101000012000", DateTimePattern.SHORT_DATETIME_PATTERN));
    }

    @Test
    public void testShortToLong() {
        String shortStr = "20190100225623444";
        System.out.println(DateTimeUtils.shortPatternToLongPattern(shortStr));
    }
    /**
     * LocalDate、Date互转
     */
    @Test
    public void testLocalDateTimeExchangeDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0, 123 * 1000000);
        Date date = DateTimeUtils.localDateTimeToDate(localDateTime);
        System.out.println("localDateTime: " + localDateTime);
        System.out.println("date:          " + date);
        LocalDateTime localDateTime1 = DateTimeUtils.dateToLocalDateTime(date);
        Assert.assertEquals(localDateTime, localDateTime1);

        System.out.println(LocalDateTime.now());
    }

    /**
     * LocalDateTime、Timestamp互转
     */
    @Test
    public void testLocalDateTimeExchangeTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0, 123 * 1000000);
        Timestamp timestamp = DateTimeUtils.localDateTimeToTimestamp(localDateTime);
        System.out.println("localDateTime: " + localDateTime);
        System.out.println("timestamp:     " + timestamp);
        LocalDateTime localDateTime1 = DateTimeUtils.timestampToLocalDateTime(timestamp);
        Assert.assertEquals(localDateTime, localDateTime1);
    }

    /**
     * 两LocalDateTime对象秒和毫秒的差值
     */
    @Test
    public void testPeriodLocalDateTime() {
        LocalDateTime dt1 = LocalDateTime.of(2019, 11, 6, 17, 45, 0, 100 * 1000000);
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 6, 17, 45, 0, 200 * 1000000);
        System.out.println("相差 " + DateTimeUtils.periodMillis(dt1, dt2) + "ms");

        LocalDateTime dt3 = LocalDateTime.of(2019, 11, 6, 17, 45, 0, 0);
        LocalDateTime dt4 = LocalDateTime.of(2019, 11, 6, 17, 45, 20, 0);
        System.out.println("相差 " + DateTimeUtils.periodSeconds(dt3, dt4) + "s");
    }

    @Test
    public void testPeriodLocalDate() {
        LocalDate d1 = LocalDate.of(2019, 11, 6);
        LocalDate d2 = LocalDate.of(2019, 11, 7);
        System.out.println("相差 " + DateTimeUtils.periodDays(d1, d2) + "d");

        LocalDate d3 = LocalDate.of(2019, 11, 6);
        LocalDate d4 = LocalDate.of(2019, 12, 7);
        System.out.println("相差 " + DateTimeUtils.periodMonths(d3, d4) + "M");

        LocalDate d5 = LocalDate.of(2019, 11, 6);
        LocalDate d6 = LocalDate.of(2020, 11, 6);
        System.out.println("相差 " + DateTimeUtils.periodYears(d5, d6) + "y");

        LocalDate d7 = LocalDate.of(2019, 11, 6);
        LocalDate d8 = LocalDate.of(2019, 12, 13);
        System.out.println("相差 " + DateTimeUtils.periodWeeks(d7, d8) + "w");
    }

    /**
     * 当天、闰年、同一天判断
     */
    @Test
    public void testIs() {
        //isToday
        Assert.assertTrue(DateTimeUtils.isToday(LocalDate.now()));
        Assert.assertFalse(DateTimeUtils.isToday(LocalDate.of(2019, 10, 1)));

        //isLeapYear闰年
        Assert.assertTrue(DateTimeUtils.isLeapYear(LocalDate.of(2020, 1, 1)));
        Assert.assertFalse(DateTimeUtils.isLeapYear(LocalDate.now()));

        //isSameDay同一天
        Assert.assertTrue(DateTimeUtils.isSameDay(LocalDateTime.of(2019, 11, 7, 0, 0, 0), LocalDateTime.of(2019, 11, 7, 23, 59, 59)));
        Assert.assertFalse(DateTimeUtils.isSameDay(LocalDateTime.of(2019, 11, 6, 0, 0, 0), LocalDateTime.now()));
    }

    /**
     * 是否为5、15、30、60分钟的倍数
     */
    @Test
    public void isTime() {
        LocalDateTime dt1 = LocalDateTime.of(2019, 11, 7, 9, 59);
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 7, 9, 45);
        LocalDateTime dt3 = LocalDateTime.of(2019, 11, 7, 9, 0);

        Assert.assertTrue(DateTimeUtils.isFiveMinute(dt2));
        Assert.assertFalse(DateTimeUtils.isFiveMinute(dt1));

        Assert.assertTrue(DateTimeUtils.isQuarter(dt2));
        Assert.assertFalse(DateTimeUtils.isQuarter(dt1));

        Assert.assertTrue(DateTimeUtils.isHalfHour(dt3));
        Assert.assertFalse(DateTimeUtils.isHalfHour(dt1));

        Assert.assertTrue(DateTimeUtils.isFullClock(dt3));
        Assert.assertFalse(DateTimeUtils.isFullClock(dt1));
    }

}
