package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {


    @Override
    public StringSchema required() {
        addPredicate(o -> o instanceof String && !o.toString().isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addPredicate(o -> o.toString().length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addPredicate(o -> o instanceof String && o.toString().contains(str));
        return this;
    }
}
