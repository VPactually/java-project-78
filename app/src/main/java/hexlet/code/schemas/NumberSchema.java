package hexlet.code.schemas;

public class NumberSchema implements BaseSchema {

    private boolean required = false;
    private boolean positive = false;
    private boolean isRange = false;
    private int lowerBound;
    private int upperBound;

    public NumberSchema() {
    }

    @Override
    public boolean isValid(Object obj) {
        if (!required) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(Integer.class)) {
            return false;
        }
        int num = (int) obj;
        boolean rangeResult = num >= lowerBound && num <= upperBound;
        if (isRange) {
            return positive && rangeResult && num > 0;
        } else {
            return !positive || num > 0;
        }
    }

    @Override
    public BaseSchema required() {
        required = true;
        return this;
    }

    public BaseSchema positive() {
        positive = true;
        return this;
    }

    public BaseSchema range(int lower, int upper) {
        lowerBound = lower;
        upperBound = upper;
        isRange = true;
        return this;
    }


}
