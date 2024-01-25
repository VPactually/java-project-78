package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {
    @Override
    public BaseSchema required() {
        addPredicate(o -> o instanceof Integer);
        return this;
    }

    public BaseSchema positive() {
        addPredicate(o -> o == null || (int) o > 0);
        return this;
    }

    public BaseSchema range(int lower, int upper) {
        addPredicate(o -> lower <= (int) o && (int) o <= upper);
        return this;
    }
}
