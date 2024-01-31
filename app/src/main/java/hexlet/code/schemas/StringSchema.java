package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addPredicate(o -> o != null && !o.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addPredicate(o -> o == null || o.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addPredicate(o -> o == null || o.contains(str));
        return this;
    }
}
