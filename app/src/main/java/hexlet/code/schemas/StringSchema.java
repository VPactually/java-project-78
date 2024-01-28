package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addPredicate(o -> o instanceof String || o == null);
    }

    @Override
    public StringSchema required() {
        addPredicate(o -> o != null && !o.toString().isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addPredicate(o -> o == null || o.toString().length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addPredicate(o -> o == null || o.toString().contains(str));
        return this;
    }
}
