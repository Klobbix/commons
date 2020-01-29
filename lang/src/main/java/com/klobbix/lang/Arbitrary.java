package com.klobbix.lang;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return alphaNumericString(1, 30);
    }

    public static String alphaNumericString(int minLength) {
        return alphaNumericString(minLength, 30);
    }

    /**
     * Returns an alphabetic string consisting of numbers and both upper and lower case letters.
     *
     * @param maxLength The maximum size of the resulting string
     * @return The generated string
     */
    public static String alphaNumericString(int minLength, int maxLength) {
        return generateStringFromPool(minLength, maxLength, StringUtil.ALPHA_NUMERIC);
    }

    /**
     * Generates a String of min and max length from a pool of letters in a String.
     *
     * @param min  The minimum resulting String length
     * @param max  The maximum resulting String length
     * @param pool The pool of characters to use
     * @return A randomly generated String
     */
    public static String generateStringFromPool(int min, int max, String pool) {
        int amount = intBetween(min, max);
        StringBuilder builder = new StringBuilder(amount);
        for (int i = 0; i < amount; i++) {
            int letter = intBetween(0, pool.length() - 1);
            builder.append(pool.charAt(letter));
        }
        return builder.toString();
    }

    /**
     * Returns an alphabetic String consisting of both upper and lower case letters.
     *
     * @return The generated String
     */
    public static String string() {
        return string(1, 30);
    }

    /**
     * Returns an alphabetic String consisting of both upper and lower case letters.
     *
     * @return The generated String
     */
    public static String string(int minLength) {
        return string(minLength, 30);
    }

    /**
     * Returns an alphabetic String consisting of both upper and lower case letters.
     *
     * @param minLength The minimum size of the resulting String
     * @param maxLength The maximum size of the resulting String
     * @return The generated string
     */
    public static String string(int minLength, int maxLength) {
        return generateStringFromPool(minLength, maxLength, StringUtil.ALPHA);
    }

    /**
     * Returns an numeric string consisting of only the numbers 0-9.
     *
     * @return The generated string
     */
    public static String numericString() {
        return numericString(1, 30);
    }

    /**
     * Returns an numeric string consisting of only the numbers 0-9.
     *
     * @param minLength The minimum length of the string
     * @return The generated string
     */
    public static String numericString(int minLength) {
        return numericString(minLength, 30);
    }

    /**
     * Returns an numeric string consisting of only the numbers 0-9.
     *
     * @param maxLength The maximum size of the resulting string
     * @return The generated string
     */
    public static String numericString(int minLength, int maxLength) {
        return generateStringFromPool(minLength, maxLength, StringUtil.NUMERIC);
    }

    public static int intBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return random.nextInt((max - min) + 1) + min;
    }

    public static long longBetween(long min, long max) {
        if (min > max) {
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
        long epochMilli = longBetween(startMillis, endMillis);
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneId.of("UTC")).toLocalDate();
    }

    public static Date date() {
        return Date.from(localDate().atStartOfDay(ZoneId.of("UTC")).toInstant());
    }

    public static <T> List<T> manyOf(Supplier<T> supplier, int numberOfValues) {
        return IntStream.range(0, numberOfValues)
                .mapToObj(n -> supplier.get())
                .collect(Collectors.toList());
    }

    public static <T> T oneOf(List<T> values) {
        return values.get(intBetween(0, values.size() - 1));
    }
}
