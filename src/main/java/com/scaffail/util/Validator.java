package com.scaffail.util;

public class Validator {

    public static boolean isValidInt(String input) {

        if (input == null || input.isEmpty()) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (i == 0 && input.charAt(i) == '-') {
                if (input.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(input.charAt(i), 10) < 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidString(String input) {

        boolean isValid = false;
        if (input != null && input.trim().length() > 0) {
            isValid = true;
        }
        return isValid;
    }

    private Validator() {

    }
}
