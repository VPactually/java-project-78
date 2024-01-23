package hexlet.code.schemas;

public interface BaseSchema {
    boolean isValid(Object obj);
    BaseSchema required();
}
