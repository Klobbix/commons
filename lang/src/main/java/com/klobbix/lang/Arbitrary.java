package com.klobbix.lang;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;

public class Arbitrary {

    private static final Random random = new Random();

    public static int nextInt() {
        return random.nextInt();
    }

    public static long nextLong() {
        return random.nextLong();
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    /**
     * Returns an alphabetic string consisting of numbers and both upper and lower case letters.
     *
     * @return The generated string
     */
    public static String alphaNumericString() {
        return alphaNumericString(30);
    }

    /**
     * Returns an alphabetic string consisting of numbers and both upper and lower case letters.
     *
     * @param maxLength The maximum size of the resulting string
     * @return The generated string
     */
    public static String alphaNumericString(int maxLength) {
        int amount = intBetween(1, maxLength);
        String pool = StringUtil.ALPHA_NUMERIC;
        StringBuilder builder = new StringBuilder(amount);
        for (int i = 0; i < amount; i++) {
            int letter = intBetween(0, pool.length());
            builder.append(pool.charAt(letter));
        }
        return builder.toString();
    }

    /**
     * Returns an alphabetic string consisting of both upper and lower case letters.
     *
     * @return The generated string
     */
    public static String string() {
        return string(30);
    }

    /**
     * Returns an alphabetic string consisting of both upper and lower case letters.
     *
     * @param maxLength The maximum size of the resulting string
     * @return The generated string
     */
    public static String string(int maxLength) {
        int amount = intBetween(1, maxLength);
        String pool = StringUtil.ALPHA;
        StringBuilder builder = new StringBuilder(amount);
        for (int i = 0; i < amount; i++) {
            int letter = intBetween(0, pool.length());
            builder.append(pool.charAt(letter));
        }
        return builder.toString();
    }

    /**
     * Returns an numeric string consisting of only the numbers 0-9.
     *
     * @return The generated string
     */
    public static String numericString() {
        return numericString(30);
    }

    /**
     * Returns an numeric string consisting of only the numbers 0-9.
     *
     * @param maxLength The maximum size of the resulting string
     * @return The generated string
     */
    public static String numericString(int maxLength) {
        int amount = intBetween(1, maxLength);
        String pool = StringUtil.NUMERIC;
        StringBuilder builder = new StringBuilder(amount);
        for (int i = 0; i < amount; i++) {
            int letter = intBetween(0, pool.length());
            builder.append(pool.charAt(letter));
        }
        return builder.toString();
    }

    public static int intBetween(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return random.nextInt((max - min) + 1) + min;
    }

    public static long longBetween(long min, long max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return min + (random.nextLong() * (max - min));
    }

    public static LocalDate localDate() {
        return localDate(LocalDate.of(1980, 1, 1), LocalDate.now().plusYears(2));
    }

    public static LocalDate localDate(LocalDate start, LocalDate end) {
        long startMillis = start.atTime(LocalTime.NOON).toInstant(ZoneOffset.UTC).toEpochMilli();
        long endMillis = end.atTime(LocalTime.NOON).toInstant(ZoneOffset.UTC).toEpochMilli();

        Long epochMilli = longBetween(startMillis, endMillis);

        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.of("UTC")).toLocalDate();
    }

    public static Date date() {
        return Date.from(localDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
    }
}
