package hexlet.code.schemas;


public final class StringSchema implements BaseSchema {
    private boolean required = false;
    private int strLength = 0;
    private String strSubstring = "";

    @Override
    public boolean isValid(Object obj) {
        String str = (String) obj;
        if (!required) {
            return true;
        }
        if (str == null || str.isEmpty()) {
            return false;
        }

        var lengthValid = str.length() >= this.strLength;
        var containsValid = str.contains(this.strSubstring);
        return lengthValid && containsValid;

    }

    public StringSchema minLength(int length) {
        this.strLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.strSubstring = substring;
        return this;
    }

    @Override
    public StringSchema required() {
        required = true;
        return this;
    }


}
