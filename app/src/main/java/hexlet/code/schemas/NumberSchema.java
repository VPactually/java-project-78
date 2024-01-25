package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {
    @Override
    public NumberSchema required() {
        addPredicate(o -> o instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addPredicate(o -> o == null || (o instanceof Integer && (int) o > 0));
        return this;
    }

    public NumberSchema range(int lower, int upper) {
        addPredicate(o -> lower <= (int) o && (int) o <= upper);
        return this;
    }
}
