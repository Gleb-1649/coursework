package utils;

/**
 * Класс Validator содержит методы для проверки корректности пользовательского ввода.
 */
public class Validator {
    public String validateNonEmptyString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " не может быть пустым");
        }
        return input.trim();
    }

    public Long validateLong(String input, String fieldName, Long maxValue) {
        try {
            Long value = Long.parseLong(input.trim());
            if (maxValue != null && value > maxValue) {
                throw new IllegalArgumentException(fieldName + " должно быть не более " + maxValue);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " должно быть числом");
        }
    }

    public Integer validateInteger(String input, String fieldName) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " должно быть целым числом");
        }
    }

    public Float validateFloat(String input, String fieldName, boolean mustBePositive) {
        try {
            Float value = Float.parseFloat(input.trim());
            if (mustBePositive && value <= 0) {
                throw new IllegalArgumentException(fieldName + " должно быть больше 0");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " должно быть числом с плавающей запятой");
        }
    }
}


