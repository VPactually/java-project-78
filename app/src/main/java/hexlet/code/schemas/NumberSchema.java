package hexlet.code.schemas;

public class NumberSchema implements BaseSchema {

    private boolean required = false;
    private boolean isPositive = false;
    private boolean isRange = false;
    private int lowerBound;
    private int upperBound;

    @Override
    public boolean isValid(Object obj) {
        if (required && (obj == null || !(obj instanceof Integer))) {
            return false;
        }

        if (obj == null || !(obj instanceof Integer)) {
            return true;
        }

        int num = (int) obj;

        if (isPositive && num <= 0) {
            return false;
        }

        if (isRange && (num < lowerBound || num > upperBound)) {
            return false;
        }

        return true;
    }

    @Override
    public BaseSchema required() {
        required = true;
        return this;
    }

    public BaseSchema positive() {
        isPositive = true;
        return this;
    }

    public void printFlags() {
        System.out.printf("%s - required, %s - positive, %s - range\n", required, isPositive, isRange);
    }

    public BaseSchema range(int lower, int upper) {
        lowerBound = lower;
        upperBound = upper;
        isRange = true;
        return this;
    }


}
