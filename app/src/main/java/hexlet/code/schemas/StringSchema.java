package hexlet.code.schemas;


public class StringSchema {
    private boolean required = false;
    private int strLength = 0;
    private String strSubstring = "";

    public StringSchema() {
    }

    public boolean isValid(String str) {
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

    public StringSchema required() {
        required = true;
        return this;
    }

    public boolean isValid(int number) {
        return isValid(Integer.toString(number));
    }

}
