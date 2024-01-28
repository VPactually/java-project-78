package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addPredicate(o -> o instanceof Integer || o == null);
    }

    @Override
    public NumberSchema required() {
        addPredicate(o -> o != null);
        return this;
    }

    public NumberSchema positive() {
        addPredicate(o -> o == null || (int) o > 0);
        return this;
    }

    public NumberSchema range(int lower, int upper) {
        addPredicate(o -> o == null || lower <= (int) o && (int) o <= upper);
        return this;
    }
}
