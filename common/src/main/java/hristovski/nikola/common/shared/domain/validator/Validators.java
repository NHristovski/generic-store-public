package hristovski.nikola.common.shared.domain.validator;

public class Validators {
    public static int requireInRange(int value, int min, int max) throws IllegalArgumentException {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("%d is not in the valid range {%d , %d}!", value, min, max)
            );
        }
        return value;
    }

    public static Long requireNonNegative(Long value) throws IllegalArgumentException {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(value + "is not positive!");
        }
        return value;
    }

    public static Double requireNonNegative(Double value) throws IllegalArgumentException {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(value + "is not positive!");
        }
        return value;
    }
}
