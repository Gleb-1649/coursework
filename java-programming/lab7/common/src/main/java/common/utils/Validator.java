package common.utils;

public class Validator {
    public String validateNonEmptyString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " not empty");
        return input.trim();
    }
    public Long validateLong(String input, String fieldName, Long max) {
        try {
            Long v = Long.parseLong(input.trim());
            if (max != null && v > max) throw new IllegalArgumentException(fieldName + " ≤ " + max);
            return v;
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + " must be long");
        }
    }
    public Integer validateInteger(String input, String fieldName) {
        try { return Integer.parseInt(input.trim()); }
        catch (Exception e) { throw new IllegalArgumentException(fieldName + " must be int"); }
    }
    public Float validateFloat(String input, String fieldName, boolean positive) {
        try {
            Float v = Float.parseFloat(input.trim());
            if (positive && v <= 0) throw new IllegalArgumentException(fieldName + " > 0");
            return v;
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + " must be float");
        }
    }
}
