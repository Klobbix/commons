package com.klobbix.lang;

public class StringUtil {

    /**
     * Reverses a string, i.e. "string" becomes "gnirts".
     *
     * @param input The string to reverse
     * @return The reversed string
     */
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Trims whitespace off of the left side of the string.
     * @param input The string to trim
     * @return The trimmed string without any leading whitespace
     */
    public static String trimLeft(String input) {
        char[] value = input.toCharArray();
        int len = value.length;
        int st = 0;

        while ((st < len) && (value[st] <= ' ')) {
            st++;
        }
        return (st > 0) ? input.substring(st, len) : input;
    }

    /**
     * Trims whitespace off of the right side of the string.
     * @param input The string to trim
     * @return The trimmed string without any trailing whitespace
     */
    public static String trimRight(String input) {
        char[] value = input.toCharArray();
        int len = value.length;
        int st = 0;

        while ((st < len) && (value[len - 1] <= ' ')) {
            len--;
        }
        return (len < value.length) ? input.substring(st, len) : input;
    }

    /**
     * Removes all whitespace in a string.
     *
     * @param input The string to remove whitespace from
     * @return The string without any whitespace
     */
    public static String removeWhitespace(String input) {
        return input.replaceAll(" ", "");
    }

    /**
     * Checks if a given input is numeric.
     *
     * @param input The string to check
     * @return True if each character is a digit, otherwise false
     */
    public static boolean isNumeric(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Spells out a number in text, i.e. 123 becomes One Hundred Twenty-Three.
     * This input can only be numbers and must be greater than 0.
     * The largest length of an acceptable input string is 15 (hundred trillion).
     *
     * @param input The number as a string
     * @return The outputted number in text
     */
    public static String spellNumber(String input) {
        if (input.length() <= 0 || !isNumeric(input)) {
            return input;
        }
        long num = Long.parseLong(input);
        if (input.length() == 1) {
            return ones(input);
        } else if (input.length() == 2) {
            if (num < 20 || num % 10 == 0)
                return tens(input);
            return tens(input) + "-" + spellNumber(input.substring(1));
        } else if (input.length() == 3 || input.length() == 4 || input.length() == 6 || input.length() == 7
                || input.length() == 9 || input.length() == 10 || input.length() == 12 || input.length() == 13 || input.length() == 15) {
            String ones = ones(String.valueOf(input.charAt(0)));
            if (ones.equals(""))
                return spellNumber(input.substring(1));
            return ones + getMagnitudeName(input.length()) + spellNumber(input.substring(1));
        } else if (input.length() == 5 || input.length() == 8 || input.length() == 11 || input.length() == 14) {
            String tens = tens(input.substring(0, 2));
            String ones = ones(String.valueOf(input.charAt(1)));
            String combine = ones.equals("") ? tens : tens + "-" + ones;
            if (combine.equals("")) {
                return spellNumber(input.substring(2));
            }
            return combine + getMagnitudeName(input.length()) + spellNumber(input.substring(2));
        } else {
            return input;
        }
    }

    private static String getMagnitudeName(int length) {
        switch (length) {
            case 3:
            case 6:
            case 9:
            case 12:
            case 15:
                return " Hundred ";
            case 4:
            case 5:
                return " Thousand ";
            case 7:
            case 8:
                return " Million ";
            case 10:
            case 11:
                return " Billion ";
            case 13:
            case 14:
                return " Trillion ";
            default:
                return "";
        }
    }

    private static String ones(String digit) {
        switch (digit) {
            case "1":
                return "One";
            case "2":
                return "Two";
            case "3":
                return "Three";
            case "4":
                return "Four";
            case "5":
                return "Five";
            case "6":
                return "Six";
            case "7":
                return "Seven";
            case "8":
                return "Eight";
            case "9":
                return "Nine";
            default:
                return "";
        }
    }

    private static String tens(String digits) {
        if (digits.charAt(0) == '1' && digits.charAt(1) != '0') {
            return teens(digits.charAt(1));
        }
        switch (digits.charAt(0)) {
            case '1':
                return "Ten";
            case '2':
                return "Twenty";
            case '3':
                return "Thirty";
            case '4':
                return "Forty";
            case '5':
                return "Fifty";
            case '6':
                return "Sixty";
            case '7':
                return "Seventy";
            case '8':
                return "Eighty";
            case '9':
                return "Ninety";
            default:
                return ones(String.valueOf(digits.charAt(1)));
        }
    }

    private static String teens(char digit) {
        switch (digit) {
            case '1':
                return "Eleven";
            case '2':
                return "Twelve";
            case '3':
                return "Thirteen";
            case '4':
                return "Fourteen";
            case '5':
                return "Fifteen";
            case '6':
                return "Sixteen";
            case '7':
                return "Seventeen";
            case '8':
                return "Eighteen";
            case '9':
                return "Nineteen";
            default:
                return "";
        }
    }
}
