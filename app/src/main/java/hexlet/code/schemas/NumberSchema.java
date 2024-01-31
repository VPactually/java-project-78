package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addPredicate(o -> o == null || (int) o > 0);
        return this;
    }

    public NumberSchema range(int lower, int upper) {
        addPredicate(o -> o == null || lower <= o && o <= upper);
        return this;
    }
}
